package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
public class ShelterServiceImpl implements ShelterService {

    private final ShelterRepository shelterRepository;

    public ShelterServiceImpl(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    @Override
    public Shelter addShelter(Shelter shelter) {
        log.info("Shelter is added: {}", shelter);
        return shelterRepository.save(shelter);
    }

    @Override
    public Shelter getShelterByShelterType(String shelterType) {
        log.info("Shelter is found by shelter type: {}", shelterType);
        return shelterRepository.findByName(shelterType);
    }

    @Override
    public Collection<Shelter> getAllShelters() {
        log.info("Get all shelters");
        return shelterRepository.findAll();
    }

    @Override
    public Shelter updateShelter(String shelterType, Shelter shelter) {
        log.info("Shelter is updated by shelter type: {}", shelterType);
        return shelterRepository.save(shelter);
    }

    @Override
    public void deleteShelter(String shelterType) {
        log.info("Shelter is deleted by shelter type: {}", shelterType);
        shelterRepository.deleteShelterByName(shelterType);
    }
}
