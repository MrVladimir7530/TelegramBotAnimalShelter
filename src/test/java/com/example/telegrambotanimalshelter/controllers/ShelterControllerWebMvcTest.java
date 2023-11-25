package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.ShelterServiceImpl;
import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    public void addShelterTest() throws Exception{
        JSONObject shelterObject = new JSONObject();
        shelterObject.put("id", 1L);
        shelterObject.put("name", "TestName");
        shelterObject.put("generalInfo", "generalInfo");
        shelterObject.put("info", "info");
        shelterObject.put("securityContact", "securityContact");
        shelterObject.put("safetyPrecautions", "safetyPrecautions");

        Shelter shelter = createShelter();

        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

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
    public void getShelterByNameTest() throws Exception{
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
    public void getAllSheltersTest() throws Exception{
        when(shelterRepository.findAll()).thenReturn(createShelterList());

        mockMvc.perform(MockMvcRequestBuilders.get("/shelter/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // возвращает массив
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("TestName"))
                .andExpect(jsonPath("$[0].generalInfo").value("generalInfo"))
                .andExpect(jsonPath("$[0].info").value("info"))
                .andExpect(jsonPath("$[0].securityContact").value("securityContact"))
                .andExpect(jsonPath("$[0].safetyPrecautions").value("safetyPrecautions"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("shelter2"))
                .andExpect(jsonPath("$[1].generalInfo").value("generalInfo2"))
                .andExpect(jsonPath("$[1].info").value("info2"))
                .andExpect(jsonPath("$[1].securityContact").value("securityContact2"))
                .andExpect(jsonPath("$[1].safetyPrecautions").value("safetyPrecautions2"));

        Mockito.verify(shelterRepository, Mockito.times(1)).findAll();
        Mockito.verify(shelterService, Mockito.times(1)).getAllShelters();
    }

    @Test
    public void deleteShelterByIdTest() throws Exception{
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

    public List<Shelter> createShelterList() {
        Shelter shelter2 = new Shelter();
        shelter2.setId(2L);
        shelter2.setName("shelter2");
        shelter2.setGeneralInfo("generalInfo2");
        shelter2.setInfo("info2");
        shelter2.setSecurityContact("securityContact2");
        shelter2.setSafetyPrecautions("safetyPrecautions2");

        ArrayList<Shelter> shelters = new ArrayList<>();
        shelters.add(createShelter());
        shelters.add(shelter2);
        return shelters;
    }
}
