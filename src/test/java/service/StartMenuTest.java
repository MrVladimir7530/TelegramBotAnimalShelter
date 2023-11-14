package service;

import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.model_services.SubscriberServiceImpl;
import com.example.telegrambotanimalshelter.services.StartMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StartMenuTest {
    private SubscriberServiceImpl subscriberServiceMock;
    private StartMenu out;
    @BeforeEach
    public void init() {
        subscriberServiceMock = mock(SubscriberServiceImpl.class);
        out = new StartMenu(subscriberServiceMock);
    }


    @Test
    public void shouldCorrectResultFromMethodProcess() {
        when(subscriberServiceMock.create(any(Subscriber.class))).thenReturn(null);
        Update update = new Update();
        //создаем ссообщение для интеграции в Update
        Message message = new Message();

        //создаем чат для интеграции в  сообщения
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);

        //создаем пользователя для интеграции в сообщение(для метода createUser)
        User user = new User(1L, "John", false);
        user.setUserName("John Smith");
        message.setFrom(user);

        update.setMessage(message);

        SendMessage actualMessage= out.process(update);
        String textExpected = "Привет. Вы находитесь в стартовом меню бота приюта для животных." +
                " Пожалуйста, выберете приют или нажмите кнопку отправки отчета.";

        InlineKeyboardMarkup expectedInlineKeyboardMarkup = createInlineKeyboard();
        InlineKeyboardMarkup actualInlineKeyboardMarkup = (InlineKeyboardMarkup)actualMessage.getReplyMarkup();

        assertEquals(textExpected, actualMessage.getText());
        assertEquals(expectedInlineKeyboardMarkup, actualInlineKeyboardMarkup);

        verify(subscriberServiceMock, times(1)).create(any(Subscriber.class));


    }
    private InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют для кошек");
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют для собак");
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Отправить отчет");
        InlineKeyboardButton volunteerInputButton = new InlineKeyboardButton("Войти как волонтер");

        catButton.setCallbackData("CAT");
        dogButton.setCallbackData("DOG");
        reportButton.setCallbackData("REPORT");
        volunteerInputButton.setCallbackData("VOLUNTEER_INPUT");



        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
        catButtonList.add(catButton);
        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
        dogButtonList.add(dogButton);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(reportButton);
        List<InlineKeyboardButton> volunteerButtonList = new ArrayList<>();
        reportButtonList.add(volunteerInputButton);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(catButtonList);
        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);
        startButtonList.add(volunteerButtonList);


        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;
    }
}
