package com.example.telegrambotanimalshelter.model_Service;


import com.example.telegrambotanimalshelter.model.Adopter;
import com.example.telegrambotanimalshelter.repository.AdopterRepository;
import org.springframework.stereotype.Service;

@Service
public class AdopterServiceImpl implements AdopterService {
    private final AdopterRepository adopterRepository;

    public AdopterServiceImpl(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    public Adopter findById(Long id) {
        return adopterRepository.findById(id).get();

    }

    public Adopter findBySubscriberId(Long id) {
        return adopterRepository.findBySubscriberId(id);
    }
}
