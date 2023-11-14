package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Volunteer;
import com.example.telegrambotanimalshelter.repositories.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService{
    private final VolunteerRepository volunteerRepository;

    public Volunteer addVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    public Volunteer getVolunteer(Long chatId) {
        return volunteerRepository.findById(chatId).get();
    }


    public void deleteVolunteer(Long chatId) {
        volunteerRepository.deleteById(chatId);
    }
}
