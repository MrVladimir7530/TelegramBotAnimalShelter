package com.example.telegrambotanimalshelter.services;

import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GeneralInfoCatShelterServiceTest {
    private ShelterRepository shelterRepository = Mockito.mock(ShelterRepository.class);
    private GeneralInfoCatShelterService generalInfoCatShelterService = new GeneralInfoCatShelterService(shelterRepository);

    @Test
    public void chooseWayCatInfoTest() {
        Update update = getUpdate();
        update.getCallbackQuery().setData("CAT_INFO");

        String text = "Вы находитесь с разделе общей информации о приюте кошек. Пожалуйста, выберите, о чем вы бы хотели узнать подробнее";

        SendMessage process = generalInfoCatShelterService.process(update);

        Assertions.assertEquals(text, process.getText());

    }

    @Test
    public void chooseWaySecurityInfoCatTest() {
        String infoButton = "Контакты охраны";
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("Приют кошек");
        shelter.setSecurityContact(infoButton);
        when(shelterRepository.findByName(any(String.class))).thenReturn(shelter);

        Update update = getUpdate();
        update.getCallbackQuery().setData("SECURITY_INFO_OF_CAT_SHELTER");

        String expected = infoButton;

        SendMessage result = generalInfoCatShelterService.process(update);

        Assertions.assertEquals(expected, result.getText());
    }

    public Update getUpdate() {
        Update update = new Update();
        CallbackQuery callbackQuery = new CallbackQuery();
        User user = new User();
        user.setId(1L);
        callbackQuery.setFrom(user);
        update.setCallbackQuery(callbackQuery);

        return update;
    }
}
