package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.VolunteerService;
import com.example.telegrambotanimalshelter.models.Volunteer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("volunteer")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<Volunteer> getVolunteer(@RequestParam Long chatId) {
        Volunteer volunteer;
        try {
            volunteer = volunteerService.getVolunteer(chatId);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(volunteer);
    }

    @PostMapping
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return ResponseEntity.ok(volunteer);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteVolunteer(@RequestParam Long chatId) {
        try {
            volunteerService.deleteVolunteer(chatId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
