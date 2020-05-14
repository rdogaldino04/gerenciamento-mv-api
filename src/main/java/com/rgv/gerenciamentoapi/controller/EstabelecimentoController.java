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

import com.rgv.gerenciamentoapi.controller.assembler.ProfissionalInputDisassembler;
import com.rgv.gerenciamentoapi.controller.assembler.ProfissionalModelAssembler;
import com.rgv.gerenciamentoapi.controller.model.ProfissionalModel;
import com.rgv.gerenciamentoapi.controller.model.input.ProfissionalInput;
import com.rgv.gerenciamentoapi.model.Profissional;
import com.rgv.gerenciamentoapi.repository.ProfissionalRepository;
import com.rgv.gerenciamentoapi.service.ProfissionalService;

@RestController
@RequestMapping("/profissionais")
public class EstabelecimentoController {

	@Autowired
	private ProfissionalRepository profissionalRepository;

	@Autowired
	private ProfissionalService profissionalService;

	@Autowired
	private ProfissionalModelAssembler profissionalModelAssembler;

	@Autowired
	private ProfissionalInputDisassembler profissionalInputDisassembler;

	@GetMapping
	public List<ProfissionalModel> listar() {
		return profissionalModelAssembler.toCollectionModel(profissionalRepository.findAll());
	}

	@GetMapping("/{profissionalId}")
	public ProfissionalModel buscar(@PathVariable Long profissionalId) {
		return profissionalModelAssembler.toModel(profissionalService.getOrFail(profissionalId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProfissionalModel adicionar(@RequestBody @Valid ProfissionalInput profissionalInput) {
		Profissional profissional = profissionalInputDisassembler.toDomainObject(profissionalInput);
		return profissionalModelAssembler.toModel(profissionalService.salvar(profissional));
	}

	@PutMapping("/{profissionalId}")
	public ProfissionalModel atualizar(@PathVariable Long profissionalId,
			@RequestBody @Valid ProfissionalInput profissionalInput) {
		Profissional profissionalAtual = profissionalService.getOrFail(profissionalId);
		profissionalInputDisassembler.copyToDomainObject(profissionalInput, profissionalAtual);
		return profissionalModelAssembler.toModel(profissionalService.salvar(profissionalAtual));
	}

	@DeleteMapping("/{profissionalId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long profissionalId) {
		profissionalService.excluir(profissionalId);
	}

}
