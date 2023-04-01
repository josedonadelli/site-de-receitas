package com.gft.palmirinha.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gft.palmirinha.entities.UnidadeMedida;
import com.gft.palmirinha.repositories.UnidadeMedidaRepository;
import com.gft.palmirinha.services.UnidadeMedidaService;

@ExtendWith(MockitoExtension.class)
public class UnidadeMedidaServiceTest {
	
	private UnidadeMedida unidadeMedida1, unidadeMedida2, unidadeMedida3;
	
	private List<UnidadeMedida> listaUnidadeMedida;
	@Mock
	private UnidadeMedidaRepository unidadeMedidaRepository;
	
	@Mock
	private UnidadeMedida unidadeMedida;
	
	@InjectMocks
	private UnidadeMedidaService unidadeMedidaService;

	@BeforeEach
	void setup() {
		
		unidadeMedida1 = new UnidadeMedida("Colher de chá");
		unidadeMedida2 = new UnidadeMedida("Xícara de café");
		unidadeMedida3 = new UnidadeMedida();
		listaUnidadeMedida = new ArrayList<UnidadeMedida>(); 
	}

	

	/*************************************************************************
	 * Metodo para tratar o throw Exception do metodo .obterUnidadeMedida(); * A
	 * Exception é lançada por conta da utilizaçao do Mockito *
	 *************************************************************************/
	private void tratarExceptionObterUnidadeMedida(Long id) {//
		Assertions.assertThrows(Exception.class, () -> {
			unidadeMedidaService.obterUnidadeMedida(id);
		});
	}

	@Test
	void deveValidarSeAUnidadeMedidaFoiSalvaNoRepositorio() throws Exception {
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
		Mockito.verify(unidadeMedidaRepository).save(unidadeMedida); // Verifica se o metodo save do repositório foi
																		// chamado.

	}

	@Test
	void deveValidarExcluirUnidadeMedidaPorId() throws Exception {
		Long id = unidadeMedida.getId();
		unidadeMedidaService.excluirUnidadeDeMedida(id);

		Mockito.verify(unidadeMedidaRepository).deleteById(id);
	}

	@Test
	void deveVerificarObterUnidadeMedida() throws Exception {
		Long id = unidadeMedida.getId();
		unidadeMedidaService.salvarUnidadeMedida(unidadeMedida);
		tratarExceptionObterUnidadeMedida(id);
		Mockito.verify(unidadeMedidaRepository).findById(id);
	}

	@Test
	void deveReceberExceptionCasoNaoEncontreUnidadeMedidaPeloIdFornecido() throws Exception {
		Long id = unidadeMedida.getId();
		Exception thrown = Assertions.assertThrows(Exception.class, () -> {
			unidadeMedidaService.obterUnidadeMedida(id);
		});
		Mockito.verify(unidadeMedidaRepository).findById(id);
		assertEquals("Unidade de medida não encontrado", thrown.getMessage());
	}

	@Test
	void deveValidarOMetodoListarUnidadesMedida() throws Exception {
		unidadeMedidaService.listarUnidadesMedida();
		Mockito.verify(unidadeMedidaRepository).findAll();
	}
	
	@Test
	void deveRetornarTrueSeUmNomeDaUnidadeMedidaExisteNaLista() throws Exception {
		//listaNomes = new ArrayList<String>();
	//	listaNomes.add("Colher de chá");
		
		listaUnidadeMedida.add(unidadeMedida1);
		listaUnidadeMedida.add(unidadeMedida2);
		unidadeMedida3.setNome("ColheR dE CHÁ"); //metodo nao é case-sensitive
		assertTrue(unidadeMedidaService.verificaExistencia(unidadeMedida3, listaUnidadeMedida));
		
	}
	
	@Test
	void deveRetornarFalseSeONomeDaUnidadeMedidaNaoExistirNaLista() throws Exception {
		
		unidadeMedida3.setNome("Colher de sopa");
		
		listaUnidadeMedida.add(unidadeMedida1);
		listaUnidadeMedida.add(unidadeMedida2);
		assertFalse(unidadeMedidaService.verificaExistencia(unidadeMedida3, listaUnidadeMedida));
		
	}

	
}
