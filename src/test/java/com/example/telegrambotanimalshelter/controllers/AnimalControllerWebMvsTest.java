package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.AnimalServiceImpl;
import com.example.telegrambotanimalshelter.models.Animal;
import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnimalController.class)
public class AnimalControllerWebMvsTest {
    private static final Long ANIMAL_ID = 123L;
    private static final String NAME = "Буся";
    private static final String BREED = "Королевская";
    private static final Long SHELTER_ID = 10L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalRepository animalRepository;

    @SpyBean
    private AnimalServiceImpl animalService;

    @Test
    public void addAnimalTest() throws Exception {
        Shelter shelter = new Shelter();
        shelter.setId(SHELTER_ID);

        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", SHELTER_ID);

        JSONObject animalObject = new JSONObject();
        animalObject.put("id", ANIMAL_ID);
        animalObject.put("name", NAME);
        animalObject.put("breed", BREED);
        animalObject.put("shelter", shelter);

        Animal animal = createAnimal();

        when(animalRepository.save(any(Animal.class))).thenReturn(animal);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/animals")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ANIMAL_ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.breed").value(BREED))
                .andExpect(jsonPath("$.shelter").value(shelter));
    }


    public Animal createAnimal() {
        Animal animal = new Animal();

        animal.setId(ANIMAL_ID);
        animal.setName(NAME);
        animal.setBreed(BREED);

        Shelter shelter = new Shelter();
        shelter.setId(SHELTER_ID);
        animal.setShelter(shelter);

        return animal;
    }
}
