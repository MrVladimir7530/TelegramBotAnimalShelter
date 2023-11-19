package com.example.telegrambotanimalshelter.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate adoptionDate;
    private Integer trialPeriod = 30;
    @OneToOne()
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @OneToOne()
    @JoinColumn(name ="animal_id")
    private Animal animal;

    public Adopter() {
    }

    public Adopter(Subscriber subscriber, Animal animal) {
        this.adoptionDate = LocalDate.now();
        this.trialPeriod = 30;
        this.subscriber = subscriber;
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }

    public int getTrialPeriod() {
        return trialPeriod;
    }

    public void setTrialPeriod(int trialPeriod) {
        this.trialPeriod = trialPeriod;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
