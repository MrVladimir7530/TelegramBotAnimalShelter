package com.example.telegrambotanimalshelter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class ChooseAnimal {

    private final TelegramBot telegramBot;

    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (text) {
            case "/cat":
                telegramBot.prepareAndSendMessage(chatId, "кошка");
                break;
            case "/dog":
                telegramBot.prepareAndSendMessage(chatId, "собака");
                break;
        }

    }
}
