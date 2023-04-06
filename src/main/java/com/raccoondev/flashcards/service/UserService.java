package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.WordCreateDto;

public interface UserService {
    User getUserByEmail(String email);

    User addUser(User user);

    User learnWord(String email, String word);

    User learnFlashCard(String email, String theme);

    User addFlashCard(String email, String flashCardId);

    User addWord(String email, String theme, String wordId);

    User createCustomFlashCard(String email, FlashCardCreateDto dto);

    User addCustomWord(String email, String theme, WordCreateDto dto);
}
