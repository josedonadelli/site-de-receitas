package com.gft.palmirinha.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Receita implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "receita", fetch = FetchType.EAGER)
	private Set<Item> itens = new HashSet<>();
	
	@Lob
	private String modoPreparo;

	private int tempoPreparo;
	private int porcoes;

	public Receita() {
	}

	public Receita(String nome, Set<Item> itens, String modoPreparo,
			int tempoPreparo, int porcoes) {
		this.nome = nome;
		this.itens = itens;
		this.modoPreparo = modoPreparo;
		this.tempoPreparo = tempoPreparo;
		this.porcoes = porcoes;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTempoPreparo() {
		return tempoPreparo;
	}

	public void setTempoPreparo(int tempoPreparo) {
		this.tempoPreparo = tempoPreparo;
	}

	public int getPorcoes() {
		return porcoes;
	}

	public void setPorcoes(int porcoes) {
		this.porcoes = porcoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getModoPreparo() {
		return modoPreparo;
	}

	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}

	public Set<Item> getItens() {
		return itens;
	}

	public void setItens(Set<Item> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itens, modoPreparo, nome, porcoes, tempoPreparo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receita other = (Receita) obj;
		return Objects.equals(id, other.id) && Objects.equals(itens, other.itens)
				&& Objects.equals(modoPreparo, other.modoPreparo) && Objects.equals(nome, other.nome)
				&& porcoes == other.porcoes && tempoPreparo == other.tempoPreparo;
	}





}
