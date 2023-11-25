package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.services.AnimalMenu;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalMenuTest {

    private final AnimalMenu out = new AnimalMenu();
    @Test
    public void shouldCorrectResultFromMethodProcessWhenCallbackDataIsCAT() {
        Update update = new Update();

        //создаем CallbackData для интеграции в  update
        CallbackQuery callbackQuery = new CallbackQuery();

        //создаем ссообщение для интеграции в CallbackQuery
        Message message = new Message();
        message.setText("");

        //создаем пользователя для интеграции в CallbackQuery
        User user = new User(1L, "John", false);
        user.setUserName("John Smith");
        callbackQuery.setFrom(user);

        callbackQuery.setMessage(message);
        callbackQuery.setData("CAT");

        update.setCallbackQuery(callbackQuery);


        SendMessage actualMessage= out.process(update);
        String textExpected = "Вы находитесь в меню информации о приюте. Выберете интересующию вас информацию ";

        InlineKeyboardMarkup expectedInlineKeyboardMarkup = createKeyboardMarkup(update);
        InlineKeyboardMarkup actualInlineKeyboardMarkup = (InlineKeyboardMarkup)actualMessage.getReplyMarkup();

        assertEquals(textExpected, actualMessage.getText());
        assertEquals(expectedInlineKeyboardMarkup, actualInlineKeyboardMarkup);
    }
 @Test
    public void shouldCorrectResultFromMethodProcessWhenCallbackDataIsDOG() {
        Update update = new Update();

        //создаем CallbackData для интеграции в  update
        CallbackQuery callbackQuery = new CallbackQuery();

        //создаем ссообщение для интеграции в CallbackQuery
        Message message = new Message();
        message.setText("");

        //создаем пользователя для интеграции в CallbackQuery
        User user = new User(1L, "John", false);
        user.setUserName("John Smith");
        callbackQuery.setFrom(user);

        callbackQuery.setMessage(message);
        callbackQuery.setData("DOG");

        update.setCallbackQuery(callbackQuery);


        SendMessage actualMessage= out.process(update);
        String textExpected = "Вы находитесь в меню информации о приюте. Выберете интересующию вас информацию ";

        InlineKeyboardMarkup expectedInlineKeyboardMarkup = createKeyboardMarkup(update);
        InlineKeyboardMarkup actualInlineKeyboardMarkup = (InlineKeyboardMarkup)actualMessage.getReplyMarkup();

        assertEquals(textExpected, actualMessage.getText());
        assertEquals(expectedInlineKeyboardMarkup, actualInlineKeyboardMarkup);
    }


    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton getInfoAboutShelter = new InlineKeyboardButton("Узнать информацию о приюте");
        InlineKeyboardButton informationAboutAnimalAdoption = new InlineKeyboardButton("Как взять животное из приюта");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Оставить контакты и позвать волонтера");

        if (update.getCallbackQuery().getData().equals("CAT")) {
            getInfoAboutShelter.setCallbackData("CAT_INFO");
            informationAboutAnimalAdoption.setCallbackData("HOW_TO_TAKE_CAT");

        } else {

            getInfoAboutShelter.setCallbackData("DOG_INFO");
            informationAboutAnimalAdoption.setCallbackData("HOW_TO_TAKE_DOG");
        }
        callVolunteer.setCallbackData(out.LEAVE_CONTACTS);

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

}
