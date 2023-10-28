package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.component.ChooseWay;
import com.example.telegrambotanimalshelter.config.BotConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class Greetings {
    private Logger log = LoggerFactory.getLogger(Greetings.class);
    private final ChooseAnimal chooseAnimal;
    private final StartMenu startMenu;
    private final ChooseWay chooseWay;
    private final TelegramBot telegramBot;
    private static final String ERROR = "ERROR: ";
    private static final String textForCancel = "Вы вернулись в стартовое меню";


    public void onUpdateReceived(Update update) {
        String text = update.getMessage().getText();
        if (text.equals("/cancel")) {
            chooseWay.setChooseWay(0);
            Long chatId = update.getMessage().getChatId();
            telegramBot.prepareAndSendMessage(chatId, textForCancel);
            return;
        }
        switch (chooseWay.getChooseWay()) {
            case 0:
                startMenu.selectRunCommand(update);
                break;
            case 1:
                chooseAnimal.onUpdateReceived(update);
                break;
            case 2:
                break;
        }
    }
}
