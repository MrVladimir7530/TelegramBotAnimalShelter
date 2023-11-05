package com.example.telegrambotanimalshelter.model;

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
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;
}
