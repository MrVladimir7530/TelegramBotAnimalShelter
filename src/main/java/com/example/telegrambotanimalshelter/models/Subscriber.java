package com.example.telegrambotanimalshelter.models;

import lombok.Data;

import javax.persistence.*;


@Entity(name = "subscriber")
@Data
public class Subscriber {
    @Id
    private Long chatId;

    private String name;

    private String phoneNumber;

    private String userName;

    public Subscriber() {
    }

    public Subscriber(Long chatId, String name, String phoneNumber, String userName) {
        this.chatId = chatId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
    }
}
