package com.rgv.gerenciamentoapi.controller.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rgv.gerenciamentoapi.controller.model.ProfissionalModel;
import com.rgv.gerenciamentoapi.model.Profissional;

@Component
public class ProfissionalModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public ProfissionalModel toModel(Profissional profissional) {
		return modelMapper.map(profissional, ProfissionalModel.class);
	}
	
	public List<ProfissionalModel> toCollectionModel(Collection<Profissional> profissionais) {
		return profissionais.stream()
				.map(prof -> toModel(prof))
				.collect(Collectors.toList());
	}
	
}
