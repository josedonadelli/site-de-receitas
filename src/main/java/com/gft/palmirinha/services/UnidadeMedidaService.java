package com.gft.palmirinha.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.repositories.UnidadeMedidaRepository;

@Service
public class UnidadeMedidaService {
	@Autowired
	private UnidadeMedidaRepository unidadeMedidaRepository;

	public UnidadeMedida salvarUnidadeMedida(UnidadeMedida unidadeMedida) {
		return unidadeMedidaRepository.save(unidadeMedida);
	}

	public List<UnidadeMedida> listarUnidadesMedida(String nome) {
		if (nome != null)
			return unidadeMedidaRepository.findByNomeContains(nome);
		
		return listarUnidadesMedida();
	}

	public List<UnidadeMedida> listarUnidadesMedida() {
		return unidadeMedidaRepository.findAll();
	}

	public UnidadeMedida obterUnidadeMedida(Long id) throws Exception {
		Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
		if (unidadeMedida.isEmpty()) {
			throw new Exception("Unidade de medida não encontrada");
		}
		return unidadeMedida.get();
	}

	public List<UnidadeMedida> obterUnidadeMedidaPorNome(String nome) throws Exception {

		if (nome != null) {
			return unidadeMedidaRepository.findByNomeContains(nome);
		}
		return listarUnidadesMedida();
	}

	public void excluirUnidadeDeMedida(Long id) {

		unidadeMedidaRepository.deleteById(id);
	}

	// Para ser usado no Controller, para verificar se a unidade de medida com Id
	// null, já não tem um nome equivalente cadastrado com Id no banco...
	public boolean verificaExistencia(UnidadeMedida unidadeMedida, List<UnidadeMedida> lista) {
		List<String> listaString = new ArrayList<String>();
		String minusculo = unidadeMedida.getNome().toLowerCase();
		for (UnidadeMedida um : lista) {

			listaString.add(um.getNome().toLowerCase());
		}

		return listaString.contains(minusculo);

	}

}
