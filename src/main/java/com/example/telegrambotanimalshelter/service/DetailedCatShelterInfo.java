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
public class DetailedCatShelterInfo implements CommandHandler {
    @Autowired
    private ShelterRepository shelterRepository;

    /**
     * Метод для обработки входящего сообщения от пользователя и возврата сообщения для отправки
     * Меню подробной информации о приюте кошек
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of DetailedCatShelterInfo was called");

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
        String textMessage;
        String animal = "cat";
        Shelter shelter = shelterRepository.findByTextKey(animal);

        switch (update.getCallbackQuery().getData()) {
            case "GENERAL_INFO_ABOUT_CAT_SHELTER":
                textMessage = shelter.getGeneralInfo();
                break;
            case "ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER":
                textMessage = shelter.getInfo();
                break;
            case "SECURITY_INFO_OF_CAT_SHELTER":
                textMessage = shelter.getSecurityContact();
                break;
            case "CAT_SAFETY_RECOMMENDATIONS":
                textMessage = shelter.getSafetyPrecautions();
                break;
            default:
                textMessage = "Команда не распознана";
        }

        return textMessage;
    }
}
