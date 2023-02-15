package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.repository.EnglishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnglishWordServiceImpl implements EnglishWordService{

    private final EnglishRepository englishRepository;

    @Override
    public WordItem findByWord(final String word) {
        return englishRepository.findByWord(word);
    }
}
