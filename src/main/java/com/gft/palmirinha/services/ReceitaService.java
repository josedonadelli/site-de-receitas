package com.gft.palmirinha.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.palmirinha.entities.Ingrediente;
import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.entities.Receita;
import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.keys.ItemKey;
import com.gft.palmirinha.repositories.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	ReceitaRepository receitaRepository;

	@Autowired
	UnidadeMedidaService unidadeMedidaService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	ItemService itemService;

	public Receita salvarReceita(Receita receita) {
		return receitaRepository.save(receita);
	}

	public Receita obterReceita(Long id) throws Exception {
		Optional<Receita> receita = receitaRepository.findById(id);

		if (receita.isEmpty()) {
			throw new Exception("Receita não encontrada");
		}

		return receita.get();
	}

	public Set<Receita> listarReceitas(String nome, String ingredienteNome) {
		
		if ( ingredienteNome != null) {
			return Set.copyOf(receitaRepository.findByNomeContainsAndItensIngredienteNomeContains(nome, ingredienteNome));
		}
		
		return Set.copyOf(listarReceitas());

	}
	
	
	public List<Receita> listarReceitas() {
		return receitaRepository.findAll();
	}

	public void excluirReceita(Long id) {
		receitaRepository.deleteById(id);
	}

	public String[] tratarStringItem(String item) throws Exception {
		String[] itensSeparados = item.trim().split(";");
		return itensSeparados;

	}

	public void criaRelacaoItem(Receita receita, String itens) throws Exception {
		Item item;
		Long id = receita.getId();
		String[] itemRows = itens.split("\\r?\\n");

		for (String itemRow : itemRows) {
			String[] itemTratado = tratarStringItem(itemRow);
			item = criaItem(id, itemTratado);
			receita.getItens().add(item);
		}
	}

	private Item criaItem(Long id, String[] itensSeparados) throws Exception {
		Ingrediente ingrediente = ingredienteService.obterIngredientePorNome(itensSeparados[2].trim()).get(0);

		UnidadeMedida unidadeMedida = unidadeMedidaService.obterUnidadeMedidaPorNome(itensSeparados[1].trim()).get(0);

		ItemKey primaryKey = new ItemKey(ingrediente.getId(), id);
		Item item = new Item();
		item.setIngrediente(ingrediente);
		item.setReceita(obterReceita(id));
		item.setQuantidade(Integer.parseInt(itensSeparados[0].trim()));
		item.setUnidadeMedida(unidadeMedida);
		item.setPrimaryKey(primaryKey);
		itemService.salvarItem(item);
		return item;
	}
	
	public boolean verificaBancoVazio() {
		int tamanho = listarReceitas().size() + unidadeMedidaService.listarUnidadesMedida().size() + ingredienteService.listarIngredientes().size() + itemService.listarItens().size();
		if(tamanho > 0)
			return false;
		
		return true;
	}
	
	public String criaStringItens(Set<Item> itens) {
		String stringItens="";
		for(Item item : itens) {
			stringItens += String.valueOf(item.getQuantidade()) + "; " + item.getUnidadeMedida().getNome()+ ";" + item.getIngrediente().getNome() + "\n";
		}
		return stringItens;
	}
	


}
