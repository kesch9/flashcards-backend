package com.raccoondev.flashcards.exceptions;

import java.io.Serial;
import lombok.NonNull;

public class WordNotFoundException extends ResourceNotFoundException {
    @Serial
    private static final long serialVersionUID = 1L;

    public WordNotFoundException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
