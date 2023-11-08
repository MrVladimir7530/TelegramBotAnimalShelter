package com.example.telegrambotanimalshelter.repository;

import com.example.telegrambotanimalshelter.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByAdopterIdAndCreationDate(Long id, LocalDate creationDate);
}
