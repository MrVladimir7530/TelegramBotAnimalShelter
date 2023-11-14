package com.example.telegrambotanimalshelter.components;

import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import com.example.telegrambotanimalshelter.repositories.VolunteerRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class VolunteerSendMessageService {
    private Logger log = LoggerFactory.getLogger(VolunteerSendMessageService.class);
    private static final String ERROR = "ERROR: ";
    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;
    private final SubscriberRepository subscriberRepository;

    private int getChatId() {
        int count = (int) volunteerRepository.count();
        Random random = new Random();
        return random.nextInt(count);
    }
    public void sendMessageToVolunteer(Long chatIdSubscriber) {
        String chatId = String.valueOf(getChatId());
        Subscriber subscriber = subscriberRepository.findByChatId(chatIdSubscriber);
        StringBuilder stringAnswer = new StringBuilder()
                .append("Пожалуйста, свяжитесть с ")
                .append(subscriber.getName())
                .append(" по номеру телефону: ")
                .append(subscriber.getPhoneNumber());
        SendMessage message = new SendMessage(chatId ,stringAnswer.toString());
        //todo реализовать отправку сообщений execute
        try {
            telegramBot.execute(message);
            log.info(String.valueOf(HttpStatus.SC_OK));
        } catch (TelegramApiException e) {
            log.error(ERROR, e);
        }
    }
}
