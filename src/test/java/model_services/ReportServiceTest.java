package model_services;

import com.example.telegrambotanimalshelter.components.VolunteerSendMessageService;
import com.example.telegrambotanimalshelter.model_services.AdopterService;
import com.example.telegrambotanimalshelter.model_services.ReportService;
import com.example.telegrambotanimalshelter.model_services.ReportServiceImpl;
import com.example.telegrambotanimalshelter.model_services.UploadReportPhoto;
import com.example.telegrambotanimalshelter.repositories.ReportRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static constants.ReportConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReportServiceTest {

    private UploadReportPhoto uploadReportPhotoMock;
    private ReportRepository reportRepositoryMock;
    private AdopterService adopterServiceMock;
    private TelegramBot telegramBotMock;
    private VolunteerSendMessageService volunteerSendMessageServiceMock;
    private ReportService out;

    @BeforeEach
    public void init() {
        uploadReportPhotoMock = Mockito.mock(UploadReportPhoto.class);
        reportRepositoryMock = Mockito.mock(ReportRepository.class);
        adopterServiceMock = Mockito.mock(AdopterService.class);
        telegramBotMock = Mockito.mock(TelegramBot.class);
        uploadReportPhotoMock = Mockito.mock(UploadReportPhoto.class);

        out = new ReportServiceImpl(telegramBotMock
                , uploadReportPhotoMock, reportRepositoryMock
                , adopterServiceMock
                , volunteerSendMessageServiceMock);
    }

    @Test
    public void shouldCorrectResultFromMethodFindById() {
        when(reportRepositoryMock.findById(1L)).thenReturn(Optional.of(REPORT1));
        when(reportRepositoryMock.findById(3L)).thenReturn(Optional.of(REPORT3));

        assertEquals(REPORT1, out.findById(1L));
        assertEquals(REPORT3, out.findById(3L));

        verify(reportRepositoryMock, times(1)).findById(1L);
        verify(reportRepositoryMock, times(1)).findById(3L);
    }
    @Test
    public void shouldCorrectResultFromMethodFindByAdopterIdAndCreationDate() {
        when(reportRepositoryMock.findByAdopterIdAndCreationDate(1L, REPORT1.getCreationDate())).thenReturn(REPORT1);
        when(reportRepositoryMock.findByAdopterIdAndCreationDate(3L, REPORT3.getCreationDate())).thenReturn(REPORT3);


        assertEquals(REPORT1, out.findByAdopterIdAndCreationDate(1L, REPORT1.getCreationDate()));
        assertEquals(REPORT3, out.findByAdopterIdAndCreationDate(3L, REPORT3.getCreationDate()));

        verify(reportRepositoryMock, times(1)).findByAdopterIdAndCreationDate(1L, REPORT1.getCreationDate());
        verify(reportRepositoryMock, times(1)).findByAdopterIdAndCreationDate(3L, REPORT3.getCreationDate());
    }




}
