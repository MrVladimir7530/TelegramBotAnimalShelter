package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Shelter findByTextKey(String animalType);
}
