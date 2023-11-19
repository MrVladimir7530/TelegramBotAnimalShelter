package service;

import com.example.telegrambotanimalshelter.services.ReportButtonAnswerService;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportButtonAnswerServiceTest {

    private final ReportButtonAnswerService out;
    private final TelegramBot telegramBotMock;

    public ReportButtonAnswerServiceTest() {
        telegramBotMock = Mockito.mock(TelegramBot.class);
        out = new ReportButtonAnswerService(telegramBotMock);
    }
    @Test
    public void shouldCorrectResultFromMethodProcessWhenCallbackDataIsREPORT() {
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


//        InlineKeyboardMarkup expectedInlineKeyboardMarkup = createKeyboardMarkup(update);
        InlineKeyboardMarkup actualInlineKeyboardMarkup = (InlineKeyboardMarkup)actualMessage.getReplyMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtonListList= actualInlineKeyboardMarkup.getKeyboard();

        boolean buttonDailyReportFormExist = false;
        for (List<InlineKeyboardButton>inlineKeyboardButtonList: inlineKeyboardButtonListList) {
            for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtonList) {
                if (inlineKeyboardButton.getCallbackData().equals("Daily_Report_Form")) {
                    buttonDailyReportFormExist = true;
                    return;
                }

            }

        }
        boolean buttonCall_VolunteerExist = false;
        for (List<InlineKeyboardButton>inlineKeyboardButtonList: inlineKeyboardButtonListList) {
            for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtonList) {
                if (inlineKeyboardButton.getCallbackData().equals("Call_VolunteerExist")) {
                    buttonCall_VolunteerExist = true;
                    return;
                }

            }

        }
        boolean buttonCancelExist = false;
        for (List<InlineKeyboardButton>inlineKeyboardButtonList: inlineKeyboardButtonListList) {
            for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtonList) {
//                if (inlineKeyboardButton.getCallbackData().equals("/cancel")) {
//                    buttonCancelExist = true;
//                    return;
//                }

            }

        }
//        boolean buttonDailyReportFormExist;
//                actualInlineKeyboardMarkup.getKeyboard()//List<List<InlineKeyboardButton>>
//                .listIterator()//List<InlineKeyboardButton>
//                .forEachRemaining(
//                        x->x.listIterator().//InlineKeyboardButton
//                                forEachRemaining(
//                                y->y.getCallbackData().contains("Форма ежедневного отчета")));
        assertTrue(buttonCancelExist);
        assertEquals(textExpected, actualMessage.getText());
        assertTrue(buttonDailyReportFormExist);
        assertTrue(buttonCall_VolunteerExist);

    }

//    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//
//        InlineKeyboardButton dailyReportForm = new InlineKeyboardButton("Форма ежедневного отчета");
//        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");
//        InlineKeyboardButton cancel = new InlineKeyboardButton("Выход");
//
//
//        dailyReportForm.setCallbackData("Daily_Report_Form");
//        callVolunteer.setCallbackData("Call_Volunteer");
//        cancel.setCallbackData("/cancel");
//
//        List<InlineKeyboardButton> dailyReportFormList = new ArrayList<>();
//        dailyReportFormList.add(dailyReportForm);
//        List<InlineKeyboardButton> callVolunteerList = new ArrayList<>();
//        callVolunteerList.add(callVolunteer);
//        List<InlineKeyboardButton> cancelButtonList = new ArrayList<>();
//        cancelButtonList.add(cancel);
//
//        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
//        startButtonList.add(dailyReportFormList);
//        startButtonList.add(callVolunteerList);
//        startButtonList.add(cancelButtonList);
//
//        inlineKeyboardMarkup.setKeyboard(startButtonList);
//        return  inlineKeyboardMarkup;
//
//
//    }
}
