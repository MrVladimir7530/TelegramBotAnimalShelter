package com.example.telegrambotanimalshelter.controllers;


import com.example.telegrambotanimalshelter.model_services.VolunteerService;
import com.example.telegrambotanimalshelter.models.Volunteer;
import com.example.telegrambotanimalshelter.repositories.VolunteerRepository;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(classes = TelegramBotAnimalShelterApplication.class)
@WebMvcTest(VolunteerController.class)
public class VolunteerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private VolunteerService volunteerService;
    @MockBean
    private VolunteerRepository volunteerRepository;


    @Test
    public void saveVolunteerTest() throws Exception {
        String name = "Ivan";
        String phoneNumber = "111111";
        String userName = "@Ivan";

        JSONObject volunteerObject = new JSONObject();
        volunteerObject.put("name", name);
        volunteerObject.put("phoneNumber", phoneNumber);
        volunteerObject.put("userName", userName);

        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setName(name);
        volunteer.setPhoneNumber(phoneNumber);
        volunteer.setUserName(userName);

        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteer);
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteer));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("volunteer")
                        .content(volunteerObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.userName").value(userName));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("volunteer?chatId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.userName").value(userName));
    }
    //todo дописать

}