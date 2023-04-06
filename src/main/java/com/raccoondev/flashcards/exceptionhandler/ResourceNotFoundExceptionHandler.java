package com.raccoondev.flashcards.exceptionhandler;

import com.raccoondev.flashcards.exceptions.DuplicateUserException;
import com.raccoondev.flashcards.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@AllArgsConstructor
public class ResourceNotFoundExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleControllerException(@NonNull ResourceNotFoundException ex) {
        return new ResponseEntity<>(
            new ErrorResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage()),
            HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleControllerException(@NonNull DuplicateUserException ex) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
