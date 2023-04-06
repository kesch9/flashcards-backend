package com.raccoondev.flashcards.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoondev.flashcards.document.WordItem;
import com.raccoondev.flashcards.dto.WordCreateDto;
import com.raccoondev.flashcards.exceptions.WordNotFoundException;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    private final ObjectMapper objectMapper;

    @Override
    public WordItem findByWord(final String word) {
        var byWord = wordRepository.findByWord(word);
        if (byWord.isEmpty()) {
            throw new WordNotFoundException("Word {} not found", word);
        }
        return byWord.get();
    }

    @Override
    public List<WordItem> findAllByTranslates(final String translates) {
        return wordRepository.findAllByTranslatesContaining(translates);
    }

    @Override
    public WordItem getWordItemById(final String id) {
        var byId = wordRepository.findById(id);
        if (byId.isEmpty()) {
            throw new WordNotFoundException("Word with id {} not found", id);
        }
        return byId.get();
    }

    @Override
    public WordItem addWord(final WordCreateDto dto) {
        return wordRepository.insert(objectMapper.convertValue(dto, WordItem.class));
    }
}
