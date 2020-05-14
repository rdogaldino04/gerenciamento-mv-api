package com.rgv.gerenciamentoapi.controller.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rgv.gerenciamentoapi.controller.model.EstabelecimentoModel;
import com.rgv.gerenciamentoapi.model.Estabelecimento;

@Component
public class EstabelecimentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstabelecimentoModel toModel(Estabelecimento estabelecimento) {
		return modelMapper.map(estabelecimento, EstabelecimentoModel.class);
	}
	
	public List<EstabelecimentoModel> toCollectionModel(List<Estabelecimento> estabelecimentos) {
		return estabelecimentos.stream()
				.map(estab -> toModel(estab))
				.collect(Collectors.toList());
	}
	
}
