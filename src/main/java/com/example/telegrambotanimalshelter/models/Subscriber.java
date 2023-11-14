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
}
