package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.VolunteerService;
import com.example.telegrambotanimalshelter.models.Volunteer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер позволяющий добавлять, удалять и получать волонтеров из БД
 */
@RestController
@RequestMapping("volunteer")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @Operation(
            summary = "Возращает волонтера из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Найденный волонтер"
                    )
            },
            tags = "Volunteer"
    )
    @GetMapping
    public ResponseEntity<Volunteer> getVolunteer(@RequestParam Long chatId) {
        Volunteer volunteer;
        try {
            volunteer = volunteerService.getVolunteer(chatId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(volunteer);
    }

    @Operation(
            summary = "Добавляет нового волонтера в БД или изменяет данные уже существующего",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Добавлен волонтер"
                    )
            },
            tags = "Volunteer"
    )
    @PostMapping
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        volunteerService.addVolunteer(volunteer);
        return ResponseEntity.ok(volunteer);
    }

    @Operation(
            summary = "удаляет волонтера из БД",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Удален волонтер"
                    )
            },
            tags = "Volunteer"
    )
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
