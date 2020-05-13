package com.rgv.gerenciamentoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rgv.gerenciamentoapi.model.Profissional;
import com.rgv.gerenciamentoapi.model.exception.EntidadeEmUsoException;
import com.rgv.gerenciamentoapi.model.exception.ProfissionalNaoEncontradaException;
import com.rgv.gerenciamentoapi.repository.ProfissionalRepository;

@Service
public class ProfissionalService {
	
	private static final String MSG_ESTADO_EM_USO = "Profissional de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	@Transactional
	public Profissional salvar(Profissional profissional) {
		return profissionalRepository.save(profissional);
	}
	
	@Transactional
	public void excluir(Long profissionalId) {
		try {
			profissionalRepository.deleteById(profissionalId);
			profissionalRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new ProfissionalNaoEncontradaException(profissionalId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, profissionalId));
		}
	}
	
	public Profissional getOrFail(Long profissionalId) {
		return profissionalRepository
				.findById(profissionalId)
				.orElseThrow(() -> new ProfissionalNaoEncontradaException(profissionalId));
	}

}
