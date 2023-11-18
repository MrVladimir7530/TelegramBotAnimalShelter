package com.example.telegrambotanimalshelter.repositories;

import com.example.telegrambotanimalshelter.models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByAdopterIdAndCreationDate(Long id, LocalDate creationDate);

    @Query(value = "SELECT id FROM report where creation_date = ?1", nativeQuery = true)
    Set<Long> getAdopterIdFromTodaySReport(LocalDate localDate);
}
