package com.example.dbexample.controller;


import com.example.dbexample.DbExampleApplication;
import com.example.dbexample.model.Account;
import com.example.dbexample.model.DogDto;
import com.example.dbexample.service.AccountService;
import com.example.dbexample.service.DogsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import javax.swing.text.html.HTMLDocument;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ DbExampleApplication.class })
public class DogsControllerTestMVC {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private DogsService dogsServiceMock;

    //@Autowired
    //private DogsController dogsController;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    // Een goede hond. De waarden worden geconverteerd naar een hond
    @Test
    public void shouldAcceptPost_EndpointAddDog() throws Exception {
        mockMvc.perform(post("/add_dog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Flash\", \"age\": 41 }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // Return value is 200. De error pagina wordt teruggegeven.
    // Voor HTTP is dat een correct resultaat
    // Het resultaat van de pagina wordt naar string geconverteerd en op
    // de pagina hoort een foutmelding te staan over de leeftijd
    @Test
    public void shouldCheckNoAge() throws Exception {
        String htmlresult = mockMvc.perform(post("/add_dog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Flash\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(htmlresult.contains("Error in field:dog age"));
    }

    /*
     *  Bij foutief formaat van de leeftijd (bijvoorbeeld als deze letters bevat)
     *  wordt een foutpagina getoond
    */
    @Test
    public void checkInvalidFormat_Errorpage() throws Exception {
        String htmlresult = mockMvc.perform(post("/add_dog")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\": \"Flash\", \"age\": \"heel oud\" }")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(htmlresult.contains("Error in field:dog age"));
    }

    @Test
    public void checkListEndpoint() throws Exception {
        String htmlresult = mockMvc.perform(post("/list"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(htmlresult.contains("Result"));
    }
}
