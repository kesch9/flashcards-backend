package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.User;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.WordCreateDto;
import com.raccoondev.flashcards.exceptions.DuplicateUserException;
import com.raccoondev.flashcards.exceptions.UserNotFoundException;
import com.raccoondev.flashcards.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public User getUserByEmail(@NonNull final String email) {
        var userById = userRepository.findById(email);
        if (userById.isEmpty()) {
            throw new UserNotFoundException("Resource with email {} not found", email);
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
    public User learnWord(final String word) {
        return null;
    }

    @Override
    public User learnFlashCard(final String flashCardTheme) {
        return null;
    }

    @Override
    public User addFlashCard(final String flashCardId) {
        return null;
    }

    @Override
    public User addWord(final String flashCardId, final String wordId) {
        return null;
    }

    @Override
    public User createCustomFlashCard(final FlashCardCreateDto dto) {
        return null;
    }

    @Override
    public User addCustomWord(final String flashCardId, final WordCreateDto dto) {
        return null;
    }
}
