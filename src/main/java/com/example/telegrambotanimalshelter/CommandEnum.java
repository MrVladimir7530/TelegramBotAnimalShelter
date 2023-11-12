package com.example.telegrambotanimalshelter;

import com.example.telegrambotanimalshelter.service.AnimalMenu;
import com.example.telegrambotanimalshelter.service.CommandHandler;
import com.example.telegrambotanimalshelter.service.StartMenu;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public enum CommandEnum {
    START("/start"), CANCEL("/cancel"), CAT("CAT"), DOG("DOG")
    , REPORT("REPORT"), VOLUNTEER_INPUT("Volunteer_Input");


    String text;

    CommandHandler commandHandler;

    CommandEnum() {

    }

    CommandEnum(String text) {

    }


    public String getText() {
        return text;
    }


}
