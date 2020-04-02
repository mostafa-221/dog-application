package com.example.dbexample.controller;

import com.example.dbexample.model.DogDto;
import com.example.dbexample.model.GetString;
import com.example.dbexample.model.IdMessage;
import com.example.dbexample.repo.Dog;
import com.example.dbexample.service.DogNotFoundException;
import com.example.dbexample.service.DogsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class DogsControllerTest {

	@InjectMocks
	private DogsController exampleControllerTest;

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

//	@Test
//	public void addDog_shouldreturn_certain_string() throws Exception {
//		String result = exampleControllerTest.addDog(modelMock);
//		assertEquals("add_dog", result);
//	}

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

    @Test
    public void deleteSubmit_success() throws Exception {
        IdMessage dogName = new IdMessage();
        dogName.setContent("Fikkie");

        String result;
        result = exampleControllerTest.deleteSubmit(dogName, bindingResultMock, modelMock);
        assertEquals("delete_dog_confirm", result);
        verify(dogsServiceMock, times(1)).getDogIdByName(isA(String.class));
        verify(dogsServiceMock, times(1)).delete(isA(Long.class));
    }

    @Test
    public void deleteSubmit_emptyName() throws Exception {
        IdMessage dogName = new IdMessage();
        dogName.setContent("");

        String result;
        result = exampleControllerTest.deleteSubmit(dogName, bindingResultMock, modelMock);

        // Assert error is invoked
        assertEquals("/expectederror", result);
        verify(dogsServiceMock, times(0)).getDogIdByName(isA(String.class));
        verify(dogsServiceMock, times(0)).delete(isA(Long.class));

        // Assert correct error message is passed
        ArgumentCaptor<GetString> errorMessage = ArgumentCaptor.forClass(GetString.class);
        verify(modelMock, times(1)).addAttribute(eq("message"), errorMessage.capture());
        assertEquals("Error: No dog name entered for deletion", errorMessage.getValue().getContent());
    }

    @Test
    public void deleteSubmit_unknownDog() throws Exception {
        IdMessage dogName = new IdMessage();
        dogName.setContent("Unknown");

        // Mock getDogIdByName method to invoke a DogNotFoundException
        String errorText = "test";
        when(dogsServiceMock.getDogIdByName(anyString())).thenThrow(new DogNotFoundException(errorText));

        // Execute deletion
        String result;
        result = exampleControllerTest.deleteSubmit(dogName, bindingResultMock, modelMock);

        // Assert error is invoked
        assertEquals("/expectederror", result);
        verify(dogsServiceMock, times(1)).getDogIdByName(isA(String.class));
        verify(dogsServiceMock, times(0)).delete(isA(Long.class));

        // Assert correct error message is passed
        ArgumentCaptor<GetString> errorMessage = ArgumentCaptor.forClass(GetString.class);
        verify(modelMock, times(1)).addAttribute(eq("message"), errorMessage.capture());
        assertEquals("Error: " + errorText, errorMessage.getValue().getContent());
    }

}
