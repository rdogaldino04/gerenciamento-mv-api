package com.rgv.gerenciamentoapi.model.exception;

public class EstabelecimentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstabelecimentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public EstabelecimentoNaoEncontradaException(Long cozinhaId) {
		this(String.format("Não existe um cadastro de estabelecimento com código %d", cozinhaId));
	}
	
}