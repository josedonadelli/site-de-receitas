package com.gft.palmirinha.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.palmirinha.entities.Ingrediente;
import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.entities.Receita;
import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.keys.ItemKey;

@Service
public class PrincipalService {
	Ingrediente ingrediente1;
	Ingrediente ingrediente2;
	Ingrediente ingrediente3;
	Ingrediente ingrediente4;
	Ingrediente ingrediente5;
	Ingrediente ingrediente6;
	Ingrediente ingrediente7;
	Ingrediente ingrediente8;
	Ingrediente ingrediente9;
	Ingrediente ingrediente10;
	Ingrediente ingrediente11;
	Ingrediente ingrediente12;
	Ingrediente ingrediente13;
	Ingrediente ingrediente14;
	Ingrediente ingrediente15;
	
	
	UnidadeMedida unidadeMedida1;
	UnidadeMedida unidadeMedida2;
	UnidadeMedida unidadeMedida3;
	UnidadeMedida unidadeMedida4;

	Receita receita1;
	Receita receita2;
	Receita receita3;

	ItemKey primaryKey1;
	ItemKey primaryKey2;
	ItemKey primaryKey3;

	Item item1;
	Item item2;
	Item item3;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	UnidadeMedidaService unidadeMedidaService;

	@Autowired
	ReceitaService receitaService;

	@Autowired
	ItemService itemService;

	private void cadastraIngrediente() {
		ingrediente1 = new Ingrediente("Manteiga");
		ingrediente2 = new Ingrediente("Sal");
		ingrediente3 = new Ingrediente("Cacau em pó");
		ingrediente4 = new Ingrediente("Açucar");
		ingrediente5 = new Ingrediente("Óleo");
		ingrediente6 = new Ingrediente("Leite");
		ingrediente7 = new Ingrediente("Ovo");
		ingrediente8 = new Ingrediente("Parmesão ralado");
		ingrediente9 = new Ingrediente("Polvilho doce");
		ingrediente10 = new Ingrediente("Pimenta");
		ingrediente11 = new Ingrediente("Dente de alho");//Dente é unidade de medida?
		ingrediente12 = new Ingrediente("Mussarela");
		ingrediente13 = new Ingrediente("Requeijão");
		ingrediente14 = new Ingrediente("Cebolinha");
		ingrediente15 = new Ingrediente("Pão italiano");
		
		ingredienteService.salvarIngrediente(ingrediente1);
		ingredienteService.salvarIngrediente(ingrediente2);
		ingredienteService.salvarIngrediente(ingrediente3);
		ingredienteService.salvarIngrediente(ingrediente4);
		ingredienteService.salvarIngrediente(ingrediente5);
		ingredienteService.salvarIngrediente(ingrediente6);
		ingredienteService.salvarIngrediente(ingrediente7);
		ingredienteService.salvarIngrediente(ingrediente8);
		ingredienteService.salvarIngrediente(ingrediente9);
		ingredienteService.salvarIngrediente(ingrediente10);
		ingredienteService.salvarIngrediente(ingrediente11);
		ingredienteService.salvarIngrediente(ingrediente12);
		ingredienteService.salvarIngrediente(ingrediente13);
		ingredienteService.salvarIngrediente(ingrediente14);
		ingredienteService.salvarIngrediente(ingrediente15);
	}
	
	private void cadastraUnidadeMedida() {
		unidadeMedida1 = new UnidadeMedida("Colher de sopa");
		unidadeMedida2 = new UnidadeMedida("Colher de chá");
		unidadeMedida3 = new UnidadeMedida("Xícara de chá");
		unidadeMedida4 = new UnidadeMedida("Colher de café");
		
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida1);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida2);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida3);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida4);
	}
	
	private void cadastraReceita() {
		receita1 = new Receita();
		receita2 = new Receita();
		receita3 = new Receita();
		
		receita1.setNome("Waffle de pão de queijo");
		receita1.setModoPreparo("Corte o pão ao meio, retire o miolo das duas metades e reserve.\r\n"
				+ "Em um recipiente, adicione a mussarela ralada, o requeijão e misture bem.\r\n"
				+ "Coloque essa mistura nas duas metades do pão, recheando bem.\r\n"
				+ "Corte as metades rodelas, transfira para uma assadeira com papel alumínio e reserve.\r\n"
				+ "Em outro recipiente, coloque a manteiga derretida, a cebolinha, o alho, o parmesão, sal, pimenta e misture bem.\r\n"
				+ "Pincele a mistura por cima das rodelas de pão, cobrindo por completo.\r\n"
				+ "Cubra as rodelas com o papel alumínio e leve ao forno preaquecido a 180º graus por cerca de 25 minutos.\r\n"
				+ "Agora é só servir. Bom apetite.");
		receita1.setPorcoes(6);
		receita1.setTempoPreparo(60);
		
		
		/*
		 * receita2.setNome(""); receita2.setModoPreparo(""); receita2.setPorcoes(0);
		 * receita2.setTempoPreparo(0);
		 * 
		 * receita3.setNome(""); receita3.setModoPreparo(""); receita3.setPorcoes(0);
		 * receita3.setTempoPreparo(0);
		 */
		receitaService.salvarReceita(receita1);
		receita1.getItens().add(cadastraItem(ingrediente6, receita1, unidadeMedida1, 6));
		receita1.getItens().add(cadastraItem(ingrediente1, receita1, unidadeMedida1, 1));
		receita1.getItens().add(cadastraItem(ingrediente2, receita1, unidadeMedida2, 1));
		receita1.getItens().add(cadastraItem(ingrediente8, receita1, unidadeMedida4, 1));
		receita1.getItens().add(cadastraItem(ingrediente9, receita1, unidadeMedida3, 2));
		/*
		 * receitaService.salvarReceita(receita2);
		 * receitaService.salvarReceita(receita3);
		 */
	}
	
	private Item cadastraItem(Ingrediente ingrediente, Receita receita, UnidadeMedida unidadeMedida, int quantidade) {
		ItemKey primaryKey = new ItemKey(ingrediente.getId(), receita.getId());
		Item item = new Item();
		item.setIngrediente(ingrediente);
		item.setReceita(receita);
		item.setPrimaryKey(primaryKey);
		item.setUnidadeMedida(unidadeMedida);
		item.setQuantidade(quantidade);
		return itemService.salvarItem(item);
	}
		//Talvez precise ser Item ao inves de void
	
	public void popular() {
		cadastraIngrediente();
		cadastraUnidadeMedida();
		cadastraReceita();
	}
	
}
