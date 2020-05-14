package com.rgv.gerenciamentoapi.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rgv.gerenciamentoapi.controller.model.input.EstabelecimentoInput;
import com.rgv.gerenciamentoapi.model.Estabelecimento;

@Component
public class EstabelecimentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Estabelecimento toDomainObject(EstabelecimentoInput estabelecimentoInput) {
		return modelMapper.map(estabelecimentoInput, Estabelecimento.class);
	}

	public void copyToDomainObject(EstabelecimentoInput estabelecimentoInput, Estabelecimento estabelecimento) {
		modelMapper.map(estabelecimentoInput, estabelecimento);
	}

}
