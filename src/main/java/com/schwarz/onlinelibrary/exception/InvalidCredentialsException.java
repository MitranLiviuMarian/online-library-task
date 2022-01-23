package com.schwarz.onlinelibrary.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
