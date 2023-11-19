package com.example.telegrambotanimalshelter.model_services;


import com.example.telegrambotanimalshelter.models.Subscriber;

public interface SubscriberService {

    Subscriber findById(Long chatId);
    boolean create(Subscriber subscriber);

}
