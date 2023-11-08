package com.example.telegrambotanimalshelter.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String photoPath = null;
    private LocalDate creationDate;
    private String report = null;
    @OneToOne
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;
    @OneToOne
    @JoinColumn(name = "adopter_id")
    private Adopter adopter;

    public Report() {
    }

    public Report(String photoPath, LocalDate creationDate, String report, Shelter shelter, Adopter adopter) {
        this.report = report;
        this.photoPath = photoPath;
        this.creationDate = creationDate;
        this.shelter = shelter;
        this.adopter = adopter;

    }

}
