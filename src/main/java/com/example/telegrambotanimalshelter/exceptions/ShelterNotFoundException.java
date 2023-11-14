package com.example.telegrambotanimalshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShelterNotFoundException extends RuntimeException {

    public ShelterNotFoundException() {
    }

    public ShelterNotFoundException(String message) {
        super(message);
    }

    public ShelterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShelterNotFoundException(Throwable cause) {
        super(cause);
    }
}
