package com.rgv.gerenciamentoapi.controller.model.input;

import javax.validation.constraints.NotBlank;

public class EstabelecimentoInput {

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
