package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.VolunteerServiceImpl;
import com.example.telegrambotanimalshelter.models.Shelter;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VolunteerController.class)
public class VolunteerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VolunteerRepository volunteerRepository;
    @SpyBean
    private VolunteerServiceImpl volunteerService;
    private String name = "TestName";
    private String phoneNumber = "TestPhoneNumber";
    private String userName = "TestUserName";

    @Test
    public void testAddVolunteer() throws Exception {
        JSONObject shelterObject = new JSONObject();
        shelterObject.put("chatId", 1L);
        shelterObject.put("name", name);
        shelterObject.put("phoneNumber", phoneNumber);
        shelterObject.put("userName", userName);


        Volunteer volunteer = createVolunteer();

        when(volunteerRepository.save(any(Volunteer.class))).thenReturn(volunteer);
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteer));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/volunteer")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatId").value(1L))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.userName").value(userName));

    }

    @Test
    public void getVolunteerByIdTest() throws Exception{
        Volunteer volunteer = createVolunteer();
        when(volunteerRepository.findById(any(Long.class))).thenReturn(Optional.of(volunteer));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/volunteer/" + volunteer.getChatId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatId").value(1L))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.userName").value(userName));
    }

    @Test
    public void deleteVolunteerByIdTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/volunteer?chatId=1"))
                .andExpect(status().isOk());
    }


    public Volunteer createVolunteer() {
        Volunteer volunteer = new Volunteer();
        volunteer.setChatId(1L);
        volunteer.setName(name);
        volunteer.setUserName(userName);
        volunteer.setPhoneNumber(phoneNumber);
        return volunteer;
    }
}
