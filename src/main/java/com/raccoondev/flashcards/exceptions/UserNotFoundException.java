package com.raccoondev.flashcards.exceptions;

import java.io.Serial;
import lombok.NonNull;

public class UserNotFoundException extends AbstractException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(@NonNull String message, Object... params) {
        super(message, params);
    }
}
