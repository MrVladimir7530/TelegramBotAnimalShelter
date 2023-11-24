package model_services;

import com.example.telegrambotanimalshelter.model_services.PhoneNumberFromMessage;
import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PhoneNumberFromMessageTest {

    private PhoneNumberFromMessage phoneNumberFromMessage;
    private SubscriberRepository subscriberRepository;

    @BeforeEach
    public void init() {
        subscriberRepository = Mockito.mock(SubscriberRepository.class);
        phoneNumberFromMessage = new PhoneNumberFromMessage(subscriberRepository);
    }
    public Update getUpdateWithText(String text) {
        Update update = new Update();
        Message message = new Message();
        message.setText(text);
        update.setMessage(message);
        return update;
    }
    @Test
    public void parsingPhoneTest1() {
        Update update = getUpdateWithText("12345678901");
        boolean result = phoneNumberFromMessage.parsingPhone(update);
        assertTrue(result);
    }

    @Test
    public void parsingPhoneTest2() {
        Update update = getUpdateWithText("1234567890F");
        boolean result = phoneNumberFromMessage.parsingPhone(update);
        assertFalse(result);
    }

    @Test
    public void parsingPhoneTest3() {
        Update update = getUpdateWithText("1212345678901");
        boolean result = phoneNumberFromMessage.parsingPhone(update);
        assertFalse(result);
    }
    @Test
    public void parsingPhoneTest4() {
        Update update = getUpdateWithText("12 12345678901");
        boolean result = phoneNumberFromMessage.parsingPhone(update);
        assertFalse(result);
    }

    @Test
    public void savePhoneTest() {
        Subscriber subscriber = new Subscriber(1L, "Ivan", "222222", "Ivan");
        when(subscriberRepository.findByChatId(any())).thenReturn(subscriber);
        when(subscriberRepository.save(any())).thenReturn(subscriber);

        Update update = getUpdateWithText("text");
        Message message = update.getMessage();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        update.setMessage(message);
        Subscriber receivedSubscriber = phoneNumberFromMessage.savePhone(update);
        assertEquals(receivedSubscriber, subscriber);
    }

}
