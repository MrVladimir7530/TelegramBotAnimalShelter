package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.models.DogHandler;
import com.example.telegrambotanimalshelter.repositories.DogHandlerRepository;
import com.example.telegrambotanimalshelter.services.ShelterInformationDogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShelterInformationDogServiceTest {
    private ShelterInformationDogService shelterInformationDogService;
    private DogHandlerRepository dogHandlerRepository;

    @BeforeEach
    public void setUp() {
        dogHandlerRepository = Mockito.mock(DogHandlerRepository.class);
        shelterInformationDogService = new ShelterInformationDogService(dogHandlerRepository);

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
    public void processTest1() {
        Update update = getUpdate("INFO_GET_DOG");
        ReflectionTestUtils.setField(shelterInformationDogService, "INFO_GET_DOG_ANSWER", "Как понять, что животное выбрало тебя? Оно захочет убить тебя");
        SendMessage sendMessage = shelterInformationDogService.process(update);
        String response = sendMessage.getText();
        assertEquals("Как понять, что животное выбрало тебя? Оно захочет убить тебя", response);
    }
    @Test
    public void processTest2() {
        Update update = getUpdate("INFO_NEED_DOCUMENTATION_FOR_DOG");
        ReflectionTestUtils.setField(shelterInformationDogService, "INFO_NEED_DOCUMENTATION_FOR_DOG_ANSWER", "возми паспорт, желательно свой");
        SendMessage sendMessage = shelterInformationDogService.process(update);
        String response = sendMessage.getText();
        assertEquals("возми паспорт, желательно свой", response);
    }

    @Test
    public void getContactDogHandlersTest() {
        List<DogHandler> dogHandlers = new ArrayList<>();

        DogHandler dogHandler1 = new DogHandler();
        dogHandler1.setId(1L);
        dogHandler1.setName("Ivan");
        dogHandler1.setPhoneNumber("111111");
        dogHandler1.setInfo("text about dogHandler1");

        DogHandler dogHandler2 = new DogHandler();
        dogHandler2.setId(1L);
        dogHandler2.setName("Petr");
        dogHandler2.setPhoneNumber("222222");
        dogHandler2.setInfo("text about dogHandler2");

        dogHandlers.add(dogHandler1);
        dogHandlers.add(dogHandler2);

        Mockito.when(dogHandlerRepository.findAll()).thenReturn(dogHandlers);

        Update update = getUpdate("DOG_HANDLER_RECOMMENDATIONS");
        SendMessage sendMessage = shelterInformationDogService.process(update);

        String text = sendMessage.getText();
        String expected = "Кинолог: Ivan, номер: 111111, информация о нем: text about dogHandler1\n" +
                "Кинолог: Petr, номер: 222222, информация о нем: text about dogHandler2\n";
        assertEquals(expected, text);
    }
}
