package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.component.ChooseWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.bots.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
/**
 * Сервис для обработки команды /start.
 */
public class StartMenu implements CommandHandler{

    /**
     * Метод для обработки входящего обновления и возврата сообщения
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setReplyMarkup(createInlineKeyboard());
        message.setText(createText());
        return message;
    }

    /**
     * Метод для создания и возврата инлайн клавиатуры
     * @return InlineKeyboardMarkup
     */
    private InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют для кошек");
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют для собак");
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Отправить отчет");

        catButton.setCallbackData("CAT");
        dogButton.setCallbackData("DOG");
        reportButton.setCallbackData("REPORT");


        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
        catButtonList.add(catButton);
        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
        dogButtonList.add(dogButton);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(reportButton);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(catButtonList);
        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);

        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;


    }

    private String createText() {
        return "Привет. Вы находитесь в стартовом меню бота приюта для животных." +
                " Пожалуйста, выберете приют или нажмите кнопку отправки отчета.";
    }
}