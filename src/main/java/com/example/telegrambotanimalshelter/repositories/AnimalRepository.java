package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByName(String animalName);

    @Query(value = "SELECT an.* FROM animal AS an " +
            "JOIN shelter AS sh ON an.shelter_id = sh.id " +
            "WHERE sh.name = ?1", nativeQuery = true)
    Collection<Animal> getAllAnimalsByAnimalType(String animalType);

    @Query(value = "SELECT an.* FROM animal AS an " +
            "JOIN adopter AS ad ON an.id = ad.animal_id", nativeQuery = true)
    Collection<Animal> getAllAdoptedAnimals();

    @Query(value = "SELECT an.* FROM animal an " +
            "JOIN SHELTER AS sh ON an.shelter_id = sh.id " +
            "JOIN adopter AS ad ON an.id = ad.animal_id " +
            "WHERE sh.name = ?1", nativeQuery = true)
    Collection<Animal> getAdoptedAnimalsByType(String animalType);
}
