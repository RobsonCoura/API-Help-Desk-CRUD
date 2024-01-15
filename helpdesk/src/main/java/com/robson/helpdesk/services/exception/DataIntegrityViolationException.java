package com.robson.helpdesk.services.exception;

public class DataIntegrityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //Construtor que recebe uma String e a causa da exception
    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    //Construtor que recebe uma String
    public DataIntegrityViolationException(String cause) {
        super(cause);
    }
}
