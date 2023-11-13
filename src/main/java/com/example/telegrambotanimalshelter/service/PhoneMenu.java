package com.example.telegrambotanimalshelter.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneMenu implements CommandHandler {
    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        String answer = "";
        if (update.hasCallbackQuery()) {
            message.setChatId(update.getCallbackQuery().getFrom().getId());
            answer = "оставьте номер";
        } else {
            message.setChatId(update.getMessage().getChatId());
            message.setReplyMarkup(createInlineKeyboard());
            answer = "Телефон успешно добавлен. Позвать волонтера?";
        }
        message.setText(answer);
        return message;
    }

    private InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton yes = new InlineKeyboardButton("Да");
        InlineKeyboardButton no = new InlineKeyboardButton("Нет");

        yes.setCallbackData("YES_VOLUNTEER");
        no.setCallbackData("NO_VOLUNTEER");

        List<InlineKeyboardButton> answerRow = new ArrayList<>();

        answerRow.add(yes);
        answerRow.add(no);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(answerRow);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
