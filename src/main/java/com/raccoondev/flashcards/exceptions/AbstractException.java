package com.raccoondev.flashcards.exceptions;

import java.io.Serial;
import lombok.NonNull;

public abstract class AbstractException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    protected AbstractException() {
        super();
    }

    protected AbstractException(@NonNull String message, Object... params) {
        super(getMessage(message, params));
    }

    protected AbstractException(@NonNull Throwable cause, @NonNull String message, Object... params) {
        super(getMessage(message, params), cause);
    }

    protected AbstractException(@NonNull Throwable cause) {
        super(cause);
    }

    @NonNull
    private static String getMessage(@NonNull String message, Object... params) {
        return String.format(message.replaceAll("\\{}", "%s"), params);
    }
}
