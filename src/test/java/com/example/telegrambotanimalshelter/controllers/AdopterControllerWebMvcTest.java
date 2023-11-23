package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.*;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static com.example.telegrambotanimalshelter.constants.AdopterConstant.*;
import static com.example.telegrambotanimalshelter.constants.AnimalConstant.*;
import static com.example.telegrambotanimalshelter.constants.SubscriberConstant.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdopterController.class)
public class AdopterControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SubscriberRepository subscriberRepository;
    @SpyBean
    private SubscriberServiceImpl subscriberService;
    @MockBean
    private AdopterRepository adopterRepository;
    @SpyBean
    private AdopterServiceImpl adopterService;
    @MockBean
    private AnimalRepository animalRepository;
    @SpyBean
    private AnimalServiceImpl animalService;
    @InjectMocks
    private AdopterController adopterController;

    @Test
    public void shouldCorrectResultFromMethodCreateAdopter() throws Exception {

        when(subscriberRepository.findById(1L)).thenReturn(Optional.of(SUBSCRIBER1));
        when(animalRepository.findById(1L)).thenReturn(Optional.of(ANIMAL_CAT1));
        when(adopterRepository.save(ADOPTER1)).thenReturn(ADOPTER1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/adopter")
                        .param("subscriberChatId", "1")
                        .param("ID животного","1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.adoptionDate").value(ADOPTER1.getAdoptionDate()))
                .andExpect(jsonPath("$.trialPeriod").value(30))
                .andExpect(jsonPath("$.subscriber_id").value(ADOPTER1.getSubscriber().getChatId()))
                .andExpect(jsonPath("$.animal_id").value(ADOPTER1.getAnimal().getId()));

        verify(subscriberRepository, times(1)).findById(1L);
        verify(animalRepository, times(1)).findById(1L);
        verify(adopterRepository, times(1)).save(ADOPTER1);






    }
}
