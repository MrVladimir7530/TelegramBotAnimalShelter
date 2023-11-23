package com.example.telegrambotanimalshelter;

import com.example.telegrambotanimalshelter.controllers.AdopterController;
import com.example.telegrambotanimalshelter.model_services.AdopterService;
import com.example.telegrambotanimalshelter.model_services.AdopterServiceImpl;
import com.example.telegrambotanimalshelter.model_services.AnimalServiceImpl;
import com.example.telegrambotanimalshelter.model_services.SubscriberServiceImpl;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TelegramBotAnimalShelterApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private AdopterController adopterController;
    @Autowired
    private AdopterService adopterService;
    @Autowired
    private AdopterRepository adopterRepository;
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private SubscriberServiceImpl subscriberService;
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private AnimalServiceImpl animalService;


    @Test
    void contextLoads() {
        assertThat(adopterController).isNotNull();
    }

}
