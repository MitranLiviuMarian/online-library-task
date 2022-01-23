package com.schwarz.onlinelibrary.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
