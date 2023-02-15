package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.WordItem;

public interface EnglishWordService {

    WordItem findByWord(String word);
}
