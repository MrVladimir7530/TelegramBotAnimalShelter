package com.example.telegrambotanimalshelter.model_services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ReportService {
    String create(Update update);

}
