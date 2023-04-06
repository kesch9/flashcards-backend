package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.dto.WordCreateDto;
import java.util.List;

public interface WordService {
    WordItem findByWord(String word);

    List<WordItem> findAllByTranslates(String translates);

    WordItem getWordItemById(String id);

    WordItem addWord(WordCreateDto dto);
}
