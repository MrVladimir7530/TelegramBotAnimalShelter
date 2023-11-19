package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    Logger logger = LoggerFactory.getLogger(SubscriberServiceImpl.class);
    private final SubscriberRepository subscriberRepository;

    public SubscriberServiceImpl(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Override
    public Subscriber findById(Long chatId) {
        return subscriberRepository.findByChatId(chatId);
    }

    @Override
    public boolean create(Subscriber subscriber) {
        logger.info("The create method of the SubscriberServiceImpl class was called");
        if (subscriberRepository.findByChatId(subscriber.getChatId()) != null) {
            return false;
        }
        subscriberRepository.save(subscriber);
        return true;
    }


}
