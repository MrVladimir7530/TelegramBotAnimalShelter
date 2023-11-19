package com.example.telegrambotanimalshelter.model_services;


import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AdopterServiceImpl implements AdopterService {
    Logger logger = LoggerFactory.getLogger(AdopterServiceImpl.class);
    private final TelegramBot telegramBot;
    private final AdopterRepository adopterRepository;

    public AdopterServiceImpl(@Lazy TelegramBot telegramBot, AdopterRepository adopterRepository) {
        this.telegramBot = telegramBot;
        this.adopterRepository = adopterRepository;
    }

    public Adopter findById(Long id) {
        logger.info("Was invoked method for findById of Adopter by ID");
        return adopterRepository.findById(id).get();

    }

    public Adopter findBySubscriberId(Long id) {
        logger.info("Was invoked method for find of Adopter by Subscriber Chat_ID");
        return adopterRepository.findBySubscriberChatId(id);
    }

    @Override
    public List<Adopter> getActualAdopter() {
        logger.info("Was invoked method for get of actual Adopters");
        return adopterRepository.getActualAdopter();
    }

    @Override
    public Adopter create(Adopter adopter) {
        logger.info("Was invoked method for create of Adopter");
        return adopterRepository.save(adopter);
    }

    @Override
    public Adopter editTrialPeriod(Long adopterId, Integer days, Boolean probationPeriodPassed) {
        logger.info("Was invoked method for edit trial period of Adopter");
        Adopter adopter = null;
        try {
            adopter = adopterRepository.findById(adopterId).get();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Неправильный ID усыновителя");
        }
        if (probationPeriodPassed != null) {
            if (probationPeriodPassed) {
                sendDefaultHappyMessage(adopter);
                adopter.setTrialPeriod(0);
            } else {
                sendDefaultSadMessage(adopter);
                adopter.setTrialPeriod(0);
            }

        } else {
            int modifiedTrialPeriod = adopter.getTrialPeriod()+days;
            adopter.setTrialPeriod(modifiedTrialPeriod);
            sendEditTrialPeriodMessage(adopter, days);
        }
        adopterRepository.save(adopter);
        return adopter;
    }

    private void sendDefaultHappyMessage(Adopter adopter) {
        SendMessage message = new SendMessage();
        message.setChatId(adopter.getSubscriber().getChatId());
        message.setText("Поздравляем. Вы прошли испытательный срок и обрели нового друга и члена семьи" +
                ". А " + adopter.getAnimal().getName() + " обрел(а) новый дом.");
        prepareAndSendMessage(message);
    }

    private void sendEditTrialPeriodMessage(Adopter adopter, Integer days) {
        SendMessage message = new SendMessage();
        message.setChatId(adopter.getSubscriber().getChatId());
        message.setText("Уважаемый " + adopter.getSubscriber().getName() +
                " мы вынужены добавить вам испытательный срок на " + days + " дней.");
        prepareAndSendMessage(message);
    }

    private void sendDefaultSadMessage(Adopter adopter) {
        SendMessage message = new SendMessage();
        message.setChatId(adopter.getSubscriber().getChatId());
        message.setText("Нам очень жаль, но вы не смогли пройти испытательный срок" +
                ". Вам нужно выполнить следующие шаги: Какие-то шаги...");
        prepareAndSendMessage(message);
    }

    public void prepareAndSendMessage(SendMessage message) {

        try {
            telegramBot.execute(message);
            logger.info(String.valueOf(HttpStatus.SC_OK));
        } catch (TelegramApiException e) {
            logger.error("Error: " + e);
        }
    }

}
