package com.raccoondev.flashcards.repository;

import com.raccoondev.flashcards.document.WordItem;
import java.util.Collection;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WordRepository extends MongoRepository<WordItem, String> {
    WordItem findByWord(final String word);

    List<WordItem> findAllByWordIn(Collection<String> word);

    List<WordItem> findAllByTranslatesContaining(String translate);
}
