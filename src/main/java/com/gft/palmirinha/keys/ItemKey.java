package com.gft.palmirinha.keys;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ItemKey implements Serializable{

	@Column(name = "ingrediente_id")
	Long ingredienteId;
	
	@Column(name = "receita_id")
	Long receitaId;
	

	public ItemKey() {

	}


	public ItemKey(Long ingredienteId, Long receitaId) {

		this.ingredienteId = ingredienteId;
		this.receitaId = receitaId;
	}


	public Long getIngredienteId() {
		return ingredienteId;
	}


	public void setIngredienteId(Long ingredienteId) {
		this.ingredienteId = ingredienteId;
	}


	public Long getReceitaId() {
		return receitaId;
	}


	public void setReceitaId(Long receitaId) {
		this.receitaId = receitaId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(ingredienteId, receitaId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemKey other = (ItemKey) obj;
		return Objects.equals(ingredienteId, other.ingredienteId) && Objects.equals(receitaId, other.receitaId);
	}




	

	
}
