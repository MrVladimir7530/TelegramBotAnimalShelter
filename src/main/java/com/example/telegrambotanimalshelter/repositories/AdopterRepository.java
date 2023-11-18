package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdopterRepository extends JpaRepository<Adopter, Long> {
    Adopter findBySubscriberChatId(Long id);
    @Query(value = "SELECT * FROM adopter where trial_period !=0", nativeQuery = true)
    List<Adopter> getActualAdopter();
}
