package com.example.dbexample.controller;


import com.example.dbexample.DbExampleApplication;
import com.example.dbexample.service.AccountService;
import com.example.dbexample.service.DogsService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK, classes={ DbExampleApplication.class })
public class ExampleControllerTestMVC {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private DogsService dogsService;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    

}
