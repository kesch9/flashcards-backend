package com.raccoondev.flashcards.exceptions;

import java.io.Serial;
import lombok.NonNull;

public class DuplicateUserException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateUserException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
