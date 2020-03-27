package com.example.dbexample.controller;

import com.example.dbexample.model.DogDto;
import com.example.dbexample.repo.Dog;
import com.example.dbexample.service.DogsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


@RunWith(MockitoJUnitRunner.class)
public class ExampleControllerTest {

	@InjectMocks
	private ExampleController exampleControllerTest;

	@Mock
	private Model modelMock;

	@Mock
	private DogsService dogsServiceMock;

	@Mock
	BindingResult bindingResultMock;

	@Mock
	private DogDto dogDtoMock;

	@Before
	public void setUp() {

	}

	@Test
	public void addDog_shouldreturn_certain_string() throws Exception {
		String result = exampleControllerTest.addDog(modelMock);
		assertEquals("add_dog", result);
	}

	@Test
	public void addDogSubmit_shouldreturn_add_dog_result() throws Exception {
		Dog dog = new Dog();
		dog.setAge(10);
		dog.setId(0);
		dog.setName("Fikkie");
		String result;

		result = exampleControllerTest.addDogSubmit(dog, bindingResultMock, modelMock);
		assertEquals("add_dog_result", result);
	}

	@Test
	public void addDogSubmit_ageZero() throws Exception {
		Dog dog = new Dog();
		dog.setAge(0);
		dog.setName("Fikkie");
		String result;

		result = exampleControllerTest.addDogSubmit(dog, bindingResultMock, modelMock);
		assertEquals("/expectederror", result);
		verify(dogsServiceMock, times(0)).add(isA(DogDto.class));
	}





}
