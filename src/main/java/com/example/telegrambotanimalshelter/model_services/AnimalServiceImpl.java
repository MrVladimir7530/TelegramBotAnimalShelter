package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Animal;
import com.example.telegrambotanimalshelter.repositories.AnimalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    private  final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).get();
    }
}
