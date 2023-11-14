package com.example.telegrambotanimalshelter.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChoiceOfAnimal implements CommandHandler{
    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setReplyMarkup(createInlineKeyboard());
        message.setText(createText());
        return message;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют для кошек");
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют для собак");

        catButton.setCallbackData("CAT");
        dogButton.setCallbackData("DOG");

        List<InlineKeyboardButton> choiceOfAnimalButton = new ArrayList<>();
        choiceOfAnimalButton.add(catButton);
        choiceOfAnimalButton.add(dogButton);

        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(choiceOfAnimalButton));
        return inlineKeyboardMarkup;
    }

    public String createText() {
        return "Привет. Вы находитесь на стадии выбора животного.";
    }
}
