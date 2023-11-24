package com.example.telegrambotanimalshelter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "animal")
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String breed;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    private Shelter shelter;

    public Animal() {
    }

    public Animal(Long id, String name, String breed, Shelter shelter) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.shelter = shelter;
    }

}
