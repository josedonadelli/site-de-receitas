package com.gft.palmirinha.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.palmirinha.entities.Ingrediente;
import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.keys.ItemKey;

@Repository
public interface ItemRepository extends JpaRepository<Item, ItemKey>{
	List<Item> findByIngredienteContains(Ingrediente ingrediente);
}
