package com.gft.palmirinha.service.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gft.palmirinha.entities.Ingrediente;
import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.entities.Receita;
import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.keys.ItemKey;
import com.gft.palmirinha.services.IngredienteService;
import com.gft.palmirinha.services.ItemService;
import com.gft.palmirinha.services.ReceitaService;
import com.gft.palmirinha.services.UnidadeMedidaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class ItemServiceRepositoryIntegrationTest {

	private Item item;
	private ItemKey itemKey;
	private Ingrediente ingrediente;
	private Receita receita;
	private UnidadeMedida unidadeMedida;
	int tamanhoEsperado;

	@Autowired
	private ItemService itemService;

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired 
	private UnidadeMedidaService unidadeMedidaService;

	@BeforeAll
	void setup() {
		tamanhoEsperado = itemService.listarItens().size();
		receita = new Receita();
		ingrediente = new Ingrediente();
		unidadeMedida = new UnidadeMedida();
		
		receita.setNome("Receita Teste");
		receita.setModoPreparo("Teste Preparo");
		receita.setPorcoes(2);
		receita.setTempoPreparo(40);
		receitaService.salvarReceita(receita);
		
		ingrediente.setNome("Farinha de trigo");
		ingredienteService.salvarIngrediente(ingrediente);
		
		unidadeMedida.setNome("Colher de chá");
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
		
	}

	@Test
	@Order(1)
	void deveSalvarItemNoRepositório() throws Exception {
		tamanhoEsperado = tamanhoEsperado + 1;
		itemKey = new ItemKey(ingrediente.getId(), receita.getId());
		item = new Item();
		item.setIngrediente(ingrediente);
		item.setReceita(receita);
		item.setQuantidade(4);
		item.setUnidadeMedida(unidadeMedida);
		item.setPrimaryKey(itemKey);
		itemService.salvarItem(item);
		assertEquals(tamanhoEsperado, itemService.listarItens().size());
	}

	@Test
	@Order(2)
	void deveEncontrarItemPeloId() throws Exception {
		itemKey = new ItemKey(ingrediente.getId(), receita.getId());
		Item buscaItem = itemService.obterItem(itemKey);
		assertAll("Os ids de receita e ingrediente devem ser iguais aos Ids de seus correspondentes em item",
				() -> assertEquals(receita.getId(), buscaItem.getPrimaryKey().getReceitaId()),
				() -> assertEquals(ingrediente.getId(), buscaItem.getPrimaryKey().getIngredienteId()));
	}

	@Test
	@Order(3)
	void deveLancarExcessaoCasoNaoEncontreItemPeloIdBuscado() throws Exception {
		ItemKey id = new ItemKey(0L,0L);
		Exception thrown = Assertions.assertThrows(Exception.class, 
				() -> {itemService.obterItem(id);
				},"Como não há um id 0, esse teste sempre irá cair na Exception");
		
		assertEquals("Item não encontrado", thrown.getMessage());
	}

	@Test
	@Order(4)
	void deveListarItemsCadastrados() throws Exception {
		List<Item> itens = itemService.listarItens();

		assertEquals(itens.get(0).getPrimaryKey().getIngredienteId(), ingrediente.getId());
	}
	
	@Disabled
	@Test
	@Order(5)
	void deveExcluirItem() throws Exception {
		tamanhoEsperado = tamanhoEsperado - 1;
		itemKey = new ItemKey(ingrediente.getId(), receita.getId());

		itemService.excluirItem(itemKey);
		assertEquals(tamanhoEsperado, itemService.listarItens().size());
	}

}
