package com.example.telegrambotanimalshelter.model_services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShelterServiceImpl implements ShelterService {
//
//    private final ShelterRepository shelterRepository;
//
//    public ShelterServiceImpl(ShelterRepository shelterRepository) {
//        this.shelterRepository = shelterRepository;
//    }
//
//    @Override
//    public Shelter addShelter(Shelter shelter) {
//        log.info("Shelter is added: {}", shelter);
//        return shelterRepository.save(shelter);
//    }
//
//    @Override
//    public Object getShelterByShelterType(String shelterType) {
//        log.info("Shelter is found by shelter type: {}", shelterType);
//        try {
//            return shelterRepository.findByName(shelterType);
//        } catch (ShelterNotFoundException e) {
//            return "Введите название приюта из списка ниже: " + shelterRepository.getAllShelterNames();
//        }
//    }
//
//    @Override
//    public Collection<Shelter> getAllShelters() {
//        log.info("Get all shelters");
//        return shelterRepository.findAll();
//    }
//
//    @Override
//    public Shelter updateShelter(String shelterType, Shelter shelter) {
//        log.info("Shelter is updated by shelter type: {}", shelterType);
//        return shelterRepository.save(shelter);
//    }
//
//    @Override
//    public void deleteShelter(String shelterType) {
//        log.info("Shelter is deleted by shelter type: {}", shelterType);
//        shelterRepository.deleteShelterByShelterType(shelterType);
//    }
}
