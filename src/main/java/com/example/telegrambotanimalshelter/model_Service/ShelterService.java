package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Shelter;

import java.util.Collection;

public interface ShelterService {
    Shelter addShelter(Shelter shelter);

    Shelter getShelterByShelterType(String shelterType); // Приют кошек, Приют собак

    Collection<Shelter> getAllShelters();

    Shelter updateShelter(String shelterType, Shelter shelter);

    void deleteShelter(String shelterType);
}
