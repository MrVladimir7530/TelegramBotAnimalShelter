package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Report;
import com.example.telegrambotanimalshelter.repository.ReportRepository;
import com.example.telegrambotanimalshelter.service.CommandHandler;
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
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class ReportServiceImpl implements ReportService, CommandHandler {
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Value("${bot.token}")
    private String token;

    @Value("${telegramApi.photoInfo.uri}")
    private  String photoInfoUri;

    @Value("${telegramApi.photoDownloadPath.uri}")
    private  String photoDownloadPathUri;

    @Value("${report.photo.path}")
    private String reportPhotoDir;

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public SendMessage process(Update update) {

        Report report = new Report();


//        if (update.getMessage().hasPhoto()) {
//            report.setPhotoPath(uploadAvatar(update));
//        }

        return null;
    }

    /**
     * Метод для создания отчета. Если не хватает фото или текста возвращается напоминане о необходимости добавления
     * недостающей информации.
     * @param report
     * @return String
     */
    @Override
    public String create(Report report) {
        logger.info("The create method of the ReportServiceImpl class was called");

        reportRepository.save(report);
        if (report.getReport() == null) {
            return "В отчете не хватает описания состояния животного. ";
        } else if (report.getPhotoPath() == null) {
            return "В отчете не хватает фото.";
        } else {

            return "Ваш отчет принят. Волонтеры проверят его после 21.00" +
                    ". Если появятся вопросы, с вами дополнительно свяжутся" +
                    ". Спасибо за обратную связь";
        }
    }


    /**
     * Метод для загрузки фотографии
     * @param update
     * @return
     */

    private String uploadAvatar(Update update) throws IOException {
        logger.info("Was invoked method for upload of Report Photo ");

        //получаем объект PhotoSize из массива объектов PhotoSize в Update
        PhotoSize photoSize = update.getMessage().getPhoto().get(0);
        String photoId = photoSize.getFileId();
        //Get запрос к Telegram Api для получения тела ответа в виде массива строк
        ResponseEntity<String> response = getPhotoPath(photoId);
        //Получение uri из объекта ResponseEntity<String>
        String photoPath = getPhotoPath(response);
        //Получение имени и расширения файла из пути файла.
        String photoName = getPhotoName(photoPath);

        Path filePath = Path.of(reportPhotoDir, photoName);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        URL downloadPhotoUri = new URL(photoDownloadPathUri);
        try (
                InputStream is = downloadPhotoUri.openStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
            return "Фото загружено";
        } catch (IOException e) {
            return "Загрузка не удалась((";
        }

    }

    /**
     * Метод отправки запроса Telegram Api по ID фото, для получения информации о файле
     * в т.ч. ссылки на скачивание фото.
     * @param photoId
     * @return ResponseEntity<><'String'>
     */
    private ResponseEntity<String> getPhotoPath(String photoId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<HttpHeaders> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                photoInfoUri,
                HttpMethod.GET,
                request,
                String.class,
                token, photoId
        );
    }
    //Получение пути файла из объекта ResponseEntity<String>, полученного с Telegram Api
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
