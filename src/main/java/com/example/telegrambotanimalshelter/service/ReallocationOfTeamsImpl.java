package com.example.telegrambotanimalshelter.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    private final Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
    private final StartMenu startMenu;
    private final AnimalMenu animalMenu;

    public ReallocationOfTeamsImpl(StartMenu startMenu, AnimalMenu animalMenu) {

        this.animalMenu = animalMenu;
        this.startMenu = startMenu;

        commandHandlerMap.put("/start", startMenu);
        commandHandlerMap.put("/cancel", startMenu);
        commandHandlerMap.put("CAT", animalMenu);
        commandHandlerMap.put("DOG", animalMenu);
    }

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        if (update.hasCallbackQuery()) {
            CommandHandler commandHandler = commandHandlerMap.get(update.getCallbackQuery().getData());
            message = commandHandler.process(update);

        } else {
            if (!commandHandlerMap.containsKey(update.getMessage().getText())) {
                AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
                callbackQuery.setText("Команда не распознана");
                callbackQuery.setCacheTime(1000);
            }
            CommandHandler commandHandler = commandHandlerMap.get(update.getMessage().getText());
            message = commandHandler.process(update);
        }

        return message;
    }


}
