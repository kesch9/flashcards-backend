package com.raccoondev.flashcards.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class FlashCardCreateDto {
    @NonNull
    private String theme;
    Set<WordDto> words;
}
