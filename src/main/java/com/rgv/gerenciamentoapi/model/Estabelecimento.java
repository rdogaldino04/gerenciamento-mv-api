package com.rgv.gerenciamentoapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(generator = "idGenEst")
	@SequenceGenerator(name = "idGenEst", sequenceName = "sq_estab", allocationSize = 1)
	private Long id;

	private String nome;

	private String endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
