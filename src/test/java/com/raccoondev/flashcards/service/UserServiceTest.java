package com.raccoondev.flashcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.raccoondev.flashcards.FlashCardApplicationTests;
import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.document.UserFlashCard;
import com.raccoondev.flashcards.document.UserWord;
import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.WordCreateDto;
import com.raccoondev.flashcards.dto.WordDto;
import com.raccoondev.flashcards.repository.FlashCardsRepository;
import com.raccoondev.flashcards.repository.UserRepository;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest extends FlashCardApplicationTests {

    private static final String EMAIL = "test@gmail.ru";
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private FlashCardsRepository flashCardsRepository;

    @BeforeAll
    void createUser() {
        userService.addUser(User.builder()
            .email(EMAIL)
            .name("test")
            .cards(Set.of(UserFlashCard.builder()
                .theme("test")
                .words(
                    Set.of(UserWord.builder().word("test").translates(List.of("тест")).transcription("test").build()))
                .build()))
            .build());
    }

    @Test
    void getUserByEmailTest() {
        var userByEmail = userService.getUserByEmail(EMAIL);
        assertEquals(EMAIL, userByEmail.getEmail());
    }

    @Test
    void addUserTest() {
        var user = userService.addUser(User.builder()
            .email("addtest@gmail.ru")
            .name("test")
            .cards(Set.of(UserFlashCard.builder().theme("test").build()))
            .build());
        var byId = userRepository.findById(user.getEmail());
        assertTrue(byId.isPresent());
        var userDb = byId.get();
        assertEquals(user, userDb);
    }

    @Test
    void learnWordTest() {
        userService.learnWord(EMAIL, "test");
        var byId = userRepository.findById(EMAIL).orElseThrow();
        assertTrue(byId.getCards().iterator().next().getWords().iterator().next().isLearned());
    }

    @Test
    void learnFlashCardTest() {
        userService.learnFlashCard(EMAIL, "test");
        var byId = userRepository.findById(EMAIL).orElseThrow();
        assertTrue(byId.getCards().iterator().next().isLearned());
    }

    @Test
    void addFlashCardTest() {
        var test2 = flashCardsRepository.insert(FlashCard.builder().theme("test2").build());
        userService.addFlashCard(EMAIL, test2.getId());
        var byId = userRepository.findById(EMAIL).orElseThrow();
        assertEquals(2, byId.getCards().size());
        assertTrue(
            byId.getCards().stream().anyMatch(userFlashCard -> test2.getTheme().equals(userFlashCard.getTheme())));
    }

    @Test
    void addWordTest() {
        var wordTest = wordRepository.insert(WordItem.builder().word("test2").build());
        userService.addWord(EMAIL, "test", wordTest.getId());
        var byId = userRepository.findById(EMAIL).orElseThrow();
        var userFlashCard =
            byId.getCards().stream().filter(card -> "test".equals(card.getTheme())).findFirst().orElseThrow();
        assertEquals(2, userFlashCard.getWords().size());
        assertTrue(userFlashCard.getWords().stream().anyMatch(word -> word.getWord().equals(wordTest.getWord())));
    }

    @Test
    void createCustomFlashCardTest() {
        userService.createCustomFlashCard(EMAIL, FlashCardCreateDto.builder()
            .theme("test3")
            .words(Set.of(WordDto.builder().id("1").word("test3").build()))
            .build());
        var byId = userRepository.findById(EMAIL).orElseThrow();
        assertTrue(byId.getCards().stream().anyMatch(card -> card.getTheme().equals("test3")));
        var userFlashCard =
            byId.getCards().stream().filter(card -> "test3".equals(card.getTheme())).findFirst().orElseThrow();
        assertEquals(1, userFlashCard.getWords().size());
        assertTrue(userFlashCard.getWords().stream().anyMatch(word -> word.getWord().equals("test3")));
    }

    @Test
    void addCustomWordTest() {
        userService.addCustomWord(EMAIL, "test", WordCreateDto.builder().word("customWord").build());
        var byId = userRepository.findById(EMAIL).orElseThrow();
        var userFlashCard =
            byId.getCards().stream().filter(card -> "test".equals(card.getTheme())).findFirst().orElseThrow();
        assertTrue(userFlashCard.getWords().stream().anyMatch(word -> word.getWord().equals("customWord")));
    }
}
