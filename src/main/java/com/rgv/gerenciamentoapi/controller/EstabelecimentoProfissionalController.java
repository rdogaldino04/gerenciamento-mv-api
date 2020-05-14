package com.rgv.gerenciamentoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rgv.gerenciamentoapi.controller.assembler.ProfissionalModelAssembler;
import com.rgv.gerenciamentoapi.controller.model.ProfissionalModel;
import com.rgv.gerenciamentoapi.model.Estabelecimento;
import com.rgv.gerenciamentoapi.service.EstabelecimentoService;

@RestController
@RequestMapping("/estabelecimento/{estabelecimentoId}/profissionais")
public class EstabelecimentoProfissionalController {
	
	@Autowired
	private EstabelecimentoService estabelecimentoService;
	
	@Autowired
	private ProfissionalModelAssembler profissionalModelAssembler;
	
	@GetMapping
	public List<ProfissionalModel> listar(@PathVariable Long estabelecimentoId) {
		Estabelecimento estabelecimento = estabelecimentoService.getOrFail(estabelecimentoId);
		return profissionalModelAssembler.toCollectionModel(estabelecimento.getProfissionais());
	}
	
	@DeleteMapping("/{profissionalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long estabelecimentoId, @PathVariable Long profissionalId) {
		estabelecimentoService.desassociarPermissao(estabelecimentoId, profissionalId);
	}
	
	@PutMapping("/{profissionalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long estabelecimentoId, @PathVariable Long profissionalId) {
		estabelecimentoService.associarPermissao(estabelecimentoId, profissionalId);
	}
	
}
