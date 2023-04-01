package com.gft.palmirinha.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.entities.Receita;
import com.gft.palmirinha.services.ReceitaService;

public class ReceitaServiceTest {
	private Item item;
	

	private ReceitaService receitaService = new ReceitaService();
	@Test
	void deveTratarStringParaSepararQuantidadeIngredienteEUnidadeMedida() throws Exception {
		String itens = "    03 ;    xícara de café ;Farinha";
			
		String[] itensSerparados = receitaService.tratarStringItem(itens);
		
		assertAll("Verificar se os campos foram separados",
				() -> assertEquals("03", itensSerparados[0].trim()),
				() -> assertEquals("xícara de café", itensSerparados[1].trim()),
				() -> assertEquals("Farinha", itensSerparados[2].trim()));
		
	}
}
