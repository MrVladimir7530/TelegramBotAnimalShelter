package com.example.telegrambotanimalshelter.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity(name = "subscriber")
@Data
public class Subscriber {
    @Id
    private Long chatId;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String phoneNumber;
    @JsonIgnore
    private String userName;
}
