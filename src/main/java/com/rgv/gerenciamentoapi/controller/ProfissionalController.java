package com.rgv.gerenciamentoapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rgv.gerenciamentoapi.controller.assembler.EstabelecimentoInputDisassembler;
import com.rgv.gerenciamentoapi.controller.assembler.EstabelecimentoModelAssembler;
import com.rgv.gerenciamentoapi.controller.model.EstabelecimentoModel;
import com.rgv.gerenciamentoapi.controller.model.input.EstabelecimentoInput;
import com.rgv.gerenciamentoapi.model.Estabelecimento;
import com.rgv.gerenciamentoapi.repository.EstabelecimentoRepository;
import com.rgv.gerenciamentoapi.service.EstabelecimentoService;

@RestController
@RequestMapping("/estabelecimentos")
public class ProfissionalController {

	@Autowired
	private EstabelecimentoRepository estabelecimentoRepository;

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	private EstabelecimentoModelAssembler estabelecimentoModelAssembler;

	@Autowired
	private EstabelecimentoInputDisassembler estabelecimentoInputDisassembler;

	@GetMapping
	public List<EstabelecimentoModel> listar() {
		return estabelecimentoModelAssembler.toCollectionModel(estabelecimentoRepository.findAll());
	}

	@GetMapping("/{estabelecimentoId}")
	public EstabelecimentoModel buscar(@PathVariable Long estabelecimentoId) {
		return estabelecimentoModelAssembler.toModel(estabelecimentoService.getOrFail(estabelecimentoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstabelecimentoModel adicionar(@RequestBody @Valid EstabelecimentoInput estabelecimentoInput) {
		Estabelecimento estabelecimento = estabelecimentoInputDisassembler.toDomainObject(estabelecimentoInput);
		return estabelecimentoModelAssembler.toModel(estabelecimentoService.salvar(estabelecimento));
	}

	@PutMapping("/{estabelecimentoId}")
	public EstabelecimentoModel atualizar(@PathVariable Long estabelecimentoId,
			@RequestBody @Valid EstabelecimentoInput estabelecimentoInput) {
		Estabelecimento estabelecimentoAtual = estabelecimentoService.getOrFail(estabelecimentoId);
		estabelecimentoInputDisassembler.copyToDomainObject(estabelecimentoInput, estabelecimentoAtual);
		return estabelecimentoModelAssembler.toModel(estabelecimentoService.salvar(estabelecimentoAtual));
	}

	@DeleteMapping("/{estabelecimentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estabelecimentoId) {
		estabelecimentoService.excluir(estabelecimentoId);
	}

}
