/*
 *  =======================================================================
 *
 *  Copyright (c) 2020 Northern Capital Gateway, LLC. All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  Northern Capital Gateway, LLC.
 *  You shall not disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with
 *  Northern Capital Gateway, LLC
 *
 *  =======================================================================
 */

package com.raccoondev.flashcards.exceptions;

import lombok.NonNull;

public class ResourceNotFoundException extends AbstractException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(@NonNull String message, Object... params) {
        super(message, params);
    }

    public ResourceNotFoundException(@NonNull Throwable cause, @NonNull String message, Object... params) {
        super(cause, message, params);
    }

    public ResourceNotFoundException(@NonNull Throwable cause) {
        super(cause);
    }
}
