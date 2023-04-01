package com.gft.palmirinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.palmirinha.entities.Ingrediente;
import com.gft.palmirinha.repositories.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired
	IngredienteRepository ingredienteRepository;
	
	public Ingrediente salvarIngrediente(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}
	
	public Ingrediente obterIngrediente(Long id) throws Exception{
		Optional<Ingrediente> ingrediente = ingredienteRepository.findById(id);
		
		if(ingrediente.isEmpty())
			throw new Exception("Ingrediente não encontrado");
		
		return ingrediente.get();
	}


	public List<Ingrediente> listarIngredientes(String nome){
		if(nome!=null)
			return ingredienteRepository.findByNomeContains(nome);
		
		return listarIngredientes();
	}
	public List<Ingrediente> listarIngredientes(){
		return ingredienteRepository.findAll();
	}
	
	public void excluirIngrediente(Long id) {
		ingredienteRepository.deleteById(id);
	}
	
	public List<Ingrediente> obterIngredientePorNome(String nome) throws Exception{
		if(nome != null)
			return ingredienteRepository.findByNomeContains(nome);
		
		
		
		return listarIngredientes();
	}
	
}
