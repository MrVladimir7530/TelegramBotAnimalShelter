package com.example.telegrambotanimalshelter.model_services;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;

public interface UploadReportPhoto {

    String upload(Update update) throws IOException;
}
