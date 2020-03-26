package com.example.dbexample.controller;

import com.example.dbexample.controller.ExampleController;
import com.example.dbexample.model.DogDto;
import com.example.dbexample.repo.Dog;
import com.example.dbexample.service.DogsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;


import javax.naming.Binding;

import java.beans.PropertyEditor;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


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

//	@Test
//	public void addDogSubmit_process() throws Exception {
//		Dog dog = new Dog();
//		String result = exampleControllerTest.addDogSubmit(modelMock, dog);
//
//		verify(dogsServiceMock, times(1)).add(isA(DogDto.class));
//		assertEquals("add_dog_result", result);
//	}


}
