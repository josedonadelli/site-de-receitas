package com.gft.palmirinha.entities;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.gft.palmirinha.keys.ItemKey;
@SuppressWarnings("serial")
@Entity
public class Item implements Serializable {
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "ingredienteId", column = @Column(name = "INGREDIENTE_ID")),
			@AttributeOverride(name = "unidadeMedidaId", column = @Column(name = "UNIDADE_MEDIDA_ID")) })
	ItemKey primaryKey;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("ingredienteId")
	@JoinColumn(name = "ingrediente_id")
	Ingrediente ingrediente;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("receitaId")
	@JoinColumn(name = "receita_id")
	Receita receita;

	private int quantidade;
	private UnidadeMedida unidadeMedida;

	public Item() {
	}

	public Item(ItemKey primaryKey, Ingrediente ingrediente, Receita receita, int quantidade,
			UnidadeMedida unidadeMedida) {
		this.primaryKey = primaryKey;
		this.ingrediente = ingrediente;
		this.receita = receita;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Ingrediente getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public ItemKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(ItemKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


}
