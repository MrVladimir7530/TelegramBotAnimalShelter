package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Shelter findByName(String animalType);

    @Query(value = "SELECT name FROM shelter", nativeQuery = true)
    List<String> getAllShelterNames();

}
