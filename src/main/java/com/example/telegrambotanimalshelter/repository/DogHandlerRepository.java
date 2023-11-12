package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.DogHandler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogHandlerRepository extends JpaRepository<DogHandler, Long> {
}
