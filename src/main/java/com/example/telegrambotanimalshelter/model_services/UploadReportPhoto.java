package com.example.telegrambotanimalshelter.model_services;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public interface UploadReportPhoto {
    /**
     * Метод для загрузки фотографии
     *
     * @param update
     * @return
     */
    String upload(Update update) throws IOException;
}
