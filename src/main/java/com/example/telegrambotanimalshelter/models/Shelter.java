package com.example.telegrambotanimalshelter.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "shelter")
@Data
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name; // приют кошек, приют собак
    private String generalInfo; //общая информация о приюте
    private String info; //адрес, расписание
    private String securityContact; //контакты охраны
    private String safetyPrecautions; //техника безопасности

    public Shelter(Long id) {
        this.id = id;
    }

}
