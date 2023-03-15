package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EnglishWordServiceImpl implements EnglishWordService {

    private final WordRepository wordRepository;

    @Override
    public WordItem findByWord(final String word) {
        return wordRepository.findByWord(word);
    }

    @Override
    public List<WordItem> findAllByTranslates(final String translates) {
        return wordRepository.findAllByTranslatesContaining(translates);
    }
}
