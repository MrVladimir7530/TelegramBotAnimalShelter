package com.example.telegrambotanimalshelter.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "volunteer")
@Data
public class Volunteer {
    @Id
    private Long chatId;
    private String name;
    private String phoneNumber;
    private String userName;
}
