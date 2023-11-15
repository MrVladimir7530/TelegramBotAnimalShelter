package com.example.telegrambotanimalshelter;

import com.example.telegrambotanimalshelter.services.CommandHandler;


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
