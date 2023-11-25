package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.components.VolunteerSendMessageService;
import com.example.telegrambotanimalshelter.constants.AdopterConstant;
import com.example.telegrambotanimalshelter.constants.ReportConstant;
import com.example.telegrambotanimalshelter.model_services.*;
import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import com.example.telegrambotanimalshelter.repositories.ReportRepository;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static com.example.telegrambotanimalshelter.constants.AdopterConstant.*;
import static com.example.telegrambotanimalshelter.constants.AdopterConstant.ADOPTER1;
import static com.example.telegrambotanimalshelter.constants.ReportConstant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UploadReportPhotoImpl uploadReportPhoto;
    @MockBean
    private TelegramBot telegramBot;
    @MockBean
    private SubscriberRepository subscriberRepository;
    @SpyBean
    private SubscriberServiceImpl subscriberService;
    @MockBean
    private AdopterRepository adopterRepository;
    @SpyBean
    private AdopterServiceImpl adopterService;
    @MockBean
    private ReportRepository reportRepository;
    @SpyBean
    private ReportServiceImpl reportService;
    @MockBean
    private VolunteerSendMessageService volunteerSendMessageService;
    @MockBean
    private AnimalRepository animalRepository;
    @SpyBean
    private AnimalServiceImpl animalService;
    @InjectMocks
    private AdopterController adopterController;

    @Test
    public void shouldCorrectResultFromMethodFindTodaysReportsByShelterId() throws Exception {

        when(adopterRepository.findAdoptersOfShelterAnimals(anyLong())).thenReturn(SHELTER1_ADOPTERS);
        when(reportRepository.findByAdopterIdAndCreationDate(ADOPTER1.getId(), LocalDate.now())).thenReturn(REPORT1);
        when(reportRepository.findByAdopterIdAndCreationDate(ADOPTER2.getId(), LocalDate.now())).thenReturn(REPORT2);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/report/todayReports")
                        .param("ID приюта", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(List.of(REPORT1, REPORT2))));

        verify(adopterRepository, times(1)).findAdoptersOfShelterAnimals(anyLong());
        verify(reportRepository, times(1)).findByAdopterIdAndCreationDate(ADOPTER1.getId(), LocalDate.now());
        verify(reportRepository, times(1)).findByAdopterIdAndCreationDate(ADOPTER2.getId(), LocalDate.now());
    }
    @Test
    public void shouldStatus200FromMethodSendWarningWhenTextIsNull() throws Exception {
        doNothing().when(telegramBot).prepareAndSendMessage(any(SendMessage.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/report/sendWarning")
                        .param("Чат ID усыновителя", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(telegramBot, times(1)).prepareAndSendMessage(any(SendMessage.class));
    }
//    @Test
//    public void shouldStatus409FromMethodSendWarningWhenTextIsNull() throws Exception {
//        when(telegramBot.execute(any(SendMessage.class))).thenThrow(TelegramApiException.class);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/report/sendWarning")
//                        .param("Чат ID усыновителя", "1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict());
//
//        verify(telegramBot, times(1)).prepareAndSendMessage(any(SendMessage.class));
//    }
}
