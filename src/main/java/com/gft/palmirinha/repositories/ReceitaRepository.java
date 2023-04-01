package com.gft.palmirinha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.palmirinha.entities.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
	List<Receita> findByNomeContainsAndItensIngredienteNomeContains(String nome, String ingredienteNome);
	//List<Receita> findByItensIngredienteNomeContains(String ingredienteNome);
	
	
	
}
