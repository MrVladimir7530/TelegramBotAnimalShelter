package com.example.telegrambotanimalshelter.rellocation;

import com.example.telegrambotanimalshelter.service.AnimalMenu;
import com.example.telegrambotanimalshelter.service.ChoiceOfAnimal;
import com.example.telegrambotanimalshelter.service.CommandHandler;
import com.example.telegrambotanimalshelter.service.StartMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReallocationOfTeamsImpl implements ReallocationOfTeams {
    private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();
    private final StartMenu startMenu;
    private final AnimalMenu animalMenu;
    private final ChoiceOfAnimal choiceOfAnimal;


    @PostConstruct
    public void configReallocationOfTeamsImpl() {
        commandHandlerMap.put("/start", startMenu);
        commandHandlerMap.put("/cancel", startMenu);
        commandHandlerMap.put("/choiceOfAnimal", choiceOfAnimal);
        commandHandlerMap.put("CAT", choiceOfAnimal);
        commandHandlerMap.put("DOG", choiceOfAnimal);
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
