package com.robson.helpdesk.services.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //Construtor que recebe uma String e a causa da exception
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    //Construtor que recebe uma String
    public ObjectNotFoundException(String cause) {
        super(cause);
    }
}
