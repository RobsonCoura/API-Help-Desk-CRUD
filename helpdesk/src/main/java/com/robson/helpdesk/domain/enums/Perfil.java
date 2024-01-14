package com.robson.helpdesk.domain.enums;

public enum Perfil {

    ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

    //Atributos - são as propriedades de um objeto
    private Integer codigo;
    private String descricao;

    //Construtor com parametros - Criação de um objeto desta classe
    private Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    //Getters e Setters - Acessar e modificar os atributos
    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    //Método para retornar o tipo de perfil de usuario
    public static Perfil toEnum(Integer cod) {
        //Validacao do perfil
        if (cod == null) {
            return null;
        }
        //Laco de repeticao
        for (Perfil x : Perfil.values()) {
            //Verificar se o perfil de usuario existe
            if (cod.equals(x.getCodigo())) {
                return x;
            }
        }
        //Lancar uma exceção caso perfil de usuario seja invalido
        throw new IllegalArgumentException("Perfil inválido!");
    }
}
