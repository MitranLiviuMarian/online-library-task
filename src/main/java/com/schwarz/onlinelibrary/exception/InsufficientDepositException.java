package com.schwarz.onlinelibrary.exception;

public class InsufficientDepositException extends RuntimeException {
    public InsufficientDepositException(String errorMessage) {
        super(errorMessage);
    }
}
