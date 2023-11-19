package com.example.telegrambotanimalshelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PropertySource(value = "getInfoAboutCat.text", encoding = "UTF-8")
public class ShelterInformationCatService implements CommandHandler{
    Logger log = LoggerFactory.getLogger(ShelterInformationCatService.class);
    private String EXCEPTION = "Ошибка команды, пожалуйста повторите действие";
    private final String HOW_TO_TAKE_CAT = "HOW_TO_TAKE_CAT";
    private final String INFO_GET_CAT = "INFO_GET_CAT";
    private final String INFO_NEED_DOCUMENTATION_FOR_CAT = "INFO_NEED_DOCUMENTATION_FOR_CAT";
    private final String HELP_WITH_TRANSPORTATION_CAT = "HELP_WITH_TRANSPORTATION_CAT";
    private final String KITTY_CARE = "KITTY_CARE";
    private final String ADULT_CAT_CARE = "ADULT_CAT_CARE";
    private final String CAT_WITH_LIMITATIONS_CARE = "CAT_WITH_LIMITATIONS_CARE";
    private final String REASONS_FOR_REFUSAL_FROM_CAT = "REASONS_FOR_REFUSAL_FROM_CAT";
    private final String LEAVE_CONTACTS = "LEAVE_CONTACTS";

    @Value("${HOW_TO_TAKE_DOG_ANSWER}")
    private String HOW_TO_TAKE_CAT_ANSWER;
    @Value("${INFO_GET_DOG_ANSWER}")
    private String INFO_GET_CAT_ANSWER;
    @Value("${INFO_NEED_DOCUMENTATION_FOR_DOG_ANSWER}")
    private String INFO_NEED_DOCUMENTATION_FOR_CAT_ANSWER;
    @Value("${HELP_WITH_TRANSPORTATION_DOG_ANSWER}")
    private String HELP_WITH_TRANSPORTATION_CAT_ANSWER;
    @Value("${KITTY_CARE_ANSWER}")
    private String KITTY_CARE_ANSWER;
    @Value("${ADULT_CAT_CARE_ANSWER}")
    private String ADULT_CAT_CARE_ANSWER;
    @Value("${CAT_WITH_LIMITATIONS_CARE_ANSWER}")
    private String CAT_WITH_LIMITATIONS_CARE_ANSWER;
    @Value("${REASONS_FOR_REFUSAL_FROM_CAT_ANSWER}")
    private String REASONS_FOR_REFUSAL_FROM_CAT_ANSWER;

    @Override
    public SendMessage process(Update update) {
        log.info("got into ShelterInformationCatService");
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createInlineKeyboard());
        String answer = choiceWay(update);
        message.setText(answer);
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton infoGetAnimal = new InlineKeyboardButton("Как забрать кошку");
        InlineKeyboardButton infoNeedDocumentation = new InlineKeyboardButton("Список документов для получения кошки");
        InlineKeyboardButton helpWithTransportationAnimal = new InlineKeyboardButton("Как правильно транспортировать кошку");
        InlineKeyboardButton kittyCare = new InlineKeyboardButton("Совету по взятию котенка");
        InlineKeyboardButton adultCatCare = new InlineKeyboardButton("Совету по взятию взрослого кота");
        InlineKeyboardButton catWithLimitationsCare = new InlineKeyboardButton("Совету по взятию кошки с ограничеными возможностями");
        InlineKeyboardButton reasonsForRefusalFromCat = new InlineKeyboardButton("Причины отказа для кота");
        InlineKeyboardButton phoneNumber = new InlineKeyboardButton("Оставить контакты");


        infoGetAnimal.setCallbackData(INFO_GET_CAT);
        infoNeedDocumentation.setCallbackData(INFO_NEED_DOCUMENTATION_FOR_CAT);
        helpWithTransportationAnimal.setCallbackData(HELP_WITH_TRANSPORTATION_CAT);
        kittyCare.setCallbackData(KITTY_CARE);
        adultCatCare.setCallbackData(ADULT_CAT_CARE);
        catWithLimitationsCare.setCallbackData(CAT_WITH_LIMITATIONS_CARE);
        reasonsForRefusalFromCat.setCallbackData(REASONS_FOR_REFUSAL_FROM_CAT);
        phoneNumber.setCallbackData(LEAVE_CONTACTS);

        List<InlineKeyboardButton> infoGetAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> infoNeedDocumentationList = new ArrayList<>();
        List<InlineKeyboardButton> helpWithTransportationAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> kittyCareList = new ArrayList<>();
        List<InlineKeyboardButton> adultCatCareList = new ArrayList<>();
        List<InlineKeyboardButton> catWithLimitationsCareList = new ArrayList<>();
        List<InlineKeyboardButton> reasonsForRefusalFromCatList = new ArrayList<>();
        List<InlineKeyboardButton> phoneNumberList = new ArrayList<>();

        infoGetAnimalList.add(infoGetAnimal);
        infoNeedDocumentationList.add(infoNeedDocumentation);
        helpWithTransportationAnimalList.add(helpWithTransportationAnimal);
        kittyCareList.add(kittyCare);
        adultCatCareList.add(adultCatCare);
        catWithLimitationsCareList.add(catWithLimitationsCare);
        reasonsForRefusalFromCatList.add(reasonsForRefusalFromCat);
        phoneNumberList.add(phoneNumber);

        List<List<InlineKeyboardButton>> infoShelterList = new ArrayList<>();
        infoShelterList.add(infoGetAnimalList);
        infoShelterList.add(infoNeedDocumentationList);
        infoShelterList.add(helpWithTransportationAnimalList);
        infoShelterList.add(kittyCareList);
        infoShelterList.add(adultCatCareList);
        infoShelterList.add(catWithLimitationsCareList);
        infoShelterList.add(reasonsForRefusalFromCatList);
        infoShelterList.add(phoneNumberList);

        inlineKeyboardMarkup.setKeyboard(infoShelterList);
        return inlineKeyboardMarkup;
    }

    public String choiceWay(Update update) {
        log.info("Choosing way into ShelterInformationCatService");
        String text = update.getCallbackQuery().getData();
        switch (text) {
            case HOW_TO_TAKE_CAT:
                return HOW_TO_TAKE_CAT_ANSWER;
            case INFO_GET_CAT:
                return INFO_GET_CAT_ANSWER;
            case INFO_NEED_DOCUMENTATION_FOR_CAT:
                return INFO_NEED_DOCUMENTATION_FOR_CAT_ANSWER;
            case HELP_WITH_TRANSPORTATION_CAT:
                return HELP_WITH_TRANSPORTATION_CAT_ANSWER;
            case KITTY_CARE:
                return KITTY_CARE_ANSWER;
            case ADULT_CAT_CARE:
                return ADULT_CAT_CARE_ANSWER;
            case CAT_WITH_LIMITATIONS_CARE:
                return CAT_WITH_LIMITATIONS_CARE_ANSWER;
            case REASONS_FOR_REFUSAL_FROM_CAT:
                return REASONS_FOR_REFUSAL_FROM_CAT_ANSWER;
            default:
                return EXCEPTION;
        }
    }
}
