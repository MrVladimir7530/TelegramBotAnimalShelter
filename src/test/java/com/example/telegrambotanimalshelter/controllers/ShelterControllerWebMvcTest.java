package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.controllers.ShelterController;
import com.example.telegrambotanimalshelter.model_services.ShelterService;
import com.example.telegrambotanimalshelter.model_services.ShelterServiceImpl;
import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import org.json.JSONObject;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShelterController.class)
public class ShelterControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterRepository shelterRepository;

    @SpyBean
    private ShelterServiceImpl shelterService;

    @Test
    public void testAddShelter() throws Exception{
        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", 1L);
        shelterObject.put("name", "TestName");
        shelterObject.put("generalInfo", "generalInfo");
        shelterObject.put("info", "info");
        shelterObject.put("securityContact", "securityContact");
        shelterObject.put("safetyPrecautions", "safetyPrecautions");

        Shelter shelter = createShelter();

        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);
        when(shelterRepository.findByName("TestName")).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/shelter")
                        .content(shelterObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.generalInfo").value("generalInfo"))
                .andExpect(jsonPath("$.info").value("info"))
                .andExpect(jsonPath("$.securityContact").value("securityContact"))
                .andExpect(jsonPath("$.safetyPrecautions").value("safetyPrecautions"));
    }

    @Test
    public void testGetShelterById() throws Exception{
        Shelter shelter = createShelter();
        String name = shelter.getName();
        when(shelterRepository.findByName(any(String.class))).thenReturn(shelter);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/shelter?Тип приюта=" + name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1l))
                .andExpect(jsonPath("$.name").value("TestName"))
                .andExpect(jsonPath("$.generalInfo").value("generalInfo"))
                .andExpect(jsonPath("$.info").value("info"))
                .andExpect(jsonPath("$.securityContact").value("securityContact"))
                .andExpect(jsonPath("$.safetyPrecautions").value("safetyPrecautions"));

        verify(shelterRepository, times(1)).findByName(name);
    }

    @Test
    public void testDeleteShelterById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/shelter/1"))
                .andExpect(status().isOk());
    }

    public Shelter createShelter() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("TestName");
        shelter.setGeneralInfo("generalInfo");
        shelter.setInfo("info");
        shelter.setSecurityContact("securityContact");
        shelter.setSafetyPrecautions("safetyPrecautions");

        return shelter;
    }
}
