package com.robson.helpdesk.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> errors = new ArrayList<>();

    //Construtor com parametros

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String massage, String path) {
        super(timestamp, status, error, massage, path);
    }

    //Métodos modificadores e recuperadores de dados

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String messagem) {
        this.errors.add(new FieldMessage(fieldName, messagem));
    }
}
