package com.example.telegrambotanimalshelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportButtonAnswerService implements CommandHandler {
    Logger log = LoggerFactory.getLogger(ReportButtonAnswerService.class);

    /**
     * Метод для обработки входящего обновления и возврата сообщения
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the ReportButtonAnswerService class was called"+"CallBackData = "
                +update.getCallbackQuery().getData());
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createKeyboardMarkup(update));
        message.setText(createText());

        return message;
    }

    private String createText() {
        return "Меню ежедневного отчета";
    }

    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton dailyReportForm = new InlineKeyboardButton("Форма ежедневного отчета");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");
        InlineKeyboardButton cancel = new InlineKeyboardButton("Выход");


        dailyReportForm.setCallbackData("Daily_Report_Form");
        callVolunteer.setCallbackData("Call_Volunteer");
        cancel.setCallbackData("/cancel");

        List<InlineKeyboardButton> dailyReportFormList = new ArrayList<>();
        dailyReportFormList.add(dailyReportForm);
        List<InlineKeyboardButton> callVolunteerList = new ArrayList<>();
        callVolunteerList.add(callVolunteer);
        List<InlineKeyboardButton> cancelButtonList = new ArrayList<>();
        cancelButtonList.add(cancel);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(dailyReportFormList);
        startButtonList.add(callVolunteerList);
        startButtonList.add(cancelButtonList);

        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;


    }


}
