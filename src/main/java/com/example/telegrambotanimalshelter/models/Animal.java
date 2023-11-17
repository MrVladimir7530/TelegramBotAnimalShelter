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
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String breed;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="shelter_id")
    private Shelter shelter;

}
