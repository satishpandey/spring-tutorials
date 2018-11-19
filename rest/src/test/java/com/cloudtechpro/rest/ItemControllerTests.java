package com.cloudtechpro.rest;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cloudtechpro.rest.beans.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testing item controller REST APIs.
 * 
 * @author satish
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemControllerTests {

	Logger logger = LoggerFactory.getLogger(ItemControllerTests.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	private Item items[] = { new Item(1, "Item 1"), new Item(2, "Item 2") };

	@Test
	public void aSaveItems() throws Exception {
		logger.info("Saving items...");
		for (Item item : items) {
			this.saveItem(item);
		}
	}

	@Test
	public void bFetchItems() throws Exception {
		logger.info("Fetching items...");
		this.fetchItems();
	}

	@Test
	public void cFetchItem() throws Exception {
		logger.info("Fetch item...");
		for (Item item : items) {
			this.fetchItem(item);
		}
	}

	@Test
	public void dDeleteItems() throws Exception {
		logger.info("Deleting items...");
		for (Item item : items) {
			this.deleteItem(item);
		}
	}

	public void fetchItems() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getItems").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().toString());
		String expected = objectMapper.writeValueAsString(items);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	public void fetchItem(Item item) throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(String.format("/getItem/%d", item.getCode()))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().toString());
		String expected = objectMapper.writeValueAsString(item);

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	private void saveItem(Item item) throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveItem").accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(item)).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().toString());
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		Item responseItem = objectMapper.readValue(response.getContentAsByteArray(), Item.class);

		assertEquals(responseItem.toString(), item.toString());

	}

	private void deleteItem(Item item) throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(String.format("/deleteItem/%d", item.getCode()))
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		logger.info(result.getResponse().toString());
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		Long code = objectMapper.readValue(response.getContentAsByteArray(), Long.class);

		assertEquals("" + code, "" + item.getCode());

	}
}
