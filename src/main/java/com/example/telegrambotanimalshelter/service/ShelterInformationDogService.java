package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.model.DogHandler;
import com.example.telegrambotanimalshelter.repository.DogHandlerRepository;
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
@PropertySource(value = "getInfoAboutDog.text", encoding = "UTF-8")
@RequiredArgsConstructor
public class ShelterInformationDogService implements CommandHandler {

    private final DogHandlerRepository dogHandlerRepository;
    private String EXCEPTION = "Ошибка команды, пожалуйста повторите действие";
    private final String HOW_TO_TAKE_DOG = "HOW_TO_TAKE_DOG";
    private final String INFO_GET_DOG = "INFO_GET_DOG";
    private final String INFO_NEED_DOCUMENTATION_FOR_DOG = "INFO_NEED_DOCUMENTATION_FOR_DOG";
    private final String HELP_WITH_TRANSPORTATION_DOG = "HELP_WITH_TRANSPORTATION_DOG";
    private final String DOG_HANDLER_ADVICE_BY_TAKE = "DOG_HANDLER_ADVICE_BY_TAKE";
    private final String DOG_HANDLER_ADVICE_BY_CARE = "DOG_HANDLER_ADVICE_BY_CARE";
    private final String PUPPY_CARE = "PUPPY_CARE";
    private final String ADULT_DOG_CARE = "ADULT_DOG_CARE";
    private final String DOG_WITH_LIMITATIONS_CARE = "DOG_WITH_LIMITATIONS_CARE";
    private final String REASONS_FOR_REFUSAL_FROM_DOG = "REASONS_FOR_REFUSAL_FROM_DOG";
    private final String DOG_HANDLER_RECOMMENDATIONS = "DOG_HANDLER_RECOMMENDATIONS";
    private final String LEAVE_CONTACTS = "LEAVE_CONTACTS";

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
    @Value("${PUPPY_CARE_ANSWER}")
    private String PUPPY_CARE_ANSWER;
    @Value("${ADULT_DOG_CARE_ANSWER}")
    private String ADULT_DOG_CARE_ANSWER;
    @Value("${DOG_WITH_LIMITATIONS_CARE_ANSWER}")
    private String DOG_WITH_LIMITATIONS_CARE_ANSWER;
    @Value("${REASONS_FOR_REFUSAL_FROM_DOG_ANSWER}")
    private String REASONS_FOR_REFUSAL_FROM_DOG_ANSWER;


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
        InlineKeyboardButton puppyCare = new InlineKeyboardButton("Совету по взятию щенка");
        InlineKeyboardButton adultDogCare = new InlineKeyboardButton("Совету по взятию взрослой собаки");
        InlineKeyboardButton dogWithLimitationsCare = new InlineKeyboardButton("Совету по взятию собаки с ограничеными возможностями");
        InlineKeyboardButton reasonsForRefusalFromDog = new InlineKeyboardButton("Причины отказа для собаки");
        InlineKeyboardButton dogHandlerRecommendations = new InlineKeyboardButton("Получить контакты кинологов");
        InlineKeyboardButton phoneNumber = new InlineKeyboardButton("Оставить контакты");

        infoGetAnimal.setCallbackData(INFO_GET_DOG);
        infoNeedDocumentation.setCallbackData(INFO_NEED_DOCUMENTATION_FOR_DOG);
        helpWithTransportationAnimal.setCallbackData(HELP_WITH_TRANSPORTATION_DOG);
        dogHandlerAdvice.setCallbackData(DOG_HANDLER_ADVICE_BY_TAKE);
        dogHandlerAdviceCare.setCallbackData(DOG_HANDLER_ADVICE_BY_CARE);
        puppyCare.setCallbackData(PUPPY_CARE);
        adultDogCare.setCallbackData(ADULT_DOG_CARE);
        dogWithLimitationsCare.setCallbackData(DOG_WITH_LIMITATIONS_CARE);
        reasonsForRefusalFromDog.setCallbackData(REASONS_FOR_REFUSAL_FROM_DOG);
        dogHandlerRecommendations.setCallbackData(DOG_HANDLER_RECOMMENDATIONS);
        phoneNumber.setCallbackData(LEAVE_CONTACTS);

        List<InlineKeyboardButton> infoGetAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> infoNeedDocumentationList = new ArrayList<>();
        List<InlineKeyboardButton> helpWithTransportationAnimalList = new ArrayList<>();
        List<InlineKeyboardButton> dogHandlerAdviceList = new ArrayList<>();
        List<InlineKeyboardButton> dogHandlerAdviceCareList = new ArrayList<>();
        List<InlineKeyboardButton> puppyCareList = new ArrayList<>();
        List<InlineKeyboardButton> adultDogCareList = new ArrayList<>();
        List<InlineKeyboardButton> dogWithLimitationsCareList = new ArrayList<>();
        List<InlineKeyboardButton> reasonsForRefusalFromDogList = new ArrayList<>();
        List<InlineKeyboardButton> dogHandlerRecommendationsList = new ArrayList<>();
        List<InlineKeyboardButton> phoneNumberList = new ArrayList<>();

        infoGetAnimalList.add(infoGetAnimal);
        infoNeedDocumentationList.add(infoNeedDocumentation);
        helpWithTransportationAnimalList.add(helpWithTransportationAnimal);
        dogHandlerAdviceList.add(dogHandlerAdvice);
        dogHandlerAdviceCareList.add(dogHandlerAdviceCare);
        puppyCareList.add(puppyCare);
        adultDogCareList.add(adultDogCare);
        dogWithLimitationsCareList.add(dogWithLimitationsCare);
        reasonsForRefusalFromDogList.add(reasonsForRefusalFromDog);
        dogHandlerRecommendationsList.add(dogHandlerRecommendations);
        phoneNumberList.add(phoneNumber);

        List<List<InlineKeyboardButton>> infoShelterList = new ArrayList<>();
        infoShelterList.add(infoGetAnimalList);
        infoShelterList.add(infoNeedDocumentationList);
        infoShelterList.add(helpWithTransportationAnimalList);
        infoShelterList.add(dogHandlerAdviceList);
        infoShelterList.add(dogHandlerAdviceCareList);
        infoShelterList.add(puppyCareList);
        infoShelterList.add(adultDogCareList);
        infoShelterList.add(dogWithLimitationsCareList);
        infoShelterList.add(reasonsForRefusalFromDogList);
        infoShelterList.add(dogHandlerRecommendationsList);
        infoShelterList.add(phoneNumberList);

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
            case PUPPY_CARE:
                return PUPPY_CARE_ANSWER;
            case ADULT_DOG_CARE:
                return ADULT_DOG_CARE_ANSWER;
            case DOG_WITH_LIMITATIONS_CARE:
                return DOG_WITH_LIMITATIONS_CARE_ANSWER;
            case REASONS_FOR_REFUSAL_FROM_DOG:
                return REASONS_FOR_REFUSAL_FROM_DOG_ANSWER;
            case DOG_HANDLER_RECOMMENDATIONS:
                return getContactDogHandlers();
            default:
                return EXCEPTION;
        }
    }

   private String getContactDogHandlers() {
       List<DogHandler> dogHandlers = dogHandlerRepository.findAll();
       StringBuilder answerDogHandler = new StringBuilder();
       for (DogHandler dogHandler : dogHandlers) {
           answerDogHandler.append("Кинолог: ")
                   .append(dogHandler.getName())
                   .append(", номер: ")
                   .append(dogHandler.getPhoneNumber())
                   .append(", информация о нем: ")
                   .append(dogHandler.getInfo())
                   .append("\n");
       }
       return String.valueOf(answerDogHandler);
   }
}
