package com.rgv.gerenciamentoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgv.gerenciamentoapi.model.Estabelecimento;
import com.rgv.gerenciamentoapi.model.exception.EntidadeEmUsoException;
import com.rgv.gerenciamentoapi.model.exception.EstabelecimentoNaoEncontradaException;
import com.rgv.gerenciamentoapi.model.exception.ProfissionalNaoEncontradaException;
import com.rgv.gerenciamentoapi.repository.EstabelecimentoRepository;

@Service
public class EstabelecimentoService {
	
	private static final String MSG_ESTADO_EM_USO = "Estabelecimento de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;
	
	@Transactional
	public Estabelecimento salvar(Estabelecimento estabelecimento) {
		return estabelecimentoRepository.save(estabelecimento);
	}
	
	@Transactional
	public void excluir(Long estabelecimentoId) {
		try {
			estabelecimentoRepository.deleteById(estabelecimentoId);
			estabelecimentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProfissionalNaoEncontradaException(estabelecimentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estabelecimentoId));
		}
	}
	
	public Estabelecimento getOrFail(Long estabelecimentoId) {
		return estabelecimentoRepository
				.findById(estabelecimentoId)
				.orElseThrow(() -> new EstabelecimentoNaoEncontradaException(estabelecimentoId));
	}

}
