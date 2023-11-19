package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.components.VolunteerSendMessageService;
import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * сервис который парсит сообщение в номер телефона
 */
@Service
@RequiredArgsConstructor
public class PhoneNumberFromMessage {
    private Logger log = LoggerFactory.getLogger(PhoneNumberFromMessage.class);
    private final SubscriberRepository subscriberRepository;
    /**
     * регулярное выражение по которой происходит парсинг номера телефона
     */
    private final Pattern PATTERN = Pattern.compile("(^\\d{11}$)");

    public boolean parsingPhone(Update update) {
        String requestText = update.getMessage().getText();
        Matcher matcher = PATTERN.matcher(requestText);
        return matcher.find();
    }

    public Subscriber savePhone(Update update) {
        String phone = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        log.info("save phone in repository");
        Subscriber subscriber = subscriberRepository.findByChatId(chatId);
        subscriber.setPhoneNumber(phone);
        return subscriberRepository.save(subscriber);
    }
}
