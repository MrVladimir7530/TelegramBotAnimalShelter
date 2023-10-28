package com.example.telegrambotanimalshelter.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalMenu implements CommandHandler {


    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());

        return message;
    }

    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton getInfoAboutShelter = new InlineKeyboardButton("Узнать информацию о приюте");
        InlineKeyboardButton informationAboutAnimalAdoption = new InlineKeyboardButton("Как взять животное из приюта");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");
        if (update.getCallbackQuery().getData().equals("CAT")) {
            getInfoAboutShelter.setCallbackData("CATINFO");

        } else {

            getInfoAboutShelter.setCallbackData("DOGINFO");
        }
        informationAboutAnimalAdoption.setCallbackData("DOG");
        callVolunteer.setCallbackData("REPORT");

        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
        catButtonList.add(getInfoAboutShelter);
        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
        dogButtonList.add(informationAboutAnimalAdoption);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(callVolunteer);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(catButtonList);
        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);

        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;


    }



    private String infoCatShelter() {
        return "Какая-то информация о приюте кошек";

    }
    private String infoDogShelter() {
        return "Какая-то информация о приюте собак";

    }
}
