package com.raccoondev.flashcards.dto;

import java.util.List;
import lombok.Data;
import lombok.NonNull;

@Data
public class FlashCardCreateDto {
    @NonNull
    private String theme;
    List<WordDto> words;
}
