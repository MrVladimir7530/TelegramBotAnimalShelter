package com.example.telegrambotanimalshelter.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportButtonAnswerService implements CommandHandler {
    Logger log = LoggerFactory.getLogger(ReportButtonAnswerService.class);
    private final TelegramBot telegramBot;


    public ReportButtonAnswerService(@Lazy TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
//    public ReportButtonAnswerService() {
//
//    }


    /**
     * Метод для обработки входящего обновления и возврата сообщения
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the ReportButtonAnswerService class was called"+"CallBackData = "
                +update.getCallbackQuery().getData());
        if (update.getCallbackQuery().getData().equals("Daily_Report_Form")) {
            String callbackQueryId = update.getCallbackQuery().getId();
            boolean alert = true;
            String text = "Для отправки ежедневного отчета отправьте фотографию и описание к ней";
            sendAnswerCallBackQuery(callbackQueryId, alert, text);
            throw new RuntimeException("Так было надо))");
        }
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
    private void sendAnswerCallBackQuery(String id, boolean alert, String text){
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(id);
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        try {
            telegramBot.execute(answerCallbackQuery);
        } catch (TelegramApiException e) {
            System.out.println("Чет не отправился ответ((");
        }
    }



}
