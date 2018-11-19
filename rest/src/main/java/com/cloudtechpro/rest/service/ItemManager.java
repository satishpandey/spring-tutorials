package com.cloudtechpro.rest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloudtechpro.rest.beans.Item;

/**
 * Item manager service
 * 
 * @author satish
 *
 */
@Service
public class ItemManager {

	Logger logger = LoggerFactory.getLogger(ItemManager.class);

	private Map<Long, Item> items;

	public ItemManager() {
		items = new HashMap<>();
	}

	public void addItem(long code, String name) {
		items.put(code, new Item(code, name));
	}

	public void deleteItem(long code) {
		items.remove(code);
	}

	public List<Item> getItems() {
		return new ArrayList<>(items.values());
	}

	public Item getItem(Long code) {
		return items.get(code);
	}
}
