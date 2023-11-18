package com.example.telegrambotanimalshelter.rellocations;

import com.example.telegrambotanimalshelter.model_services.PhoneNumberFromMessage;
import com.example.telegrambotanimalshelter.model_services.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.telegrambotanimalshelter.services.*;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для обработки перераспределения входящих обновлений по сервисам в зависимости от команды.
 */
@Service
@RequiredArgsConstructor
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    /**
     * Коллекция, в которой хранятся команды и сервисы для обработки команд
     */
    private final Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    private Logger log = LoggerFactory.getLogger(ReallocationOfTeamsImpl.class);
    private final StartMenu startMenu;
    private final AnimalMenu animalMenu;
    private final GeneralInfoCatShelterService generalInfoCatShelterService;
    private final GeneralInfoDogShelterService generalInfoDogShelterService;
    private final ShelterInformationDogService shelterInformationDogService;
    private final ShelterInformationCatService shelterInformationCatService;
    private final ReportButtonAnswerService reportButtonAnswerService;
    private final PhoneNumberFromMessage phoneNumberFromMessage;
    private final PhoneMenu phoneMenu;
    private final ReportService reportService;

    @PostConstruct
    public void init() {
        commandHandlerMap.put("/start", startMenu);
        commandHandlerMap.put("/cancel", startMenu);
        commandHandlerMap.put("CAT", animalMenu);
        commandHandlerMap.put("DOG", animalMenu);
        commandHandlerMap.put("REPORT", reportButtonAnswerService);
        commandHandlerMap.put("LEAVE_CONTACTS", phoneMenu);
        commandHandlerMap.put("YES_VOLUNTEER", startMenu);
        commandHandlerMap.put("NO_VOLUNTEER", startMenu);

        commandHandlerMap.put("HOW_TO_TAKE_CAT", shelterInformationCatService);
        commandHandlerMap.put("INFO_GET_CAT", shelterInformationCatService);
        commandHandlerMap.put("INFO_NEED_DOCUMENTATION_FOR_CAT", shelterInformationCatService);
        commandHandlerMap.put("HELP_WITH_TRANSPORTATION_CAT", shelterInformationCatService);
        commandHandlerMap.put("KITTY_CARE", shelterInformationCatService);
        commandHandlerMap.put("ADULT_CAT_CARE", shelterInformationCatService);
        commandHandlerMap.put("CAT_WITH_LIMITATIONS_CARE", shelterInformationCatService);
        commandHandlerMap.put("REASONS_FOR_REFUSAL_FROM_CAT", shelterInformationCatService);

        commandHandlerMap.put("HOW_TO_TAKE_DOG", shelterInformationDogService);
        commandHandlerMap.put("INFO_GET_DOG", shelterInformationDogService);
        commandHandlerMap.put("INFO_NEED_DOCUMENTATION_FOR_DOG", shelterInformationDogService);
        commandHandlerMap.put("HELP_WITH_TRANSPORTATION_DOG", shelterInformationDogService);
        commandHandlerMap.put("DOG_HANDLER_ADVICE_BY_TAKE", shelterInformationDogService);
        commandHandlerMap.put("DOG_HANDLER_ADVICE_BY_CARE", shelterInformationDogService);
        commandHandlerMap.put("PUPPY_CARE", shelterInformationDogService);
        commandHandlerMap.put("ADULT_DOG_CARE", shelterInformationDogService);
        commandHandlerMap.put("DOG_WITH_LIMITATIONS_CARE", shelterInformationDogService);
        commandHandlerMap.put("REASONS_FOR_REFUSAL_FROM_DOG", shelterInformationDogService);
        commandHandlerMap.put("DOG_HANDLER_RECOMMENDATIONS", shelterInformationDogService);

        commandHandlerMap.put("CAT_INFO", generalInfoCatShelterService);
        commandHandlerMap.put("GENERAL_INFO_ABOUT_CAT_SHELTER", generalInfoCatShelterService);
        commandHandlerMap.put("ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER", generalInfoCatShelterService);
        commandHandlerMap.put("SECURITY_INFO_OF_CAT_SHELTER", generalInfoCatShelterService);
        commandHandlerMap.put("CAT_SAFETY_RECOMMENDATIONS", generalInfoCatShelterService);

        commandHandlerMap.put("DOG_INFO", generalInfoDogShelterService);
        commandHandlerMap.put("GENERAL_INFO_ABOUT_DOG_SHELTER", generalInfoDogShelterService);
        commandHandlerMap.put("ADDRESS_AND_SCHEDULE_OF_DOG_SHELTER", generalInfoDogShelterService);
        commandHandlerMap.put("SECURITY_INFO_OF_DOG_SHELTER", generalInfoDogShelterService);
        commandHandlerMap.put("DOG_SAFETY_RECOMMENDATIONS", generalInfoDogShelterService);


    }

    /**
     * Метод для распределения обновлений по сервисам. Возвращает сообщение для отправки пользователю.
     *
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the ReallocationOfTeamsImpl class was called");
        SendMessage message = new SendMessage();

        if (update.hasCallbackQuery()) {
            if (commandHandlerMap.containsKey(update.getCallbackQuery().getData())) {
                CommandHandler commandHandler = commandHandlerMap.get(update.getCallbackQuery().getData());
                message = commandHandler.process(update);
            } else {
                message.setChatId(update.getCallbackQuery().getFrom().getId());
                message.setText("Команда не распознана");
            }

        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            message = reportService.process(update);
        } else if (update.hasMessage() && commandHandlerMap.containsKey(update.getMessage().getText())) {
            CommandHandler commandHandler = commandHandlerMap.get(update.getMessage().getText());
            message = commandHandler.process(update);
        } else if (phoneNumberFromMessage.parsingPhone(update)) {
            phoneNumberFromMessage.savePhone(update);
            message = phoneMenu.process(update);
        } else {

            message.setChatId(update.getMessage().getChatId());
            message.setText("Команда не распознана");
        }

        return message;
    }


}
