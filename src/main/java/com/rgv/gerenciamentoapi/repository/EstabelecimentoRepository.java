package com.rgv.gerenciamentoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rgv.gerenciamentoapi.model.Estabelecimento;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {
	
	@Query(value = "select count(1) as total from tb_estab_prof t where t.prof_id = :profissionalId", nativeQuery = true)
	Long findTotalProfissional(Long profissionalId);

}
