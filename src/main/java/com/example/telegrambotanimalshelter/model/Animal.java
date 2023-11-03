package com.example.telegrambotanimalshelter.model;

import javax.persistence.*;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;


    @ManyToOne
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private Subscriber subscriber;
}
