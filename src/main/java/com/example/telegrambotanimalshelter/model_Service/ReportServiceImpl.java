package com.example.telegrambotanimalshelter.model_Service;

import com.example.telegrambotanimalshelter.model.Adopter;
import com.example.telegrambotanimalshelter.model.Report;
import com.example.telegrambotanimalshelter.model.Subscriber;
import com.example.telegrambotanimalshelter.repository.ReportRepository;
import com.example.telegrambotanimalshelter.service.CommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

@Service
public class ReportServiceImpl implements ReportService, CommandHandler {
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);


    private final UploadReportPhoto uploadReportPhoto;
    private final ReportRepository reportRepository;
    private final AdopterService adopterService;

    public ReportServiceImpl(UploadReportPhoto uploadAvatar, ReportRepository reportRepository, AdopterService adopterService) {
        this.uploadReportPhoto = uploadAvatar;
        this.reportRepository = reportRepository;
        this.adopterService = adopterService;
    }

    @Override
    public SendMessage process(Update update) {
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        message.setText(create(update));

        return message;
    }

    /**
     * Метод для создания отчета. Если не хватает фото или текста возвращается напоминане о необходимости добавления
     * недостающей информации.
     *
     * @param update
     * @return String
     */
    @Override
    public String create(Update update) {
        logger.info("The create method of the ReportServiceImpl class was called");
        Report report;
        //Находим связь пользователя и животного(Усыновитель)
        Adopter adopter = adopterService.findBySubscriberId(update.getMessage().getChatId());

        LocalDate currentDate = LocalDate.now();
        //Ищем отчет по Усыновителю и текущей дате
        if (reportRepository.findByAdopterIdAndCreationDate(adopter.getId(), currentDate)!=null){
            report = reportRepository.findByAdopterIdAndCreationDate(adopter.getId(), currentDate);

        } else {
            report = new Report();
        }

        if (update.getMessage().hasPhoto()) {
            report.setPhotoPath(uploadReportPhoto.upload(update));
        }
        if (update.getMessage().hasText()) {
            report.setReport(update.getMessage().getText());
        }
        report.setCreationDate(currentDate);
        report.setAdopter(adopter);
        report.setShelter(adopter.getAnimal().getShelter());

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


}

