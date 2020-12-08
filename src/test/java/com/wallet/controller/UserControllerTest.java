package com.wallet.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	private static final Long ID = 0L;
	private static final String NAME = "mock";
	private static final String PASSWORD = "123456";
	private static final String EMAIL = "mock@teste.com";
	private static final String URL = "/user";

	@MockBean
	UserService userService;

	@Autowired
	MockMvc mvc;

	@Test
	public void testSave() throws Exception {

		given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());

		mvc.perform(post(URL).content(getJsonPayload(ID, NAME, EMAIL, PASSWORD)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.data.id").value(ID)).andExpect(jsonPath("$.data.name").value(NAME))
				.andExpect(jsonPath("$.data.email").value(EMAIL))
				.andExpect(jsonPath("$.data.password").value(PASSWORD));

	}

	@Test
	public void testSaveInvalidUser() throws Exception {

		given(userService.save(Mockito.any(User.class))).willReturn(getMockUser());

		mvc.perform(post(URL).content(getJsonPayload(ID, NAME, "emailinvalido", PASSWORD))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors[0]").value("Email invalido"));

	}

	public User getMockUser() {
		User u = new User();
		u.setId(ID);
		u.setName(NAME);
		u.setPassword(PASSWORD);
		u.setEmail(EMAIL);

		return u;
	}

	public String getJsonPayload(Long id, String name, String email, String password) throws JsonProcessingException {
		UserDTO dto = new UserDTO();
		dto.setId(id);
		dto.setName(name);
		dto.setPassword(password);
		dto.setEmail(email);

		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(dto);

	}

}
