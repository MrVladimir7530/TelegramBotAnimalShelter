package com.example.telegrambotanimalshelter.rellocation;

import com.example.telegrambotanimalshelter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
    private final StartMenu startMenu;
    private final AnimalMenu animalMenu;
    private final ShelterInformationService shelterInformationService;

    public ReallocationOfTeamsImpl(StartMenu startMenu, AnimalMenu animalMenu, ShelterInformationService shelterInformationService) {

        this.animalMenu = animalMenu;
        this.startMenu = startMenu;
        this.shelterInformationService = shelterInformationService;

        commandHandlerMap.put("/start", startMenu);
        commandHandlerMap.put("/cancel", startMenu);
        commandHandlerMap.put("CAT", animalMenu);
        commandHandlerMap.put("How_to_take_an_animal", shelterInformationService);
        commandHandlerMap.put("INFO_GET_ANIMAL", shelterInformationService);
        commandHandlerMap.put("INFO_NEED_DOCUMENTATION", shelterInformationService);
        commandHandlerMap.put("HELP_WITH_TRANSPORTATION_ANIMAL", shelterInformationService);
    }

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasCallbackQuery()) {
            CommandHandler commandHandler = commandHandlerMap.get(update.getCallbackQuery().getData());
            message = commandHandler.process(update);

        } else {
            CommandHandler commandHandler = commandHandlerMap.get(update.getMessage().getText());
            message = commandHandler.process(update);
        }

        return message;
    }


}
