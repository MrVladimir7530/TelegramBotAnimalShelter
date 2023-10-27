package com.example.telegrambotanimalshelter.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
@RequiredArgsConstructor
public class CommandHandlerImpl implements CommandHandler {
    private final StartMenu startMenu;

    @Override
    public SendMessage process(Update update) {

        String text = null;
        InlineKeyboardMarkup inlineKeyboardMarkup = null;

        if (update.getMessage().getText().equals("/start")) {
            inlineKeyboardMarkup = startMenu.returnInlineKeyboard();
            text = startMenu.returnString();

        }

        return createMessage(update, text, inlineKeyboardMarkup);
    }

    private SendMessage createMessage(Update update, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(text);
        if (inlineKeyboardMarkup != null) {

            message.setReplyMarkup(inlineKeyboardMarkup);
        }
        return message;
    }

}
