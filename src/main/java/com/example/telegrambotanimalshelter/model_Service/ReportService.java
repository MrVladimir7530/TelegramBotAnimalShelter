package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Report;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public interface ReportService {
    String create(Update update);

}
