package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class PhoneNumberFromMessage {

    private final SubscriberRepository subscriberRepository;
    private final Pattern PATTERN = Pattern.compile("(^\\d{11}$)");
    public boolean parsingPhone(Update update) {
        String requestText = update.getMessage().getText();
        Matcher matcher = PATTERN.matcher(requestText);
        return matcher.find();
    }

    public Subscriber savePhone(Update update) {
        String phone = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Subscriber subscriber = subscriberRepository.findByChatId(chatId);
        subscriber.setPhoneNumber(phone);
        return subscriberRepository.save(subscriber);
    }
}
