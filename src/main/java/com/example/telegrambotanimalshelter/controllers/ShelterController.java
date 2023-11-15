package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model.Shelter;
import com.example.telegrambotanimalshelter.model_Service.ShelterService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/shelter")
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @PostMapping
    public Shelter addShelter(@RequestBody Shelter shelter) {
        return shelterService.addShelter(shelter);
    }

    @GetMapping
    public Shelter getShelterByShelterType(@RequestParam String shelterType) {
        return shelterService.getShelterByShelterType(shelterType);
    }

    @GetMapping("/all")
    public Collection<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @PutMapping
    public Shelter updateShelter(@PathVariable String shelterType, @RequestBody Shelter shelter) {
        return shelterService.updateShelter(shelterType, shelter);
    }

    @DeleteMapping
    public void deleteShelter(@PathVariable String shelterType) {
        shelterService.deleteShelter(shelterType);
    }
}
