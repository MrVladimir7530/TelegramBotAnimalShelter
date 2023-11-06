package com.example.telegrambotanimalshelter.service;

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
@PropertySource(value = "getInfoAboutDog.text", encoding = "UTF-8")
public class ShelterInformationDogService implements CommandHandler {
    private String EXCEPTION = "Ошибка команды, пожалуйста повторите действие";
    private final String HOW_TO_TAKE_DOG = "HOW_TO_TAKE_DOG";
    private final String INFO_GET_DOG = "INFO_GET_DOG";
    private final String INFO_NEED_DOCUMENTATION_FOR_DOG = "INFO_NEED_DOCUMENTATION_FOR_DOG";
    private final String HELP_WITH_TRANSPORTATION_DOG = "HELP_WITH_TRANSPORTATION_DOG";
    private final String DOG_HANDLER_ADVICE_BY_TAKE = "DOG_HANDLER_ADVICE_BY_TAKE";
    private final String DOG_HANDLER_ADVICE_BY_CARE = "DOG_HANDLER_ADVICE_BY_CARE";

    @Value("${HOW_TO_TAKE_DOG_ANSWER}")
    private String HOW_TO_TAKE_DOG_ANSWER;
    @Value("${INFO_GET_DOG_ANSWER}")
    private String INFO_GET_DOG_ANSWER;
    @Value("${INFO_NEED_DOCUMENTATION_FOR_DOG_ANSWER}")
    private String INFO_NEED_DOCUMENTATION_FOR_DOG_ANSWER;
    @Value("${HELP_WITH_TRANSPORTATION_DOG_ANSWER}")
    private String HELP_WITH_TRANSPORTATION_DOG_ANSWER;
    @Value("${DOG_HANDLER_ADVICE_BY_TAKE_ANSWER}")
    private String DOG_HANDLER_ADVICE_BY_TAKE_ANSWER;
    @Value("${DOG_HANDLER_ADVICE_BY_CARE_ANSWER}")
    private String DOG_HANDLER_ADVICE_BY_CARE_ANSWER;



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
        InlineKeyboardButton infoGetAnimal = new InlineKeyboardButton("Как забрать собаку");
        InlineKeyboardButton infoNeedDocumentation = new InlineKeyboardButton("Список документов для получения собаки");
        InlineKeyboardButton helpWithTransportationAnimal = new InlineKeyboardButton("Как правильно транспортировать собаку");
        InlineKeyboardButton dogHandlerAdvice = new InlineKeyboardButton("Советы кинолога по взятию");
        InlineKeyboardButton dogHandlerAdviceCare = new InlineKeyboardButton("Советы кинолога по уходу");

        infoGetAnimal.setCallbackData(INFO_GET_DOG);
        infoNeedDocumentation.setCallbackData(INFO_NEED_DOCUMENTATION_FOR_DOG);
        helpWithTransportationAnimal.setCallbackData(HELP_WITH_TRANSPORTATION_DOG);
        dogHandlerAdvice.setCallbackData(DOG_HANDLER_ADVICE_BY_TAKE);
        dogHandlerAdviceCare.setCallbackData(DOG_HANDLER_ADVICE_BY_CARE);

        List<InlineKeyboardButton> infoGetAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> infoNeedDocumentationList = new ArrayList<>();
        List<InlineKeyboardButton> helpWithTransportationAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> dogHandlerAdviceList = new ArrayList<>();
        List<InlineKeyboardButton> dogHandlerAdviceCareList = new ArrayList<>();

        infoGetAnimalList.add(infoGetAnimal);
        infoNeedDocumentationList.add(infoNeedDocumentation);
        helpWithTransportationAnimalList.add(helpWithTransportationAnimal);
        dogHandlerAdviceList.add(dogHandlerAdvice);
        dogHandlerAdviceCareList.add(dogHandlerAdviceCare);

        List<List<InlineKeyboardButton>> infoShelterList = new ArrayList<>();
        infoShelterList.add(infoGetAnimalList);
        infoShelterList.add(infoNeedDocumentationList);
        infoShelterList.add(helpWithTransportationAnimalList);
        infoShelterList.add(dogHandlerAdviceList);
        infoShelterList.add(dogHandlerAdviceCareList);

        inlineKeyboardMarkup.setKeyboard(infoShelterList);
        return inlineKeyboardMarkup;
    }

    public String choiceWay(Update update) {
        String text = update.getCallbackQuery().getData();
        switch (text) {
            case HOW_TO_TAKE_DOG:
                return HOW_TO_TAKE_DOG_ANSWER;
            case INFO_GET_DOG:
                return INFO_GET_DOG_ANSWER;
            case INFO_NEED_DOCUMENTATION_FOR_DOG:
                return INFO_NEED_DOCUMENTATION_FOR_DOG_ANSWER;
            case HELP_WITH_TRANSPORTATION_DOG:
                return HELP_WITH_TRANSPORTATION_DOG_ANSWER;
            case DOG_HANDLER_ADVICE_BY_TAKE:
                return DOG_HANDLER_ADVICE_BY_TAKE_ANSWER;
            case DOG_HANDLER_ADVICE_BY_CARE:
                return DOG_HANDLER_ADVICE_BY_CARE_ANSWER;
            default:
                return EXCEPTION;
        }
    }
}
