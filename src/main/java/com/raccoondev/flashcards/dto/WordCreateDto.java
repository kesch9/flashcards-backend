package com.raccoondev.flashcards.dto;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class WordCreateDto {
    @NonNull
    private String word;
    private String transcription;
    @NotEmpty
    private List<String> translates;
}
