package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Animal;

public interface AnimalService {

    Animal findById(Long id);
}
