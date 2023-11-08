package com.example.telegrambotanimalshelter.model;

import javax.persistence.*;

@Entity
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;

    @OneToOne()
    @JoinColumn(name ="animal_id")
    private Animal animal;

    public Adopter() {
    }


    public Long getId() {
        return id;
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
