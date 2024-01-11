package com.robson.helpdesk.domain.enums;

public enum Status {

	ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

	// Atributos - são as propriedades de um objeto
	private Integer codigo;
	private String descricao;

	// Construtor com parametros - Criação de um objeto desta classe
	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	// Getters e Setters - Acessar e modificar os atributos
	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	// Método para retornar o tipo de status do chamado
	public static Status toEnum(Integer cod) {
		// Validacao do perfil
		if (cod == null) {
			return null;
		}
		// Laco de repeticao
		for (Status x : Status.values()) {
			// Verificar se o status de chamado existe
			if (cod.equals(x.getCodigo())) {
				return x;
			}
			// Lancar uma exceção caso status de chamado seja invalido
			throw new IllegalArgumentException("Status inválido!");
		}
		return null;
	}
}
