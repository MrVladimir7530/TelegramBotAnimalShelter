package com.example.telegrambotanimalshelter.rellocation;

import com.example.telegrambotanimalshelter.CommandEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.telegrambotanimalshelter.service.*;
import lombok.RequiredArgsConstructor;
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
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    /**
     * Коллекция, в которой хранятся команды и сервисы для обработки команд
     */
    private final Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    private Logger log = LoggerFactory.getLogger(ReallocationOfTeamsImpl.class);
    private final StartMenu startMenu;
    private final AnimalMenu animalMenu;
    private final ShelterInformationService shelterInformationService;

    public ReallocationOfTeamsImpl(StartMenu startMenu, AnimalMenu animalMenu, ShelterInformationService shelterInformationService) {


        this.animalMenu = animalMenu;
        this.startMenu = startMenu;
        this.shelterInformationService = shelterInformationService;


    }
    @PostConstruct
    public void init() {
        commandHandlerMap.put("/start", startMenu);
        commandHandlerMap.put("/cancel", startMenu);
        commandHandlerMap.put("CAT", animalMenu);
        commandHandlerMap.put("DOG", animalMenu);

        commandHandlerMap.put("How_to_take_an_animal", shelterInformationService);
        commandHandlerMap.put("INFO_GET_ANIMAL", shelterInformationService);
        commandHandlerMap.put("INFO_NEED_DOCUMENTATION", shelterInformationService);
        commandHandlerMap.put("HELP_WITH_TRANSPORTATION_ANIMAL", shelterInformationService);
    }

    /**
     * Метод для распределения обновлений по сервисам. Возвращает сообщение для отправки пользователю.
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the ReallocationOfTeamsImpl class was called");
        SendMessage message = new SendMessage();

        if (update.hasCallbackQuery()){
            if(commandHandlerMap.containsKey(update.getCallbackQuery().getData())) {
                CommandHandler commandHandler = commandHandlerMap.get(update.getCallbackQuery().getData());
                message = commandHandler.process(update);
            }else {
                message.setChatId(update.getCallbackQuery().getFrom().getId());
                message.setText("Команда не распознана");
            }

        } else if (update.hasMessage() && commandHandlerMap.containsKey(update.getMessage().getText())) {

            CommandHandler commandHandler = commandHandlerMap.get(update.getMessage().getText());
            message = commandHandler.process(update);
        } else {

            message.setChatId(update.getMessage().getChatId());
            message.setText("Команда не распознана");
        }




        return message;
    }




}
