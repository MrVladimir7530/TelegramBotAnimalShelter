package com.example.telegrambotanimalshelter.model_services;

import com.example.telegrambotanimalshelter.models.Volunteer;
import com.example.telegrambotanimalshelter.repositories.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {
    private Logger log = LoggerFactory.getLogger(VolunteerServiceImpl.class);
    private final VolunteerRepository volunteerRepository;

    public Volunteer addVolunteer(Volunteer volunteer) {
        log.info("saving volunteer in repository");
        return volunteerRepository.save(volunteer);
    }

    public Volunteer getVolunteer(Long chatId) {
        log.info("getting volunteer from repository");
        return volunteerRepository.findById(chatId).get();
    }


    public void deleteVolunteer(Long chatId) {
        log.info("deleting volunteer from repository");
        volunteerRepository.deleteById(chatId);
    }
}
