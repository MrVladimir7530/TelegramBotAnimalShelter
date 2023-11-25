package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.AnimalService;
import com.example.telegrambotanimalshelter.model_services.AnimalServiceImpl;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AnimalController.class)
public class AnimalControllerWebMvsTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalController animalController;

    @SpyBean
    private AnimalServiceImpl animalService;
    // TODO: дописать тесты на контроллер 25.11.2023
}
