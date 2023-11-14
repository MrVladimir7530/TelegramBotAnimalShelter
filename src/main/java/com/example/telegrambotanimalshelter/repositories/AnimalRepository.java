package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
