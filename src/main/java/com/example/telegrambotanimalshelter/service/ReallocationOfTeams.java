package com.example.telegrambotanimalshelter.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ReallocationOfTeams {
    SendMessage process(Update update);
}
