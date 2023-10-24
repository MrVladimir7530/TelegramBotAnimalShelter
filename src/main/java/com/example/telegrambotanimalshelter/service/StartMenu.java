package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.component.ChooseWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.bots.*;

@Service
@RequiredArgsConstructor
public class StartMenu {
    private final ChooseAnimal chooseAnimal;
    private final ChooseWay chooseWay;
    private static final String ERROR = "Ошибка: ";
    public void selectRunCommand(Update update) {
        String text = update.getMessage().getText();
        switch (text) {
            case "/animal":
                chooseWay.setChooseWay(1);
                chooseAnimal.onUpdateReceived(update);
                break;
            case "/getInfo":
                break;
            case "/help":
                break;
            case "/animalCare":
                chooseWay.setChooseWay(2);
                break;
        }
    }

}