package com.raccoondev.flashcards.document;

import jakarta.validation.constraints.NotNull;
import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserFlashCard {
    @NotNull
    private String theme;

    @Builder.Default
    private boolean isLearned = false;

    private Set<UserWord> words;
}
