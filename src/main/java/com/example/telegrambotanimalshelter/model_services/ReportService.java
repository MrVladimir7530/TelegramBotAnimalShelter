package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Report;
import org.apache.http.HttpStatus;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public interface ReportService {

    Report findById(long id);
    Report findByAdopterIdAndCreationDate(Long id, LocalDate creationDate);
    SendMessage process(Update update);
    String create(Update update);

    int sendWarning(Long subscriberId, String text);
}
