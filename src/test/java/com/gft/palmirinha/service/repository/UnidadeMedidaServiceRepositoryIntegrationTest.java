package com.gft.palmirinha.service.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.services.UnidadeMedidaService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class UnidadeMedidaServiceRepositoryIntegrationTest {
	private List<UnidadeMedida> lista;
	private UnidadeMedida unidadeMedida1;
	private UnidadeMedida unidadeMedida2;
	
	@Autowired
	private UnidadeMedidaService unidadeMedidaService;

	@BeforeAll
	void setup() {
		lista = new ArrayList<UnidadeMedida>();
		unidadeMedida1 = new UnidadeMedida("Colher de chá");
		unidadeMedida2 = new UnidadeMedida("Colher de sopa");
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida1);
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida2);
		
	}

	@AfterAll
	void limpaBd() {
		lista = unidadeMedidaService.listarUnidadesMedida();
		for(UnidadeMedida x : lista)
			unidadeMedidaService.excluirUnidadeDeMedida(x.getId());
	}
	@Test
	@Order(1)
	void deveRetornarListaComTodasAsUnidadesDeMedidasCadastradasNoBancoDeDados() throws Exception {

		 List<UnidadeMedida> teste = unidadeMedidaService.listarUnidadesMedida();
		 assertAll("Deve retornar uma lista com os dois objetos existentes no bd",
				 () -> assertEquals(unidadeMedida1.getId(), teste.get(0).getId()),
				 () -> assertEquals(unidadeMedida2.getId(), teste.get(1).getId()));
		
	}

	
	@Test
	@Order(2)
	void deveBuscarUnidadeMedidaPorId() throws Exception {
		Long id = unidadeMedida1.getId();
		
		assertEquals(unidadeMedida1.getNome(), unidadeMedidaService.obterUnidadeMedida(id).getNome());
	}
	
	@Test
	@Order(3)
	void deveSalvarNovaUnidadeMedidaNoRepositorio() throws Exception {
		UnidadeMedida novaUnidadeMedida = new UnidadeMedida();
		
		novaUnidadeMedida.setNome("Xícara de café");
		unidadeMedidaService.salvarUnidadeMedida(novaUnidadeMedida);
		Long id = novaUnidadeMedida.getId();
		
		assertEquals(novaUnidadeMedida.getNome(), unidadeMedidaService.obterUnidadeMedida(id).getNome());
		
	}
	@Test
	@Order(4)
	void deveLancarExceptionCasoNaoEncontreOIdBuscado() throws Exception {
		Exception thrown = Assertions.assertThrows(Exception.class, 
				() -> {unidadeMedidaService.obterUnidadeMedida(0L);
				},"Como não há um id 0, esse teste sempre irá cair na Exception");
		
		assertEquals("Unidade de medida não encontrada", thrown.getMessage());
	}
	@Test
	@Order(5)
	void deveExcluirUnidadeMedidaPeloId() throws Exception {
		lista = unidadeMedidaService.listarUnidadesMedida();
		List<UnidadeMedida> novaLista;
		int tamanhoEsperado = lista.size() - 1;
		Long id = lista.get(0).getId();
		
		unidadeMedidaService.excluirUnidadeDeMedida(id);
		novaLista = unidadeMedidaService.listarUnidadesMedida();
		assertAll("A lista deve estar um elemento menor, e o Id do primeiro elemento da nova lista TEM que ser diferente do Id do elemento da lista anterior",
				() -> assertEquals(tamanhoEsperado, novaLista.size()),
				() -> assertNotEquals(id, novaLista.get(0).getId()));

	}
}
