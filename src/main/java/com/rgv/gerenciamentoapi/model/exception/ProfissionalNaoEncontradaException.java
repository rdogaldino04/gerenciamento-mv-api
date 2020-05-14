package com.rgv.gerenciamentoapi.model.exception;

public class ProfissionalNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProfissionalNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public ProfissionalNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de profissional com código %d", cozinhaId));
	}
	
}