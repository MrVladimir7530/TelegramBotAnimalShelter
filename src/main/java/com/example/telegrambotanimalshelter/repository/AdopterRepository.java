package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
    Adopter findBySubscriberChatId(Long id);
    @Query(value = "SELECT id FROM adopter where trial_period is not null", nativeQuery = true)
    List<Adopter> getActualAdopter();
}
