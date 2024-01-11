package com.robson.helpdesk.domain.enums;

public enum Prioridade {

	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

	// Atributos - são as propriedades de um objeto
	private Integer codigo;
	private String descricao;

	// Construtor com parametros - Criação de um objeto desta classe
	private Prioridade(Integer codigo, String descricao) {
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

	// Método para retornar o tipo de prioridade de atendimento
	public static Prioridade toEnum(Integer cod) {
		// Validacao do perfil
		if (cod == null) {
			return null;
		}
		// Laco de repeticao
		for (Prioridade x : Prioridade.values()) {
			// Verificar se o prioridade de atendimento existe
			if (cod.equals(x.getCodigo())) {
				return x;
			}
			// Lancar uma exceção caso prioridade de atendimento seja invalida
			throw new IllegalArgumentException("Prioridade inválida!");
		}
		return null;
	}
}
