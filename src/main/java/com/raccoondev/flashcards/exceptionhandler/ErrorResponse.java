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

package com.raccoondev.flashcards.exceptionhandler;

import lombok.Data;
import lombok.NonNull;

@Data
public class ErrorResponse {
    private final int status;
    @NonNull
    private final String code;
    @NonNull
    private final String message;
}
