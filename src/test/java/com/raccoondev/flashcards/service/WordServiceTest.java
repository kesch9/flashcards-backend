package com.raccoondev.flashcards.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.raccoondev.flashcards.FlashCardApplicationTests;
import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.dto.WordCreateDto;
import com.raccoondev.flashcards.exceptions.WordNotFoundException;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class WordServiceTest extends FlashCardApplicationTests {

    @Autowired
    private WordService wordService;

    @Autowired
    private WordRepository wordRepository;

    @Test
    void addWordTest() {
        var test = wordService.addWord(
            WordCreateDto.builder().word("add").translates(Collections.singletonList("add")).build());
        assertEquals(test.getWord(), wordService.findByWord("add").getWord());
    }

    @Test
    void findByWordTest() {
        assertThrows(WordNotFoundException.class, () -> wordService.findByWord("test"));
        var test = wordService.addWord(
            WordCreateDto.builder().word("test").translates(Collections.singletonList("Тест")).build());
        assertEquals(test.getWord(), wordService.findByWord("test").getWord());
    }

    @Test
    void findAllByTranslatesTest() {
        assertEquals(0, wordService.findAllByTranslates("Тест1").size());
        var test = wordService.addWord(
            WordCreateDto.builder().word("test1").translates(Collections.singletonList("Тест1")).build());
        var allByTranslates = wordService.findAllByTranslates("Тест1");
        assertEquals(1, allByTranslates.size());
        assertEquals(test, allByTranslates.get(0));
    }

    @Test
    void getWordItemByIdTest() {
        var word = wordRepository.insert(WordItem.builder().word("test2").build());
        assertEquals(word, wordService.getWordItemById(word.getId()));
    }
}
