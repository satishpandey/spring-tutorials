package com.cloudtechpro.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cloudtechpro.rest.beans.Item;
import com.cloudtechpro.rest.exception.BadRequestException;
import com.cloudtechpro.rest.exception.ResourceNotFoundException;
import com.cloudtechpro.rest.service.ItemManager;

/**
 * Item Controller REST APIs
 * 
 * @author satish
 *
 */
@RestController
public class ItemController {

	Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemManager itemManager;

	@GetMapping("/getItems")
	public List<Item> getItems() {
		return itemManager.getItems();
	}

	@GetMapping("/getItem/{code}")
	public Item getItem(@PathVariable Long code) {
		Item item = itemManager.getItem(code);
		if (item == null)
			throw new ResourceNotFoundException("" + code, "Item not found in database");
		return item;
	}

	@PostMapping("/saveItem")
	public Item saveItem(@RequestBody Item item) {
		if (item.getCode() < 1 || item.getName() == null || item.getName().isEmpty())
			throw new BadRequestException("Check for a valid code and item name in the request body");
		itemManager.addItem(item.getCode(), item.getName());
		return item;
	}

	@DeleteMapping("/deleteItem/{code}")
	public Long deleteItem(@PathVariable Long code) {
		if (code < 1)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item code can't be a negative number");
		if (itemManager.getItem(code) == null)
			throw new ResourceNotFoundException("" + code, "Item not found");
		itemManager.deleteItem(code);
		return code;
	}
}
