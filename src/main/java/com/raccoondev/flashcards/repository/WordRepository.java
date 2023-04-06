package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.WordItem;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<WordItem, String> {
    Optional<WordItem> findByWord(String word);

    List<WordItem> findAllByWordIn(Collection<String> word);

    List<WordItem> findAllByTranslatesContaining(String translate);
}
