package service;

import com.example.telegrambotanimalshelter.services.ChoiceOfAnimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChoiceOfAnimalTest {
    private ChoiceOfAnimal choiceOfAnimal;

    @BeforeEach
    public void init() {
        choiceOfAnimal = new ChoiceOfAnimal();
    }

    @Test
    public void processTest() {
        String text = "Привет. Вы находитесь на стадии выбора животного.";
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1L);
        message.setChat(chat);
        message.setText(text);
        update.setMessage(message);
        SendMessage process = choiceOfAnimal.process(update);
        String text1 = process.getText();
        assertEquals(text1, text);
    }
}
