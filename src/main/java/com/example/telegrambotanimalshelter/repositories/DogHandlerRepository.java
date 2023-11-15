package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.DogHandler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogHandlerRepository extends JpaRepository<DogHandler, Long> {
}
