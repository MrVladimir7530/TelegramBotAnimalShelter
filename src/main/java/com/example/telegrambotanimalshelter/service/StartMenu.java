package com.example.telegrambotanimalshelter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StartMenu implements CommandHandler{


    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setReplyMarkup(createInlineKeyboard());
        message.setText(createText());
        return message;
    }

    public String check(Update update) {
        switch (update.getMessage().getText()) {
        }
        return null;
    }

    public InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют для кошек");
//        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют для собак");
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Отправить отчет");
        InlineKeyboardButton choiceOfAnimalButton = new InlineKeyboardButton("Выбрать животное");

//        catButton.setCallbackData("CAT");
//        dogButton.setCallbackData("DOG");
        reportButton.setCallbackData("REPORT");
        choiceOfAnimalButton.setCallbackData("/choiceOfAnimal");

//        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
//        catButtonList.add(catButton);
//        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
//        dogButtonList.add(dogButton);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(reportButton);

        List<InlineKeyboardButton> choiceButtonList = new ArrayList<>();
        choiceButtonList.add(choiceOfAnimalButton);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
//        startButtonList.add(catButtonList);
//        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);
        startButtonList.add(choiceButtonList);

        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;


    }

    public String createText() {
        return "Привет. Вы находитесь в стартовом меню бота приюта для животных." +
                " Пожалуйста, выберете приют или нажмите кнопку отправки отчета.";
    }
}