package com.cloudtechpro.rest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudtechpro.rest.beans.Item;
import com.cloudtechpro.rest.service.ItemManager;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testing item manager service with mock instances.
 * 
 * @author satish
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemManagerTests {

	private Logger logger = LoggerFactory.getLogger(ItemManagerTests.class);

	private Item mockItems[] = { new Item(1, "Mock Item 1"), new Item(2, "Mock Item 2") };

	@InjectMocks
	private ItemManager itemManager;

	@InjectMocks
	ObjectMapper objectMapper;

	@Test
	public void testItemsAPIs() throws Exception {
		for (Item mockItem : mockItems) {
			this.saveItem(mockItem);
		}
		this.fetchItems();
		for (Item mockItem : mockItems) {
			this.fetchItem(mockItem);
		}
		for (Item mockItem : mockItems) {
			this.deleteItem(mockItem);
		}
	}

	public void fetchItems() throws Exception {
		List<Item> items = itemManager.getItems();

		logger.info(items.toString());
		String expected = objectMapper.writeValueAsString(mockItems);

		JSONAssert.assertEquals(expected, objectMapper.writeValueAsString(items), false);
	}

	public void fetchItem(Item mockItem) throws Exception {

		Item item = itemManager.getItem(mockItem.getCode());

		logger.info(item.toString());
		String expected = objectMapper.writeValueAsString(mockItem);

		JSONAssert.assertEquals(expected, objectMapper.writeValueAsString(item), false);
	}

	private void saveItem(Item mockItem) throws Exception {

		itemManager.addItem(mockItem.getCode(), mockItem.getName());

		Item item = itemManager.getItem(mockItem.getCode());

		assertNotNull(item);
	}

	private void deleteItem(Item mockItem) throws Exception {

		itemManager.deleteItem(mockItem.getCode());

		Item item = itemManager.getItem(mockItem.getCode());

		assertNull(item);

	}
}
