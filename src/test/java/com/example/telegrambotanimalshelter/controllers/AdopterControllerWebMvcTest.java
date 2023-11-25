package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.*;
import com.example.telegrambotanimalshelter.models.Adopter;
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
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.NoSuchElementException;
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

        when(subscriberRepository.findByChatId(1L)).thenReturn(SUBSCRIBER1);
        when(animalRepository.findById(1L)).thenReturn(Optional.of(ANIMAL_CAT1));
        when(adopterRepository.save(any(Adopter.class))).thenReturn(ADOPTER1);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/adopter")
                        .param("subscriberChatId", "1")
                        .param("ID животного", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ADOPTER1.getId()))
                .andExpect(jsonPath("$.adoptionDate").value(ADOPTER1.getAdoptionDate().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(30));

        verify(subscriberRepository, times(1)).findByChatId(1L);
        verify(animalRepository, times(1)).findById(1L);
        verify(adopterRepository, times(1)).save(any(Adopter.class));
    }

    @Test
    public void shouldCorrectResultFromMethodEditTrialPeriodWhenDaysInNotNull() throws Exception {

        when(adopterRepository.findById(1L)).thenReturn(Optional.of(ADOPTER1));
        when(adopterRepository.save(any(Adopter.class))).thenReturn(ADOPTER1);
        doNothing().when(adopterService).prepareAndSendMessage(any(SendMessage.class));


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/adopter")
                        .param("Чат ID усыновления", "1")
                        .param("Количество добавляемых дней", "15")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ADOPTER1.getId()))
                .andExpect(jsonPath("$.adoptionDate").value(ADOPTER1.getAdoptionDate().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(45));

        verify(adopterRepository, times(1)).findById(1L);
        verify(adopterRepository, times(1)).save(any(Adopter.class));
        verify(adopterService, times(1)).prepareAndSendMessage(any(SendMessage.class));

    }
    @Test
    public void shouldCorrectResultFromMethodEditTrialPeriodWhenBooleanIsTrue() throws Exception {

        when(adopterRepository.findById(1L)).thenReturn(Optional.of(ADOPTER1));
        when(adopterRepository.save(any(Adopter.class))).thenReturn(ADOPTER1);
        doNothing().when(adopterService).prepareAndSendMessage(any(SendMessage.class));


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/adopter")
                        .param("Чат ID усыновления", "1")
                        .param("Решение о прохождении испытательного срока", "true")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ADOPTER1.getId()))
                .andExpect(jsonPath("$.adoptionDate").value(ADOPTER1.getAdoptionDate().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(0));

        verify(adopterRepository, times(1)).findById(1L);
        verify(adopterRepository, times(1)).save(any(Adopter.class));

    }
    @Test
    public void shouldCorrectResultFromMethodEditTrialPeriodWhenBooleanIsFalse() throws Exception {

        when(adopterRepository.findById(1L)).thenReturn(Optional.of(ADOPTER1));
        when(adopterRepository.save(any(Adopter.class))).thenReturn(ADOPTER1);
        doNothing().when(adopterService).prepareAndSendMessage(any(SendMessage.class));


        mockMvc.perform(MockMvcRequestBuilders
                        .put("/adopter")
                        .param("Чат ID усыновления", "1")
                        .param("Решение о прохождении испытательного срока", "false")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ADOPTER1.getId()))
                .andExpect(jsonPath("$.adoptionDate").value(ADOPTER1.getAdoptionDate().toString()))
                .andExpect(jsonPath("$.trialPeriod").value(0));

        verify(adopterRepository, times(1)).findById(1L);
        verify(adopterRepository, times(1)).save(any(Adopter.class));

    }

//    @Test
//    public void shouldThrowNoSuchElementException() throws Exception {
//
//        when(adopterRepository.findById(1L)).thenThrow(NoSuchElementException.class);
//
//
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .put("/adopter")
//                        .param("Чат ID усыновления", "1")
//                        .param("Решение о прохождении испытательного срока", "false")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is5xxServerError());
//
//        verify(adopterRepository, times(1)).findById(1L);
//
//    }
}
