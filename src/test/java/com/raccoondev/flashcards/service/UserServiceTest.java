package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.FlashCardApplicationTests;
import com.raccoondev.flashcards.repository.UserRepository;
import com.raccoondev.flashcards.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceTest extends FlashCardApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void getUserByEmailTest() {

    }

    @Test
    void addUserTest() {

    }

    @Test
    void learnWordTest() {

    }

    @Test
    void learnFlashCardTest() {

    }

    @Test
    void addFlashCardTest() {

    }

    @Test
    void addWordTest() {

    }

    @Test
    void createCustomFlashCardTest() {

    }

    @Test
    void addCustomWordTest() {

    }
}
