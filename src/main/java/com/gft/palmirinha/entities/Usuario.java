package com.gft.palmirinha.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String senha;
	private boolean isAdmin;
	
	@OneToMany
	private List<Receita> historicoReceitas;

	public Usuario() {
	}

	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
		this.isAdmin = false;
		// this.historicoReceitas = new ArrayList<Receita>();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Receita> getHistoricoReceitas() {
		return historicoReceitas;
	}

	public void setHistoricoReceitas(List<Receita> historicoReceitas) {
		this.historicoReceitas = historicoReceitas;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	 

}
