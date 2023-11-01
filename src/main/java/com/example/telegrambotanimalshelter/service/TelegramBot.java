package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.component.ChooseWay;
import com.example.telegrambotanimalshelter.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    private Logger log = LoggerFactory.getLogger(TelegramBot.class);
    private final ReallocationOfTeams reallocationOfTeams;
    private final StartMenu startMenu;
    private final ChooseAnimal chooseAnimal;
    private final ChooseWay chooseWay;
    private final BotConfig botConfig;
    private static final String ERROR = "ERROR: ";
    private static final String textForCancel = "Вы вернулись в стартовое меню";
    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Processing update: {}", update);

        prepareAndSendMessage(reallocationOfTeams.process(update));

    }

    public void prepareAndSendMessage(SendMessage message) {

        try {
            execute(message);
            log.info(String.valueOf(HttpStatus.SC_OK));
        } catch (TelegramApiException e) {
            log.error(ERROR, e);
        }
    }

}
