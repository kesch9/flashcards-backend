package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.FlashCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashCardsRepository extends MongoRepository<FlashCard, String> {
    FlashCard findByTheme(final String theme);
}
