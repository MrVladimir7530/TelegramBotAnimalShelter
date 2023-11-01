package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
