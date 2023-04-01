package com.gft.palmirinha.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.palmirinha.entities.Item;
import com.gft.palmirinha.keys.ItemKey;
import com.gft.palmirinha.repositories.ItemRepository;

@Service
public class ItemService {
	@Autowired
	ItemRepository itemRepository;

	public Item salvarItem(Item item) {
		return itemRepository.save(item);
	}

	public Item obterItem(ItemKey id) throws Exception {
		Optional<Item> item = itemRepository.findById(id);
		if (item.isEmpty())
			throw new Exception("Item não encontrado");

		return item.get();
	}

	public List<Item> listarItens() {
		return itemRepository.findAll();
	}

	public void excluirItem(ItemKey id) {
		itemRepository.deleteById(id);
	}
}
