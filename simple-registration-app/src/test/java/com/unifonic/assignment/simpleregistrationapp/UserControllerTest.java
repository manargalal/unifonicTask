package com.unifonic.assignment.simpleregistrationapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.unifonic.assignment.controller.UserController;
import com.unifonic.assignment.service.SenderService;
import com.unifonic.assignment.service.UserService;
import com.unifonic.assignment.validator.UserFormValidator;


public class UserControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private UserController userController;
	@Mock
	private UserFormValidator validator;

	@Mock
	private UserService registerUseCase;


	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Before
	public void before(){
		when(validator.supports(any())).thenReturn(true);
	}



	@Test
	public void givenRegistartionFormPageURI_whenMockMVC_thenReturnsViewName() throws Exception {
		this.mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("registrationFormPage"))
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}


	@Test
	public void whenRegisterWithValidInput_thenReturns200() throws Exception {
		mockMvc.perform(post("/register?action=submit")
				.param("firstName", "Manar").param("lastName", "anan").param("email", "manar@gmail.com")
				.param("phoneNumber", "962799999999").param("code", "4665")
				.contentType("application/form-data"))
				.andExpect(status().isOk());
	}

	@Test
	public void whenValidPhoneNumber_thenReturns200() throws Exception {
		mockMvc.perform(post("/register?action=verify")
				.param("firstName", "Manar").param("lastName", "anan").param("email", "manar@gmail.com")
				.param("phoneNumber", "962799999999").param("code", "4665")
				.contentType("application/form-data"))
				.andExpect(status().isOk());
	}


}
