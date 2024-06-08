package com.robson.helpdesk.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timestamp;
    private Integer status;
    private String error;
    private String massage;
    private String path;

    //Construtor padrao
    public StandardError() {
        super();
    }

    // //Construtor padrao com argumentos
    public StandardError(Long timestamp, Integer status, String error, String massage, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.massage = massage;
        this.path = path;
    }

    // Getters e Setters - Acessar e modificar os atributos
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
