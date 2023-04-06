package com.raccoondev.flashcards.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class FlashCardUpdateDto {
    @NonNull
    private String id;
    private String theme;
    Set<WordDto> words;
}
