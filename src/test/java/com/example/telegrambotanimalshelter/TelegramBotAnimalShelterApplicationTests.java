package com.example.telegrambotanimalshelter;

import com.example.telegrambotanimalshelter.constants.AdopterConstant;
import com.example.telegrambotanimalshelter.constants.AnimalConstant;
import com.example.telegrambotanimalshelter.constants.ShelterConstant;
import com.example.telegrambotanimalshelter.constants.SubscriberConstant;
import com.example.telegrambotanimalshelter.controllers.AdopterController;
import com.example.telegrambotanimalshelter.model_services.AdopterService;
import com.example.telegrambotanimalshelter.model_services.AdopterServiceImpl;
import com.example.telegrambotanimalshelter.model_services.AnimalServiceImpl;
import com.example.telegrambotanimalshelter.model_services.SubscriberServiceImpl;
import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.models.Animal;
import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.AdopterRepository;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import com.example.telegrambotanimalshelter.repositories.SubscriberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static com.example.telegrambotanimalshelter.constants.AdopterConstant.*;
import static com.example.telegrambotanimalshelter.constants.ShelterConstant.*;
import static com.example.telegrambotanimalshelter.constants.SubscriberConstant.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TelegramBotAnimalShelterApplicationTests {
    @LocalServerPort
    private int port;
    @Autowired
    private AdopterController adopterController;
    @Autowired
    private TestRestTemplate restTemplate;
    //    @Autowired
//    private AdopterService adopterService;
    @Autowired
    private AdopterRepository adopterRepository;
    @Autowired
    private SubscriberRepository subscriberRepository;
    @Autowired
    private ShelterRepository shelterRepository;
    //    @Autowired
//    private SubscriberServiceImpl subscriberService;
    @Autowired
    private AnimalRepository animalRepository;
//    @Autowired
//    private AnimalServiceImpl animalService;


    @Test
    void contextLoads() {
        assertThat(adopterController).isNotNull();
    }

//    @Test
//    public void shouldCorrectResultFromMethodCreateAdopter() {
//        Shelter shelter = null;
//        Subscriber subscriber =  null;
//        Animal animal = null;
//        Adopter actual = null;
//        try {
//            shelter = shelterRepository.save(new Shelter());
//            subscriber = subscriberRepository.save(new Subscriber(111111L, "Иван"
//                    , null, "ЮзерИван"));
//            animal = animalRepository.save(new Animal(null, "Муська", "CAT", shelter));
//
//
//
//            actual = this.restTemplate.exchange("http://localhost:" + port + "/adopter" +
//                            "/subscriberChatId?subscriberChatId=" + subscriber.getChatId() + "&ID животного?ID животного=" + animal.getId()
//                    , HttpMethod.POST, null, Adopter.class).getBody();
//
//            assertEquals(ADOPTER1, actual);
//        } finally {
//            adopterRepository.deleteById(actual.getId());
//            animalRepository.deleteById(animal.getId());
//            subscriberRepository.deleteByChatId(subscriber.getChatId());
//            shelterRepository.deleteById(shelter.getId());
//        }
//
//
//    }
}
