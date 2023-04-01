package com.gft.palmirinha.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import com.gft.palmirinha.entities.Usuario;

public class UsuarioServiceTest {
	private Usuario usuario;
	@BeforeEach
	public void setup() {
		usuario = new Usuario("Joao Pedro", "jpedro@gmail.com");
	}
	@Test
	@Description("Deve criar um usuario comum. Sem privilegios de administrador")
	void deveCriarUsuarioSemPrivilegioAdmin() throws Exception {
		assertEquals(false, usuario.isAdmin());
	}
	
	@Test
	@Description("Deve permitir setar um usuario como administrador")
	void devePermitirSetarUsuarioComoAdmin() throws Exception {
		usuario.setAdmin(true);
		assertEquals(true, usuario.isAdmin());
	}
	
}
