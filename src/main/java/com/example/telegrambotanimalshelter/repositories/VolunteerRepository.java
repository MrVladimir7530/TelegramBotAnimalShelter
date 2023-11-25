package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
