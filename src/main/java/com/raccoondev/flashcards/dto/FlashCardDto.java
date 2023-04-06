package com.raccoondev.flashcards.dto;

import java.util.Set;
import lombok.Data;
import lombok.NonNull;

@Data
public class FlashCardDto {
    @NonNull
    private String id;
    @NonNull
    private String theme;
    Set<WordDto> words;
}
