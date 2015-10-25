package org.kerwyn.game.service;

import org.kerwyn.game.entities.Crew;
import org.kerwyn.game.entities.Human;
import org.kerwyn.game.entities.Item;
import org.kerwyn.game.entities.Location;
import org.kerwyn.game.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item create(String name, int quantity, Location loc, Crew ownership,
			Human human, boolean equiped) {
		Item item = new Item(name, quantity, loc);
		if (ownership != null) { item.setOwnership(ownership); }
		if (human != null) { item.setHuman(human); }
		if (equiped) { item.setIsEquipped(true); }
		itemRepository.save(item);
		return item;
	}

	@Override
	public Item findFoodInInventory(Human human) {
		for (Item item : human.getInventory()){
			if (item.getName().equals("Food")){
				return item;
			}
		}
		return null;
	}

	@Override
	public void delete(Item item) {
		itemRepository.delete(item.getId());

	}

}
