package com.example.telegrambotanimalshelter.service;

import com.example.telegrambotanimalshelter.model.Shelter;
import com.example.telegrambotanimalshelter.repository.ShelterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class DetailedShelterInfo implements CommandHandler {

    @Autowired
    private ShelterRepository shelterRepository;

    /**
     * Метод для обработки входящего сообщения от пользователя и возврата сообщения для отправки
     * Меню подробной информации о приютах
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of DetailedShelterInfo was called");

        SendMessage message = new SendMessage();

        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setText(getInfoAboutShelter(update));

        return message;
    }

    /**
     * Метод для обработки входящего сообщения от пользователя и возврата информации
     * @param update
     * @return String
     */
    private String getInfoAboutShelter(Update update) {
        log.info("Method getInfoAboutShelter of DetailedShelterInfo was called");

        String textMessage;

        Shelter shelter = new Shelter();
        if (update.getCallbackQuery().getData().contains("Cat")) {
            shelter = shelterRepository.findByTextKey("cat");
        } else shelter = shelterRepository.findByTextKey("dog");

        switch (update.getCallbackQuery().getData()) {
            case "GeneralInfoAboutCatShelter":
            case "GeneralInfoAboutDogShelter":
                textMessage = shelter.getGeneralInfo();
                break;
            case "AddressAndScheduleOfCatShelter":
            case "AddressAndScheduleOfDogShelter":
                textMessage = shelter.getInfo();
                break;
            case "SecurityInfoOfCatShelter":
            case "SecurityInfoOfDogShelter":
                textMessage = shelter.getSecurityContact();
                break;
            case "CatSafetyRecommendations":
            case "DogSafetyRecommendations":
                textMessage = shelter.getSafetyPrecautions();
                break;
            default:
                textMessage = "Команда не распознана";
        }

        return textMessage;
    }
}
