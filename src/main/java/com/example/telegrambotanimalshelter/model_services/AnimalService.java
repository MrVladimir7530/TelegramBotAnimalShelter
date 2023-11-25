package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Animal;
import liquibase.pro.packaged.C;

import java.util.Collection;

import com.example.telegrambotanimalshelter.models.Animal;

public interface AnimalService {

    Animal addAnimal(Animal animal);

    Animal getAnimalByName(String name);

    Collection<Animal> getAllAnimalsByType(String animalType);

    Collection<Animal> getAllAnimals();

    Collection<Animal> getAllAdoptedAnimals();

    Collection<Animal> getAllAdoptedAnimalsByType(String animalType);

    Animal updateAnimal(Long id, Animal animal);

    void deleteAnimal(Long id);


    Animal findById(Long id);
}
