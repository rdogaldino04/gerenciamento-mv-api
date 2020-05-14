package com.rgv.gerenciamentoapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_estabelecimento")
public class Estabelecimento {

	@Id
	@GeneratedValue(generator = "idGenEst")
	@SequenceGenerator(name = "idGenEst", sequenceName = "sq_estab", allocationSize = 1)
	private Long id;

	private String nome;

	private String endereco;

	@ManyToMany
	@JoinTable(name = "tb_estab_prof", joinColumns = @JoinColumn(name = "estab_id"), inverseJoinColumns = @JoinColumn(name = "prof_id"))
	private Set<Profissional> profissionais = new HashSet<>();
	
	public boolean removerProfissional(Profissional profissional) {
		return getProfissionais().remove(profissional);
	}
	
	public boolean adicionarProfissional(Profissional profissional) {
		return getProfissionais().add(profissional);
	}

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

	public Set<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(Set<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

}
