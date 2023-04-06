package com.raccoondev.flashcards.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.document.UserFlashCard;
import com.raccoondev.flashcards.document.UserWord;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.WordCreateDto;
import com.raccoondev.flashcards.exceptions.DuplicateUserException;
import com.raccoondev.flashcards.exceptions.FlashCardNotFoundException;
import com.raccoondev.flashcards.exceptions.UserNotFoundException;
import com.raccoondev.flashcards.repository.UserRepository;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final FlashCardService flashCardService;

    private final WordService wordService;

    private final ObjectMapper objectMapper;

    @Override
    public User getUserByEmail(@NonNull final String email) {
        var userById = userRepository.findById(email);
        if (userById.isEmpty()) {
            throw new UserNotFoundException("User with email {} not found", email);
        }
        return userById.get();
    }

    @Override
    public User addUser(final User user) {
        if (userRepository.existsById(user.getEmail())) {
            throw new DuplicateUserException("User with {} exists", user.getEmail());
        }
        return userRepository.insert(user);
    }

    @Override
    public User learnWord(String email, String word) {
        var user = getUserByEmail(email);
        user.getCards().forEach(userFlashCard -> userFlashCard.getWords().forEach(userWord -> {
            if (userWord.getWord().equals(word)) {
                userWord.setLearned(true);
            }
        }));
        return userRepository.save(user);
    }

    @Override
    public User learnFlashCard(String email, String theme) {
        var user = getUserByEmail(email);
        user.getCards().forEach(userFlashCard -> {
            if (userFlashCard.getTheme().equals(theme)) {
                userFlashCard.setLearned(true);
            }
        });
        return userRepository.save(user);
    }

    @Override
    public User addFlashCard(String email, String flashCardId) {
        var user = getUserByEmail(email);
        var flashCard = flashCardService.getFlashCardById(flashCardId);
        var newFlashCards = UserFlashCard.builder()
            .theme(flashCard.getTheme())
            .words(flashCard.getWords()
                .stream()
                .map(wordItem -> objectMapper.convertValue(wordItem, UserWord.class))
                .collect(Collectors.toSet()))
            .build();
        user.getCards().add(newFlashCards);
        return userRepository.save(user);
    }

    @Override
    public User addWord(String email, String theme, String wordId) {
        var word = wordService.getWordItemById(wordId);
        var user = getUserByEmail(email);
        var first = user.getCards().stream().filter(card -> card.getTheme().equals(theme)).findFirst();
        if (first.isEmpty()) {
            throw new FlashCardNotFoundException("FlashCard with theme {} not found", theme);
        }
        first.get().getWords().add(objectMapper.convertValue(word, UserWord.class));
        return userRepository.save(user);
    }

    @Override
    public User createCustomFlashCard(String email, final FlashCardCreateDto dto) {
        var user = getUserByEmail(email);
        user.getCards()
            .add(UserFlashCard.builder()
                .theme(dto.getTheme())
                .words(dto.getWords()
                    .stream()
                    .map(wordDto -> objectMapper.convertValue(wordDto, UserWord.class))
                    .collect(Collectors.toSet()))
                .build());
        return userRepository.save(user);
    }

    @Override
    public User addCustomWord(String email, String theme, final WordCreateDto dto) {
        var user = getUserByEmail(email);
        var first = user.getCards().stream().filter(card -> card.getTheme().equals(theme)).findFirst();
        if (first.isEmpty()) {
            throw new FlashCardNotFoundException("FlashCard with theme {} not found", theme);
        }
        first.get().getWords().add(objectMapper.convertValue(dto, UserWord.class));
        return userRepository.save(user);
    }
}
