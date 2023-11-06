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
    @ManyToOne
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    public Report() {
    }

    public Report(String photoPath, LocalDate creationDate, String report, Shelter shelter, Subscriber subscriber, Animal animal) {
        this.report = report;
        this.photoPath = photoPath;
        this.creationDate = creationDate;
        this.shelter = shelter;
        this.subscriber = subscriber;
        this.animal = animal;
    }

}
