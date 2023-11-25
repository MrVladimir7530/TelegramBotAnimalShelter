package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Animal;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AnimalServiceImpl implements AnimalService {

    Logger log = LoggerFactory.getLogger(AnimalServiceImpl.class);

    private  final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).get();
    }


    @Override
    public Animal addAnimal(Animal animal) {
        log.info("Animal is added: {}", animal);
        return animalRepository.save(animal);
    }

    @Override
    public Animal getAnimalByName(String name) {
        log.info("Animal is found by animal name: {}", name);
        return animalRepository.findByName(name);
    }

    @Override
    public Collection<Animal> getAllAnimalsByType(String animalType) {
        log.info("Animals are found by animal type: {}", animalType);
        return animalRepository.getAllAnimalsByAnimalType(animalType);
    }

    @Override
    public Collection<Animal> getAllAnimals() {
        log.info("Get all animals");
        return animalRepository.findAll();
    }

    @Override
    public Collection<Animal> getAllAdoptedAnimals() {
        log.info("Get all adopted animals");
        return animalRepository.getAllAdoptedAnimals();
    }

    @Override
    public Collection<Animal> getAllAdoptedAnimalsByType(String animalType) {
        log.info("Adopted animals are found by animal type: {}", animalType);
        return animalRepository.getAdoptedAnimalsByType(animalType);
    }

    @Override
    public Animal updateAnimal(Long id, Animal animal) {
        log.info("Animal is updated by id: {}", id);
        return animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(Long id) {
        log.info("Animal is deleted by id: {}", id);
        animalRepository.deleteById(id);
    }
}
