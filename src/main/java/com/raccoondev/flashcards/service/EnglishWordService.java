package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.WordItem;
import java.util.List;

public interface EnglishWordService {
    WordItem findByWord(String word);

    List<WordItem> findAllByTranslates(String translates);
}
