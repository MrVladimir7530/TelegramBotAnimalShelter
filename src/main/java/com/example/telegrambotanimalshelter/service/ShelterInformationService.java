package com.example.telegrambotanimalshelter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("getInfo.text")
public class ShelterInformationService implements CommandHandler {
    private String EXCEPTION = "Ошибка команды, пожалуйста повторите действие";
    private final String INFO_GET_ANIMAL = "INFO_GET_ANIMAL";
    private final String INFO_NEED_DOCUMENTATION = "INFO_NEED_DOCUMENTATION";
    @Value("${INFO_GET_ANIMAL_ANSWER}")
    private String INFO_GET_ANIMAL_ANSWER;
    @Value("${INFO_NEED_DOCUMENTATION_ANSWER}")
    private String INFO_NEED_DOCUMENTATION_ANSWER;

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setReplyMarkup(createInlineKeyboard());
        String answer = choiceWay(update);
        message.setText(answer);
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton infoGetAnimal = new InlineKeyboardButton("Как забрать животное?");
        InlineKeyboardButton infoNeedDocumentation = new InlineKeyboardButton("Список документов для получения животного");

        infoGetAnimal.setCallbackData("infoGetAnimal");
        infoNeedDocumentation.setCallbackData("infoNeedDocumentation");

        List<InlineKeyboardButton> infoShelterButton = new ArrayList<>();
        infoShelterButton.add(infoGetAnimal);
        infoShelterButton.add(infoNeedDocumentation);
        return inlineKeyboardMarkup;
    }

    public String choiceWay(Update update) {
        String text = update.getMessage().getText();
        switch (text) {
            case INFO_GET_ANIMAL:
                return INFO_GET_ANIMAL_ANSWER;
            case INFO_NEED_DOCUMENTATION:
                return INFO_NEED_DOCUMENTATION_ANSWER;
            default:
        }
        return EXCEPTION;
    }
}
