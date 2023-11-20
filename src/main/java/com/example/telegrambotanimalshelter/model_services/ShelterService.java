package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Shelter;

import java.util.Collection;

public interface ShelterService {
    Shelter addShelter(Shelter shelter);

    Shelter getShelterByShelterType(String shelterType); // Приют кошек, Приют собак

    Collection<Shelter> getAllShelters();

    Shelter updateShelter(Long id, Shelter shelter);

    void deleteShelter(Long id);
}
