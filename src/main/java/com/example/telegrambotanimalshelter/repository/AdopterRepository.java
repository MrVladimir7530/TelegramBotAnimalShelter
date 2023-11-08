package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
    Adopter findBySubscriberId(Long id);
}
