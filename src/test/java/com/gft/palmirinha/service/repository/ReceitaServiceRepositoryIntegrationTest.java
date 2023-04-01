package com.gft.palmirinha.service.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
public class ReceitaServiceRepositoryIntegrationTest {

	private UnidadeMedida unidadeMedida1;
	private UnidadeMedida unidadeMedida2;
	private UnidadeMedida unidadeMedida3;

	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	private Ingrediente ingrediente3;

	private ItemKey itemKey;
	private Item item;

	private Receita receita;

	private int tamanhoEsperado;

	@Autowired
	UnidadeMedidaService unidadeMedidaService;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	ItemService itemService;

	@Autowired
	ReceitaService receitaService;

	@BeforeAll
	void setup() {
		unidadeMedida1 = new UnidadeMedida("Colher de chá");
		unidadeMedida2 = new UnidadeMedida("Xícara de café");
		unidadeMedida3 = new UnidadeMedida("Colher de sopa");

		ingrediente1 = new Ingrediente("Bicarbonato de sódio");
		ingrediente2 = new Ingrediente("Farinha de trigo");
		ingrediente3 = new Ingrediente("Cacau em pó");

		receita = new Receita();
		receita.setModoPreparo("Teste Preparo");
		receita.setNome("Receita Teste");
		receita.setPorcoes(2);
		receita.setTempoPreparo(40);
		receitaService.salvarReceita(receita);

		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida1);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida2);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida3);

		ingredienteService.salvarIngrediente(ingrediente1);
		ingredienteService.salvarIngrediente(ingrediente2);
		ingredienteService.salvarIngrediente(ingrediente3);
	}

	@BeforeEach
	void tamanhoLista() {
		tamanhoEsperado = receitaService.listarReceitas().size();
	}

	@Test
	@Order(1)
	void deveBuscarReceitaPeloId() throws Exception {
		Long id = receita.getId();
		Receita buscaReceita = receitaService.obterReceita(id);

		assertEquals(receita.getNome(), buscaReceita.getNome());
	}

	@Test
	@Order(2)
	void deveLancarExceptionCasoNaoEncontreIdBuscado() throws Exception {
		Exception thrown = Assertions.assertThrows(Exception.class, () -> {
			receitaService.obterReceita(0L);
		}, "Como não há um id 0, esse teste sempre irá cair na Exception");

		assertEquals("Receita não encontrada", thrown.getMessage());
	}

	@Test
	@Order(3)
	void deveRetonrnarListaComTodasAsReceitascadastradas() throws Exception {
		List<Receita> receitas = receitaService.listarReceitas();

		assertEquals(receita.getId(), receitas.get(0).getId());
	}

	@Test
	@Order(4)
	void deveSalvarNovaReceita() throws Exception {
		tamanhoEsperado += 1;
		Receita novaReceita = new Receita();
		novaReceita.setModoPreparo("Misturar tudo e levar ao forno");
		novaReceita.setNome("Receita Taz");
		novaReceita.setPorcoes(1);
		novaReceita.setTempoPreparo(20);
		receitaService.salvarReceita(novaReceita);
		assertEquals(tamanhoEsperado, receitaService.listarReceitas().size());
	}
	
	@Test
	@Order(5)
	void deveTratarStringParaSepararQuantidadeIngredienteEUnidadeMedida() throws Exception {
		Receita receita1 = new Receita();
		String itens = "    03 ;    Xícara de café ;Cacau em pó";
		
		int qtdEsperada = 3;	
		receita1.setNome("Receita 1 teste com relacao a 1 item");
		receita1.setModoPreparo("Teste");
		receita1.setPorcoes(3);
		receita1.setTempoPreparo(26);
		receitaService.salvarReceita(receita1);

		receitaService.criaRelacaoItem(receita1, itens);
		System.out.println("Tamanho do Set Itens" + receita1.getItens().size());
	}

	@Test
	@Order(6)
	void deveExcluirReceitaPeloId() throws Exception {
		tamanhoEsperado -= 1;
		Long id = receita.getId();
		receitaService.excluirReceita(id);
		
		assertAll("Deve verificar se o Id do único elemento restante é diferente do Id que excluímos", 
				() -> assertNotEquals(id, receitaService.listarReceitas().get(0).getId()),
				() -> assertEquals(tamanhoEsperado, receitaService.listarReceitas().size()));
	}


}
