package com.raccoondev.flashcards.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.FlashCardDto;
import com.raccoondev.flashcards.dto.FlashCardUpdateDto;
import com.raccoondev.flashcards.dto.WordDto;
import com.raccoondev.flashcards.exceptions.FlashCardNotFoundException;
import com.raccoondev.flashcards.exceptions.WordNotFoundException;
import com.raccoondev.flashcards.repository.FlashCardsRepository;
import com.raccoondev.flashcards.repository.WordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlashCardServiceImpl implements FlashCardService {

    private static final String FLASH_CARD_WITH_ID_NOT_FOUND = "FlashCard with id {} not found";

    private static final String FLASH_CARD_WITH_THEME_NOT_FOUND = "FlashCard with theme {} not found";

    private final FlashCardsRepository repository;

    private final WordRepository wordRepository;

    private final ObjectMapper objectMapper;

    @Override
    public List<FlashCardDto> findAll() {
        var allCards = repository.findAll();
        return allCards.stream().map(this::getDto).toList();
    }

    @Override
    public FlashCardDto findByTheme(@NonNull String theme) {
        return getDto(repository.findByTheme(theme)
            .orElseThrow(() -> new FlashCardNotFoundException(FLASH_CARD_WITH_THEME_NOT_FOUND, theme)));
    }

    @Override
    public FlashCardDto getFlashCardById(final String id) {
        var byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new FlashCardNotFoundException(FLASH_CARD_WITH_ID_NOT_FOUND, id);
        }
        return getDto(byId.get());
    }

    @Override
    public FlashCardDto insert(final FlashCardCreateDto dto) {
        var allByWord = wordRepository.findAllById(dto.getWords().stream().map(WordDto::getId).toList());
        var flashCard = FlashCard.builder().theme(dto.getTheme()).words(allByWord).build();
        return getDto(repository.insert(flashCard));
    }

    @Override
    public FlashCardDto update(final FlashCardUpdateDto dto) {
        log.info("input {}", dto);
        var flashCard = repository.findById(dto.getId())
            .orElseThrow(() -> new FlashCardNotFoundException(FLASH_CARD_WITH_ID_NOT_FOUND, dto.getId()));
        if (dto.getTheme() != null) {
            flashCard.setTheme(dto.getTheme());
        }
        if (dto.getWords() != null) {
            var allWordById = wordRepository.findAllById(dto.getWords().stream().map(WordDto::getId).toList());
            flashCard.getWords().addAll(allWordById);
        }
        return getDto(repository.save(flashCard));
    }

    @Override
    public FlashCardDto addWord(final String cardId, final String wordId) {
        var flashCard = repository.findById(cardId)
            .orElseThrow(() -> new FlashCardNotFoundException(FLASH_CARD_WITH_ID_NOT_FOUND, cardId));
        var wordItem = wordRepository.findById(wordId)
            .orElseThrow(() -> new WordNotFoundException(FLASH_CARD_WITH_ID_NOT_FOUND, wordId));
        flashCard.getWords().add(wordItem);
        return getDto(repository.save(flashCard));
    }

    private FlashCardDto getDto(final FlashCard flashCard) {
        var flashCardDto = objectMapper.convertValue(flashCard, FlashCardDto.class);
        flashCardDto.setWords(flashCard.getWords()
            .stream()
            .map(wordItem -> objectMapper.convertValue(wordItem, WordDto.class))
            .collect(Collectors.toSet()));
        return flashCardDto;
    }
}
