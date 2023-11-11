package com.example.telegrambotanimalshelter.сcontroller;

import com.example.telegrambotanimalshelter.model.Adopter;
import com.example.telegrambotanimalshelter.model_Service.AdopterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adopter")
public class AdopterController {
    private final AdopterService adopterService;


    public AdopterController(AdopterService adopterService) {
        this.adopterService = adopterService;
    }

    @PostMapping
    public Adopter createAdopter(@RequestBody Adopter adopter) {
        return adopterService.create(adopter);
    }

    @PutMapping
    public int editTrialPeriod(@RequestParam Long adopterId,
                              @RequestParam (required = false) Integer days,
                              @RequestParam (required = false) Boolean probationPeriodPassed) {
        return adopterService.editTrialPeriod(adopterId, days, probationPeriodPassed);
    }
}
