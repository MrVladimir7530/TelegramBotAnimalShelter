package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Volunteer;

public interface VolunteerService {
    Volunteer addVolunteer(Volunteer volunteer);

    Volunteer getVolunteer(Long chatId);

    void deleteVolunteer(Long chatId);
}
