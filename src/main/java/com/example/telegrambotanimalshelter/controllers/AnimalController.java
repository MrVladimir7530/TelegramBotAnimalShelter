package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.AnimalService;
import com.example.telegrambotanimalshelter.models.Animal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public Animal addAnimal(@RequestBody Animal animal) {
        return animalService.addAnimal(animal);
    }

    @GetMapping
    public ResponseEntity getAnimals(@RequestParam(required = false) String animalName,
                                     @RequestParam(required = false) String animalType) {
        if (animalName != null && !animalName.isBlank() && animalType.isBlank()) {
            return ResponseEntity.ok(animalService.getAnimalByName(animalName));
        }
        if (animalType != null && !animalType.isBlank() && animalName.isBlank()) {
            return ResponseEntity.ok(animalService.getAllAnimalsByType(animalType));
        }
        return ResponseEntity.ok(animalService.getAllAnimals());
    }

    @GetMapping("/adopted")
    public ResponseEntity getAdoptedAnimal(@RequestParam(required = false) String animalType) {
        if (animalType != null && !animalType.isBlank()) {
            return ResponseEntity.ok(animalService.getAllAdoptedAnimalsByType(animalType));
        }
        return ResponseEntity.ok(animalService.getAllAdoptedAnimals());
    }

    @PutMapping
    public Animal updateAnimal(@PathVariable Long id, @RequestBody Animal animal) {
        return animalService.updateAnimal(id, animal);
    }

    @DeleteMapping
    public void deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
    }
}
