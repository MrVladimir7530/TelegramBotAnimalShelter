package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.AnimalService;
import com.example.telegrambotanimalshelter.model_services.SubscriberService;
import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.model_services.AdopterService;
import com.example.telegrambotanimalshelter.models.Animal;
import com.example.telegrambotanimalshelter.models.Subscriber;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adopter")
public class AdopterController {
    private final AdopterService adopterService;
    private final SubscriberService subscriberService;
    private final AnimalService animalService;


    public AdopterController(AdopterService adopterService, SubscriberService subscriberService, AnimalService animalService) {
        this.adopterService = adopterService;
        this.subscriberService = subscriberService;
        this.animalService = animalService;
    }

    @Operation(summary = "Добавление информации о усыновителе и усыновленном животном",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавление информации о усыновителе и усыновленном животном.",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Adopter.class)
                                    )
                            }
                    )
            }, tags = "Усыновление"
    )
    @PostMapping
    public ResponseEntity<Adopter> createAdopter(
            @Parameter(description = "Чат ID пользователя", example = "Какие-то циферки") @RequestParam Long subscriberChatId,
            @RequestParam(name = "ID животного") Long animalId) {

        Adopter adopter = new Adopter();
        Subscriber subscriber = subscriberService.findById(subscriberChatId);
        Animal animal = animalService.findById(animalId);

        adopter.setSubscriber(subscriber);
        adopter.setAnimal(animal);
        return ResponseEntity.ok(adopterService.create(adopter));
    }
    @Operation(summary = "Изменение испытательного периода усыновления/ заключение о прохождении/непрохождении испытательного периода",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Для добавления испытательного периода укажите количество добавляемых дней. " +
                                    "Для решения о прохождении испытательного срока укажите true в случае успешного прохождения " +
                                    "и false в противном случае. Указать нужно только один из этих параметров." +
                                    " Чат ID нужно указать в любов случае",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Adopter.class)
                                    )
                            }
                    )
            }, tags = "Усыновление"
    )
    @PutMapping
    public Adopter editTrialPeriod(@RequestParam(name = "Чат ID пользователя") Long adopterId,
                                   @RequestParam(name = "Количество добавляемых дней",required = false) Integer days,
                                   @RequestParam(name = "Решение о прохождении испытательного срока",required = false) Boolean probationPeriodPassed) {
        return adopterService.editTrialPeriod(adopterId, days, probationPeriodPassed);
    }
}
