package com.robson.helpdesk.resources.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    //Atributos
    private String fieldName;
    private String message;

    //Construtor sem argumentos


    public FieldMessage() {
        super();
    }

    //Construtor com argumentos
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    //Métodos modificadores e recuperadores

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
