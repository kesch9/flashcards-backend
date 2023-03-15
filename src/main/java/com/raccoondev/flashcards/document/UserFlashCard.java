package com.raccoondev.flashcards.document;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFlashCard {
    @NotNull
    private String theme;

    @Builder.Default
    private boolean isLearned = false;

    private List<UserWord> words;
}
