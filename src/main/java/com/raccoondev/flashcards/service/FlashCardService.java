package com.raccoondev.flashcards.service;

import com.raccoondev.flashcards.document.FlashCard;
import com.raccoondev.flashcards.dto.FlashCardDto;
import java.util.List;

public interface FlashCardService {
    List<FlashCard> findAll();

    FlashCard findByTheme(String theme);

    FlashCard insert(FlashCardDto dto);

    FlashCard update(FlashCardDto dto);
}
