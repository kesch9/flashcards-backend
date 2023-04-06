package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.FlashCard;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashCardsRepository extends MongoRepository<FlashCard, String> {
    Optional<FlashCard> findByTheme(final String theme);
}
