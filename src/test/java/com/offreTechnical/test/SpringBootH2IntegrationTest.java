package com.offreTechnical.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offretechnical.test.SpringBootApplicationRunner;
import com.offretechnical.test.models.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootApplicationRunner.class)
public class SpringBootH2IntegrationTest extends AbstractTest {

	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * create Not Adult user , and check return status
	 * 
	 * @throws Exception
	 */
	@Test
	public void createUserNotAdult() throws Exception {
		String uri = "/api/users";
		User user = new User();
		user.setCountry("France");
		user.setUserName("userName");
		user.setBirthdate(new Date());

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.contains("Only adult are allowed to create an account!"));
	}

	/**
	 * create Not French resident user , and check return status
	 * 
	 * @throws Exception
	 */
	@Test
	public void createUserNotFrench() throws Exception {
		String uri = "/api/users";
		User user = new User();
		user.setCountry("Tunisie");
		user.setUserName("userName");

		Date date = new Date();
		date.setYear(0);
		user.setBirthdate(date);

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertTrue(content.contains("Only French residents are allowed to create an account!"));

	}

	/**
	 * Create User With Success
	 * 
	 * @throws Exception
	 */
	@Test
	public void createUserWithSuccess() throws Exception {
		String uri = "/api/users";
		User user = new User();
		user.setCountry("France");
		user.setUserName("userName");
		Date date = new Date();
		date.setYear(0);
		user.setBirthdate(date);

		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(user);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

}
