package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.dto.FlashCardCreateDto;
import com.raccoondev.flashcards.dto.FlashCardDto;
import com.raccoondev.flashcards.dto.FlashCardUpdateDto;
import java.util.List;

public interface FlashCardService {
    List<FlashCardDto> findAll();

    FlashCardDto findByTheme(String theme);

    FlashCardDto getFlashCardById(String id);

    FlashCardDto insert(FlashCardCreateDto dto);

    FlashCardDto update(FlashCardUpdateDto dto);

    FlashCardDto addWord(String cardId, String wordId);
}
