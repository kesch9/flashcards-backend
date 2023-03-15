package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.WordCreateDto;

public interface UserService {
    User getUserByEmail(String email);

    User addUser(User user);

    User learnWord(String word);

    User learnFlashCard(String flashCardTheme);

    User addFlashCard(String flashCardId);

    User addWord(String flashCardId, String wordId);

    User createCustomFlashCard(FlashCardCreateDto dto);

    User addCustomWord(String flashCardId, WordCreateDto dto);
}
