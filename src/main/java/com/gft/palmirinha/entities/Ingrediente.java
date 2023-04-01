package com.gft.palmirinha.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Ingrediente implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String nome;

	@OneToMany(mappedBy = "ingrediente")
	private Set<Item> itens;

	public Ingrediente() {
	}

	public Ingrediente(String nome) {
		this.nome = nome;
		// this.unidadeMedida = unidadeMedida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, itens, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(itens, other.itens) && Objects.equals(nome, other.nome);
	}
	
	

}
