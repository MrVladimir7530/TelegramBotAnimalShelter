package com.example.telegrambotanimalshelter.model_services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ReportService {
    SendMessage process(Update update);
    String create(Update update);

}
