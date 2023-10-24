package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.component.ChooseWay;
import com.example.telegrambotanimalshelter.config.BotConfig;
import lombok.RequiredArgsConstructor;
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
    private final ChooseAnimal chooseAnimal;
    private final StartMenu startMenu;
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
        String text = update.getMessage().getText();
        if (text.equals("/cancel")) {
            chooseWay.setChooseWay(0);
            Long chatId = update.getMessage().getChatId();
            prepareAndSendMessage(chatId, textForCancel);
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

    public void prepareAndSendMessage(Long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error(ERROR, e);
        }
    }

}
