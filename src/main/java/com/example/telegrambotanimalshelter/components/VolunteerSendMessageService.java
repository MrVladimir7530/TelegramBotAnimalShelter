package com.example.telegrambotanimalshelter.components;

import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.models.Volunteer;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import com.example.telegrambotanimalshelter.repositories.VolunteerRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Random;

/**
 * Сервис отвечающий за отправку сообщий в тг волонтеру
 */
@Service
public class VolunteerSendMessageService {
    private Logger log = LoggerFactory.getLogger(VolunteerSendMessageService.class);
    private static final String ERROR = "ERROR: ";
    private final TelegramBot telegramBot;
    private final VolunteerRepository volunteerRepository;
    private final SubscriberRepository subscriberRepository;

    public VolunteerSendMessageService(@Lazy TelegramBot telegramBot, VolunteerRepository volunteerRepository, SubscriberRepository subscriberRepository) {
        this.telegramBot = telegramBot;
        this.volunteerRepository = volunteerRepository;
        this.subscriberRepository = subscriberRepository;
    }

    private int getChatId() {
        int count = (int) volunteerRepository.count();
        Random random = new Random();
        int check = random.nextInt(count);
        List<Volunteer> volunteers = volunteerRepository.findAll();
        int i = 0;
        for (Volunteer volunteer : volunteers) {
            if (i == check) {
                return (int) (long) volunteer.getChatId();
            } else {
                i++;
            }
        }
        return -1;
    }

    public void sendMessageToVolunteer(Long chatIdSubscriber) {
        int chatIdInt = getChatId();
        if (chatIdInt != -1) {
            String chatId = String.valueOf(chatIdInt);
            Subscriber subscriber = subscriberRepository.findByChatId(chatIdSubscriber);
            StringBuilder stringAnswer = new StringBuilder()
                    .append("Пожалуйста, свяжитесть с ")
                    .append(subscriber.getName())
                    .append(" по номеру телефону: ")
                    .append(subscriber.getPhoneNumber());
            SendMessage message = new SendMessage(chatId, stringAnswer.toString());
            try {
                telegramBot.execute(message);
                log.info(String.valueOf(HttpStatus.SC_OK));
            } catch (TelegramApiException e) {
                log.error(ERROR, e);
            }
        }
    }
}
