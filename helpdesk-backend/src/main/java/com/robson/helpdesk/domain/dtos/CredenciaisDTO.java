package com.robson.helpdesk.domain.dtos;

public class CredenciaisDTO {

    //Atributos
    private String email;
    private String senha;

    //Método Getters e Setters = Obter dados e Moditficar dados
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
