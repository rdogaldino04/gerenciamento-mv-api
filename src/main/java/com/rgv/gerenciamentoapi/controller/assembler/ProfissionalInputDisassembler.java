package com.rgv.gerenciamentoapi.controller.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rgv.gerenciamentoapi.controller.model.input.ProfissionalInput;
import com.rgv.gerenciamentoapi.model.Profissional;

@Component
public class ProfissionalInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Profissional toDomainObject(ProfissionalInput profissionalInput) {
		return modelMapper.map(profissionalInput, Profissional.class);
	}

	public void copyToDomainObject(ProfissionalInput profissionalInput, Profissional profissional) {
		modelMapper.map(profissionalInput, profissional);
	}

}
