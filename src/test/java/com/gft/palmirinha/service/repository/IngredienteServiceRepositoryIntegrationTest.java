package com.gft.palmirinha.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
import com.gft.palmirinha.services.IngredienteService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class IngredienteServiceRepositoryIntegrationTest {

	private Ingrediente ingrediente1;
	private Ingrediente ingrediente2;
	private Ingrediente ingrediente3;
	@Autowired
	private IngredienteService ingredienteService;
	
	@BeforeAll
	void setup() {
		ingrediente1 = new Ingrediente("Farinha de trigo");
		ingrediente2 = new Ingrediente("Farinha de rosca");
		ingrediente3 = new Ingrediente("Cacau em pó");
		
		ingredienteService.salvarIngrediente(ingrediente1);
		ingredienteService.salvarIngrediente(ingrediente2);
		ingredienteService.salvarIngrediente(ingrediente3);
	}
	
	@AfterAll
	void limpaBD() {
		List<Ingrediente> ingredientes = ingredienteService.listarIngredientes();
		
		for(Ingrediente ingrediente : ingredientes) {
			ingredienteService.excluirIngrediente(ingrediente.getId());
		}
	}
	
	
	@Test
	@Order(1)
	void deveBuscarERetornarObjetoPeloIdFornecido() throws Exception {
		Long id = ingrediente1.getId();
		Ingrediente ingredienteRetorno = ingredienteService.obterIngrediente(id);
		assertAll("Os campos id e nome devem ser iguais",
				() -> assertEquals(ingrediente1.getId(), ingredienteRetorno.getId()),
				() -> assertEquals(ingrediente1.getNome(), ingredienteRetorno.getNome()));
	}
	
	@Test
	@Order(2)
	void deveLancarExceptionCasoNaoEncontreOIdBuscado() throws Exception {
		Exception thrown = Assertions.assertThrows(Exception.class, 
				() -> {ingredienteService.obterIngrediente(0L);
				},"Como não há um id 0, esse teste sempre irá cair na Exception");				
		
		assertEquals("Ingrediente não encontrado", thrown.getMessage());
	}
	
	@Test
	@Order(3)
	void deveRetornarListaComTodosOsIngredientesCadastradosNoBanco() throws Exception {
		List<Ingrediente> listaIngredientes = ingredienteService.listarIngredientes();
		
		assertAll("Deve retornar lista com os dois ingredientes pré inseridos no repositório",
				() -> assertEquals(listaIngredientes.get(0).getId(), ingrediente1.getId()),
				() -> assertEquals(listaIngredientes.get(1).getId(), ingrediente2.getId()));
	}
	
	@Test
	@Order(4)
	void deveSalvarNovoIngredienteNoRepositorio() throws Exception {
		Ingrediente novoIngrediente = new Ingrediente();
		novoIngrediente.setNome("Cacau em pó");
		ingredienteService.salvarIngrediente(novoIngrediente);
		Long id = novoIngrediente.getId();
		assertEquals(novoIngrediente.getNome(), ingredienteService.obterIngrediente(id).getNome());
	}
	
	@Test
	@Order(5)
	void deveObterIngredientePorNome() throws Exception {
		String teste = "Cacau em pó";
		List<Ingrediente> ingredienteNome = ingredienteService.obterIngredientePorNome(teste);
		System.out.println(ingredienteNome.get(0).getNome() + ingredienteNome.get(0).getId());
		assertFalse(ingredienteNome.isEmpty());
	}
	@Test
	@Order(6)
	void deveExcluirUmIngredientePeloId() throws Exception {
		int tamanhoEsperado = ingredienteService.listarIngredientes().size() - 1;
		
		ingredienteService.excluirIngrediente(ingrediente1.getId());
		assertEquals(tamanhoEsperado, ingredienteService.listarIngredientes().size());
		
	}
	
	
}
