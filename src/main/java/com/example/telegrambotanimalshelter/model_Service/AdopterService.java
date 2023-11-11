package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Adopter;

import java.util.List;

public interface AdopterService {
    Adopter findById(Long id);
    Adopter findBySubscriberId(Long id);

    List<Adopter> getActualAdopter();

}
