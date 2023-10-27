package com.example.telegrambotanimalshelter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
@RequiredArgsConstructor
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    private final StartMenu startMenu;

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();

        if (update.getMessage().getText().equals("/start")) {
            message = startMenu.process(update);
        }

        return message;
    }



}
