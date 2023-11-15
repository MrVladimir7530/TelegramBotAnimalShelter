package com.example.telegrambotanimalshelter.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(id, report.id) && Objects.equals(creationDate, report.creationDate) && Objects.equals(shelter, report.shelter) && Objects.equals(adopter, report.adopter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, shelter, adopter);
    }
}
