package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Subscriber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Subscriber findByChatId(Long chatId);
}
