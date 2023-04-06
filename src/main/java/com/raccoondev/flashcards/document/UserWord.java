package com.raccoondev.flashcards.document;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserWord {
    private boolean isLearned;
    @NonNull
    private String word;
    private String transcription;
    @NotEmpty
    private List<String> translates;
}
