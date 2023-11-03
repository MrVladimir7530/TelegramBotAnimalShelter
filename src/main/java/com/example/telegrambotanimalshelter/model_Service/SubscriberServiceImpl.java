package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Subscriber;
import com.example.telegrambotanimalshelter.repository.SubscriberRepository;
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
    public Subscriber create(Subscriber subscriber) {
        logger.info("The create method of the SubscriberServiceImpl class was called");
        if (subscriberRepository.findByUserName(subscriber.getUserName())!=null) {
            return null;
        }
        return subscriberRepository.save(subscriber);
    }


}
