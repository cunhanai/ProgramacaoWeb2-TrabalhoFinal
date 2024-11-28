package com.trabalho.projeto.exception;

public class LoginException extends RuntimeException {

    public LoginException(String pMessage) {
        super(pMessage);
    }
    public LoginException(String pMessage, Throwable pCause) {
        super(pMessage, pCause);
    }
}
