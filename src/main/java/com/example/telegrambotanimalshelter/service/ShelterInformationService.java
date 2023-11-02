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
    private final String WELCOME_MESSAGE_INFO = "WELCOME_MESSAGE_INFO";
    private final String HELP_WITH_TRANSPORTATION_ANIMAL = "HELP_WITH_TRANSPORTATION_ANIMAL";
    @Value("${How_to_take_an_animal}")
    private String How_to_take_an_animal;
    @Value("${INFO_GET_ANIMAL_ANSWER}")
    private String INFO_GET_ANIMAL_ANSWER;
    @Value("${INFO_NEED_DOCUMENTATION_ANSWER}")
    private String INFO_NEED_DOCUMENTATION_ANSWER;
    @Value("${HELP_WITH_TRANSPORTATION_ANIMAL_ANSWER}")
    private String HELP_WITH_TRANSPORTATION_ANIMAL_ANSWER;

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createInlineKeyboard());
        String answer = choiceWay(update);
        message.setText(answer);
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton infoGetAnimal = new InlineKeyboardButton("Как забрать животное");
        InlineKeyboardButton infoNeedDocumentation = new InlineKeyboardButton("Список документов для получения животного");
        InlineKeyboardButton helpWithTransportationAnimal = new InlineKeyboardButton("Как правильно транспортировать животное");

        infoGetAnimal.setCallbackData(INFO_GET_ANIMAL);
        infoNeedDocumentation.setCallbackData(INFO_NEED_DOCUMENTATION);
        helpWithTransportationAnimal.setCallbackData(HELP_WITH_TRANSPORTATION_ANIMAL);

        List<InlineKeyboardButton> infoGetAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> infoNeedDocumentationList = new ArrayList<>();
        List<InlineKeyboardButton> helpWithTransportationAnimalList = new ArrayList<>();

        infoGetAnimalList.add(infoGetAnimal);
        infoNeedDocumentationList.add(infoNeedDocumentation);
        helpWithTransportationAnimalList.add(helpWithTransportationAnimal);

        List<List<InlineKeyboardButton>> infoShelterList = new ArrayList<>();
        infoShelterList.add(infoGetAnimalList);
        infoShelterList.add(infoNeedDocumentationList);
        infoShelterList.add(helpWithTransportationAnimalList);

        inlineKeyboardMarkup.setKeyboard(infoShelterList);
        return inlineKeyboardMarkup;
    }

    public String choiceWay(Update update) {
        String text = update.getCallbackQuery().getData();
        switch (text) {
            case WELCOME_MESSAGE_INFO:
                return How_to_take_an_animal;
            case INFO_GET_ANIMAL:
                return INFO_GET_ANIMAL_ANSWER;
            case INFO_NEED_DOCUMENTATION:
                return INFO_NEED_DOCUMENTATION_ANSWER;
            case HELP_WITH_TRANSPORTATION_ANIMAL:
                return HELP_WITH_TRANSPORTATION_ANIMAL_ANSWER;
            default:
                return EXCEPTION;
        }
    }
}
