package service;

import com.example.telegrambotanimalshelter.service.AnimalMenu;
import com.example.telegrambotanimalshelter.service.ReportButtonAnswerService;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportButtonAnswerServiceTest {
    private final ReportButtonAnswerService out = new ReportButtonAnswerService();
    @Test
    public void shouldCorrectResultFromMethodProcessWhenCallbackDataIsCAT() {
        Update update = new Update();

        //создаем CallbackData для интеграции в  update
        CallbackQuery callbackQuery = new CallbackQuery();

        //создаем пользователя для интеграции в CallbackQuery
        User user = new User(1L, "John", false);
        user.setUserName("John Smith");

        callbackQuery.setFrom(user);
        callbackQuery.setData("REPORT");

        update.setCallbackQuery(callbackQuery);


        SendMessage actualMessage= out.process(update);
        String textExpected = "Меню ежедневного отчета";

        InlineKeyboardMarkup expectedInlineKeyboardMarkup = createKeyboardMarkup(update);
        InlineKeyboardMarkup actualInlineKeyboardMarkup = (InlineKeyboardMarkup)actualMessage.getReplyMarkup();

        assertEquals(textExpected, actualMessage.getText());
        assertEquals(expectedInlineKeyboardMarkup, actualInlineKeyboardMarkup);
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
