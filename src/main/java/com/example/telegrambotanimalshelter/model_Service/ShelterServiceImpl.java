package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.exceprions.ShelterNotFoundException;
import com.example.telegrambotanimalshelter.model.Shelter;
import com.example.telegrambotanimalshelter.repository.ShelterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
