package com.example.telegrambotanimalshelter.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneMenuTest {

    private PhoneMenu phoneMenu;

    @BeforeEach
    public void init() {
        phoneMenu = new PhoneMenu();
    }

    public Update getUpdateWithText(String text) {
        Update update = new Update();

        Message message = new Message();
        message.setText(text);
        update.setMessage(message);

        return update;
    }
    @Test
    public void processTestOnHasCallbackQuery() {
        String expected = "оставьте номер";
        Update update = getUpdateWithText(expected);

        CallbackQuery callbackQuery = new CallbackQuery();
        User from = new User();
        from.setId(1L);
        callbackQuery.setFrom(from);
        update.setCallbackQuery(callbackQuery);

        SendMessage process = phoneMenu.process(update);
        String textResult = process.getText();
        assertEquals(expected, textResult);
    }

    @Test
    public void processTestOnHasMessage() {
        String expected = "Телефон успешно добавлен. Позвать волонтера?";
        Update update = getUpdateWithText(expected);

        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        update.getMessage().setChat(chat);
        message.setChat(chat);

        update.setMessage(message);
        SendMessage process = phoneMenu.process(update);
        String textResult = process.getText();

        assertEquals(expected, textResult);
    }
}
