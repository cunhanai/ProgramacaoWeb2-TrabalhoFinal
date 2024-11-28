package com.trabalho.projeto.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException(String pMessage) {
        super(pMessage);
    }
    public InvalidLoginException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
