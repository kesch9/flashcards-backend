package com.raccoondev.flashcards.dto;

import java.util.List;
import lombok.Data;

@Data
public class FlashCardDto {
    private String id;
    private String theme;
    List<WordDto> words;
}
