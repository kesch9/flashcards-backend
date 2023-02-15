package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.WordItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnglishRepository extends MongoRepository<WordItem, String> {
    WordItem findByWord(final String word);
}
