package com.example.telegrambotanimalshelter.services;

import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GeneralInfoDogShelterServiceTest {

    private ShelterRepository shelterRepository = Mockito.mock(ShelterRepository.class);
    private GeneralInfoDogShelterService generalInfoDogShelterService = new GeneralInfoDogShelterService(shelterRepository);

    @Test
    public void chooseWayDogInfoTest() {
        Update update = getUpdate();
        update.getCallbackQuery().setData("DOG_INFO");

        String expected = "Вы находитесь с разделе общей информации о приюте для собак. Пожалуйста, выберите, о чем вы бы хотели узнать подробнее";

        SendMessage result = generalInfoDogShelterService.process(update);

        Assertions.assertEquals(expected, result.getText());
    }

    @Test
    public void chooseWayGeneralInfoDogTest() {
        String infoButton = "Инфо о собаках";
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("Приют собак");
        shelter.setGeneralInfo(infoButton);
        when(shelterRepository.findByName(any(String.class))).thenReturn(shelter);

        Update update = getUpdate();
        update.getCallbackQuery().setData("GENERAL_INFO_ABOUT_DOG_SHELTER");

        String expected = infoButton;

        SendMessage result = generalInfoDogShelterService.process(update);

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
