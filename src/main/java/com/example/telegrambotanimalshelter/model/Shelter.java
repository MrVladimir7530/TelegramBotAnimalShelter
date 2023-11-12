package com.example.telegrambotanimalshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

}
