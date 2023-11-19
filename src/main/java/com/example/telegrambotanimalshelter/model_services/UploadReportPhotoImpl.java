package com.example.telegrambotanimalshelter.model_services;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class UploadReportPhotoImpl implements UploadReportPhoto {
    Logger logger = LoggerFactory.getLogger(UploadReportPhotoImpl.class);
    @Value("${bot.token}")
    private String token;

    @Value("${telegramApi.photoInfo.uri}")
    private String photoInfoUri;

    @Value("${telegramApi.photoDownloadPath.uri}")
    private String photoDownloadPathUri;

    @Value("${report.photo.path}")
    private String reportPhotoDir;

    /**
     * Метод для загрузки фотографии
     *
     * @param update
     * @return
     */
    @Override
    public String upload(Update update) throws IOException {
        logger.info("Was invoked method for upload of Report Photo ");

        //получаем объект PhotoSize из массива объектов PhotoSize в Update
        PhotoSize photoSize = update.getMessage().getPhoto().get(0);
        String photoId = photoSize.getFileId();
        //Получение url из объекта ResponseEntity<String> полученного через Get запрос к TelegramBotApi
        String photoPath = getPhotoPath(photoId);
        //Получение имени и расширения файла из пути файла.
        String photoName = getPhotoName(photoPath);

        Path filePath = Path.of(reportPhotoDir, photoName);
        try {
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            logger.error("Ошибка создания директории");
        }
        URL downloadPhotoUri = null;
        try {
            downloadPhotoUri = new URL(photoDownloadPathUri.replace("{token}", token).replace("{photoPath}", photoPath));
            System.out.println(downloadPhotoUri.toString() +" "+token+ " "+ photoPath);
        } catch (MalformedURLException e) {
            logger.error("Неправильный URL");
        }
        try (

                InputStream is = downloadPhotoUri.openStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
            return String.valueOf(filePath);
        }


    }

    /**
     * Метод отправки запроса Telegram Api по ID фото, для получения информации о файле
     * в т.ч. ссылки на скачивание фото.
     *
     * @param photoId
     * @return ResponseEntity<><'String'>
     */
    private String getPhotoPath(String photoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                photoInfoUri,
                HttpMethod.GET,
                request,
                String.class,
                token, photoId
        );

        return getPhotoPath(response);
    }


    /**Получение пути файла из объекта ResponseEntity<String>, полученного с Telegram Api
     *
     * @param response
     * @return String
     */
    private String getPhotoPath(ResponseEntity<String> response) {
        JSONObject jsonObject = new JSONObject(response.getBody());
        return String.valueOf(jsonObject
                .getJSONObject("result")
                .getString("file_path"));
    }

    //Получение имени и расширения файла из пути файла.
    private String getPhotoName(String photoPath) {
        return photoPath.substring(photoPath.lastIndexOf("/") + 1);
    }
}
