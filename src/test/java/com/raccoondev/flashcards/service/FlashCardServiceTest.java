package com.raccoondev.flashcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.raccoondev.flashcards.FlashCardApplicationTests;
import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.FlashCardUpdateDto;
import com.raccoondev.flashcards.dto.WordDto;
import com.raccoondev.flashcards.repository.FlashCardsRepository;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class FlashCardServiceTest extends FlashCardApplicationTests {

    @Autowired
    private FlashCardService flashCardService;

    @Autowired
    private FlashCardsRepository flashCardsRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void findAllTest() {
        flashCardsRepository.deleteAll();
        var word1 = wordRepository.insert(
            WordItem.builder().word("test1").translates(Collections.singletonList("тест1")).build());
        var word2 = wordRepository.insert(
            WordItem.builder().word("test2").translates(Collections.singletonList("тест2")).build());
        var flashCard1 = flashCardsRepository.insert(
            FlashCard.builder().theme("test1").words(Collections.singletonList(word1)).build());
        var flashCard2 = flashCardsRepository.insert(
            FlashCard.builder().theme("test2").words(Collections.singletonList(word2)).build());

        var flashCards = flashCardService.findAll();
        assertEquals(2, flashCards.size());
        var flashCard1DtoOptional =
            flashCards.stream().filter(flashCardDto -> flashCardDto.getId().equals(flashCard1.getId())).findFirst();
        assertTrue(flashCard1DtoOptional.isPresent());
        var flashCard1Dto = flashCard1DtoOptional.get();
        assertEquals(flashCard1Dto.getId(), flashCard1.getId());
        assertEquals(flashCard1Dto.getTheme(), flashCard1.getTheme());
        assertEquals(1, flashCard1Dto.getWords().size());
        assertEquals(flashCard1Dto.getWords().size(), flashCard1.getWords().size());
        assertEquals(flashCard1Dto.getWords().iterator().next().getId(), flashCard1.getWords().get(0).getId());
        assertEquals(flashCard1Dto.getWords().iterator().next().getWord(), flashCard1.getWords().get(0).getWord());
        assertEquals(flashCard1Dto.getWords().iterator().next().getTranscription(),
            flashCard1.getWords().get(0).getTranscription());
        assertEquals(1, flashCard1Dto.getWords().iterator().next().getTranslates().size());
        assertEquals(flashCard1Dto.getWords().iterator().next().getTranslates().get(0),
            flashCard1.getWords().get(0).getTranslates().get(0));

        var flashCard2DtoOptional =
            flashCards.stream().filter(flashCardDto -> flashCardDto.getId().equals(flashCard2.getId())).findFirst();
        assertTrue(flashCard2DtoOptional.isPresent());
        var flashCard2Dto = flashCard2DtoOptional.get();
        assertEquals(flashCard2Dto.getId(), flashCard2.getId());
        assertEquals(flashCard2Dto.getTheme(), flashCard2.getTheme());
        assertEquals(1, flashCard2Dto.getWords().size());
        assertEquals(flashCard2Dto.getWords().size(), flashCard2.getWords().size());
        assertEquals(flashCard2Dto.getWords().iterator().next().getId(), flashCard2.getWords().get(0).getId());
        assertEquals(flashCard2Dto.getWords().iterator().next().getWord(), flashCard2.getWords().get(0).getWord());
        assertEquals(flashCard2Dto.getWords().iterator().next().getTranscription(),
            flashCard1.getWords().get(0).getTranscription());
        assertEquals(1, flashCard2Dto.getWords().iterator().next().getTranslates().size());
        assertEquals(flashCard2Dto.getWords().iterator().next().getTranslates().get(0),
            flashCard2.getWords().get(0).getTranslates().get(0));
    }

    @Test
    void findByThemeTest() {
        var flashCard = flashCardsRepository.insert(FlashCard.builder().theme("theme").build());
        var byTheme = flashCardService.findByTheme("theme");
        assertEquals(flashCard.getTheme(), byTheme.getTheme());
    }

    @Test
    void getFlashCardByIdTest() {
        var flashCard = flashCardsRepository.insert(FlashCard.builder().theme("theme").build());
        var byTheme = flashCardService.getFlashCardById(flashCard.getId());
        assertEquals(flashCard.getId(), byTheme.getId());
    }

    @Test
    void insertTest() {
        var word = wordRepository.insert(WordItem.builder()
            .word("insert")
            .translates(Collections.singletonList("вставка"))
            .transcription("ˈɪnsɜːt")
            .build());
        var flashCardCreateDto = FlashCardCreateDto.builder()
            .theme("insert")
            .words(Set.of(WordDto.builder()
                .id(word.getId())
                .word(word.getWord())
                .translates(word.getTranslates())
                .transcription(word.getTranscription())
                .build()))
            .build();
        var insert = flashCardService.insert(flashCardCreateDto);
        assertEquals(insert.getTheme(), flashCardCreateDto.getTheme());
        assertEquals(1, insert.getWords().size());
        assertEquals(insert.getWords().iterator().next().getWord(),
            flashCardCreateDto.getWords().iterator().next().getWord());
        assertEquals(insert.getWords().iterator().next().getTranscription(),
            flashCardCreateDto.getWords().iterator().next().getTranscription());
        assertEquals(1, insert.getWords().iterator().next().getTranslates().size());
        assertEquals(insert.getWords().iterator().next().getTranslates().get(0),
            flashCardCreateDto.getWords().iterator().next().getTranslates().get(0));
    }

    @Test
    void updateTest() {
        var flashCard = flashCardsRepository.insert(FlashCard.builder().theme("update").build());
        var word = wordRepository.insert(WordItem.builder()
            .word("update")
            .translates(Collections.singletonList("обновление"))
            .transcription("ˈʌpˈdeɪt")
            .build());
        var flashCardUpdateDto = FlashCardUpdateDto.builder()
            .id(flashCard.getId())
            .theme("update_new")
            .words(Set.of(WordDto.builder()
                .id(word.getId())
                .word(word.getWord())
                .transcription(word.getTranscription())
                .translates(word.getTranslates())
                .build()))
            .build();
        var update = flashCardService.update(flashCardUpdateDto);
        assertEquals(update.getId(), flashCardUpdateDto.getId());
        assertEquals(update.getTheme(), flashCardUpdateDto.getTheme());
        assertEquals(1, update.getWords().size());
        assertEquals(update.getWords().iterator().next().getWord(), word.getWord());
        assertEquals(update.getWords().iterator().next().getTranscription(), word.getTranscription());
        assertEquals(1, update.getWords().iterator().next().getTranslates().size());
        assertEquals(update.getWords().iterator().next().getTranslates().get(0), word.getTranslates().get(0));
    }

    @Test
    void addWordTest() {
        var flashCard = flashCardsRepository.insert(FlashCard.builder().theme("add").build());
        var word = wordRepository.insert(WordItem.builder()
            .word("add")
            .translates(Collections.singletonList("добавление"))
            .transcription("add")
            .build());
        var addWord = flashCardService.addWord(flashCard.getId(), word.getId());
        assertEquals(addWord.getId(), flashCard.getId());
        assertEquals(1, addWord.getWords().size());
        assertEquals(addWord.getWords().iterator().next().getWord(), word.getWord());
        assertEquals(addWord.getWords().iterator().next().getTranscription(), word.getTranscription());
        assertEquals(1, addWord.getWords().iterator().next().getTranslates().size());
        assertEquals(addWord.getWords().iterator().next().getTranslates().get(0), word.getTranslates().get(0));
    }
}
