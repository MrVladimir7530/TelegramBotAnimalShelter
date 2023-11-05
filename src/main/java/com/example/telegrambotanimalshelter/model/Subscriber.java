package com.example.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity(name = "subscriber")
@Data
public class Subscriber {
    @Id
    private Long chatId;
    private String name;
    private String phoneNumber;
    private String userName;
}
