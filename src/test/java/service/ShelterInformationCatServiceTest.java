package service;

import com.example.telegrambotanimalshelter.services.ShelterInformationCatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.*;



public class ShelterInformationCatServiceTest {

    private ShelterInformationCatService shelterInformationCatService;

    @BeforeEach
    public void setUp() {
        shelterInformationCatService = new ShelterInformationCatService();

    }

    public Update getUpdate(String text) {
        Update update = new Update();
        CallbackQuery callbackQuery = new CallbackQuery();
        update.setCallbackQuery(callbackQuery);
        update.getCallbackQuery().setFrom(new User());
        update.getCallbackQuery().getFrom().setId(1318507437L);
        update.getCallbackQuery().setData(text);
        return update;
    }

    @Test
    public void test1() {
        Update update = getUpdate("HOW_TO_TAKE_CAT");
        ReflectionTestUtils.setField(shelterInformationCatService, "HOW_TO_TAKE_CAT_ANSWER", "Добро пожаловать в инфо о кошках");
        SendMessage sendMessage = shelterInformationCatService.process(update);
        String response = sendMessage.getText();
        assertEquals("Добро пожаловать в инфо о кошках", response);
    }
    @Test
    public void test2() {
        Update update = getUpdate("KITTY_CARE");
        ReflectionTestUtils.setField(shelterInformationCatService, "KITTY_CARE_ANSWER", "уход за котенком");
        SendMessage sendMessage = shelterInformationCatService.process(update);
        String response = sendMessage.getText();
        assertEquals("уход за котенком", response);
    }

}
