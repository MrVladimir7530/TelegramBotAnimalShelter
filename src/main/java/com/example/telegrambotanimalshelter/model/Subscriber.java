package com.example.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatId;

    private String name;

    private String userName;

    @OneToMany(mappedBy = "subscriber")
    @JsonIgnore
    private Set<Animal> animals;
    public Subscriber() {

    }

    public Subscriber(Long id, Long chatId, String name, String userName) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public String getName() {
        return name;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }
}
