package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.model_services.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @Operation(
            summary = "Добавление приюта",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавленное приюта"
                    )
            },
            tags = "Shelters"
    )
    @PostMapping
    public Shelter addShelter(@RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @Operation(
            summary = "Возвращает конкретных приют",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный приют"
                    )
            },
            tags = "Shelters"
    )
    @GetMapping
    public Shelter getShelterByShelterType(@Parameter(example = "Приют кошек / Приют собак") @RequestParam(name = "Типа приюта") String shelterType) {
        return shelterService.getShelterByShelterType(shelterType);
    }

    @Operation(
            summary = "Возвращает все приюты",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденные приют"
                    )
            },
            tags = "Shelters"
    )
    @GetMapping("/all")
    public Collection<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @Operation(
            summary = "Изменение приюта по типу",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Результат правок"
                    )
            },
            tags = "Shelters"
    )
    @PutMapping
    public Shelter updateShelter(@Parameter(example = "Приют кошек / Приют собак") @PathVariable(name = "Тип приюта") String shelterType, @RequestBody Shelter shelter) {
        return shelterService.updateShelter(shelterType, shelter);
    }

    @Operation(
            summary = "Удаление приюта по ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "При успешном удалении код 200"
                    )
            },
            tags = "Shelters"
    )
    @DeleteMapping
    public void deleteShelter(@Parameter(example = "Приют кошек / Приют собак") @PathVariable(name = "Тип приюта") String shelterType) {
        shelterService.deleteShelter(shelterType);
    }
}
