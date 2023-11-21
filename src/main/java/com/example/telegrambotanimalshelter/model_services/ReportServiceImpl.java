package com.example.telegrambotanimalshelter.model_services;


import com.example.telegrambotanimalshelter.components.VolunteerSendMessageService;
import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.models.Report;
import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.repositories.ReportRepository;
import com.example.telegrambotanimalshelter.services.CommandHandler;
import com.example.telegrambotanimalshelter.services.TelegramBot;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReportServiceImpl implements ReportService, CommandHandler {
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final UploadReportPhoto uploadReportPhoto;
    private final ReportRepository reportRepository;
    private final AdopterService adopterService;
    private final TelegramBot telegramBot;
    private final VolunteerSendMessageService volunteerSendMessageService;

    public ReportServiceImpl(@Lazy TelegramBot telegramBot, UploadReportPhoto uploadAvatar
            , ReportRepository reportRepository, AdopterService adopterService
            , VolunteerSendMessageService volunteerSendMessageService) {

        this.telegramBot = telegramBot;
        this.uploadReportPhoto = uploadAvatar;
        this.reportRepository = reportRepository;
        this.adopterService = adopterService;
        this.volunteerSendMessageService = volunteerSendMessageService;
    }


    @Override
    public Report findById(long id) {
        return reportRepository.findById(id).get();
    }

    @Override
    public Report findByAdopterIdAndCreationDate(Long id, LocalDate creationDate) {
        return reportRepository.findByAdopterIdAndCreationDate(id, creationDate);
    }

    @Override
    public SendMessage process(Update update) {
        logger.info("The process method of the ReportServiceImpl class was called");
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
        //Ищем отчет по Усыновителю и текущей дате. Если его нет, создаем новый.
        if (reportRepository.findByAdopterIdAndCreationDate(adopter.getId(), currentDate) != null) {
            report = reportRepository.findByAdopterIdAndCreationDate(adopter.getId(), currentDate);

        } else {
            report = new Report();
        }

        if (update.getMessage().hasPhoto() || update.getMessage().hasDocument()) {
            try {
                report.setPhotoPath(uploadReportPhoto.upload(update));
            } catch (IOException e) {
                logger.error("Ошибка загрузки фото. " + e.getClass());

                return "Загрузка фото не удалась. Мы починим это в ближайшее время.";
            }

            report.setReport(update.getMessage().getCaption());

        } else {
            report.setReport(update.getMessage().getText());
        }
        report.setCreationDate(currentDate);
        report.setAdopter(adopter);
        report.setShelter(adopter.getAnimal().getShelter());

        reportRepository.save(report);
        if (report.getReport() == null) {
            return "В отчете не хватает описания состояния животного.";
        } else if (report.getPhotoPath() == null) {
            return "В отчете не хватает фото.";
        } else {

            return "Ваш отчет принят. Волонтеры проверят его после 21.00" +
                    ". Если появятся вопросы, с вами дополнительно свяжутся" +
                    ". Спасибо за обратную связь";
        }
    }


    @Scheduled(cron = "0 0 14 * * *")
    public void checkingTheSendingOfTheDailyReport() {
        //получаем связи усыновления, для которых еще не прошел испытательный срок
        List<Adopter> adopterList = adopterService.getActualAdopter();
        //список усыновлений, для которых не прислали отчет
        List<Adopter> adopterList2 = new ArrayList<>();
        Set<Long> adopterIdListFromTodaySReports = reportRepository.getAdopterIdFromTodaySReport(LocalDate.now());
        String text = "Этот нехороший человек уже 2 дня не отправляет отчет. ";
        for (Adopter adopter : adopterList) {
            if (!adopterIdListFromTodaySReports.contains(adopter.getId())) {
                adopterList2.add(adopter);

            }
        }

        for (Adopter adopter : adopterList2) {
            //ищем последний отчет для усыновления
//            Report lastReport = reportRepository.findLastReportByAdopterId(adopter.getId());
            LocalDate lastReportDate = reportRepository.findLastReportByAdopterId(adopter.getId());
            //определяем сколько дней прошло с последнего отчета
            int daysHavePassed;
            if (lastReportDate != null) {
                daysHavePassed = LocalDate.now().compareTo(lastReportDate);
            } else {
                daysHavePassed = LocalDate.now().compareTo(adopter.getAdoptionDate());
            }
            if (daysHavePassed >= 2) {

                Subscriber subscriber = adopter.getSubscriber();
                String message = text + " " + subscriber.toString();
                volunteerSendMessageService.sendMessageToVolunteer(subscriber.getChatId(), message);
            } else {
                sendReminder(adopter.getSubscriber());
            }
        }
    }

    private void sendReminder(Subscriber subscriber) {


        String text = "Напоминание. Уважаемый усыновитель. Нами было замеченно" +
                ", что вы забыли отправить ежедневный отчет о состоянии усыновленного вами животного." +
                "Пожалуйста, не забывайте отправлять его.";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(subscriber.getChatId());
        telegramBot.prepareAndSendMessage(message);

    }

    @Override
    public int sendWarning(Long chatId, String text) {
        SendMessage message = new SendMessage();
        if (text == null) {
            text = "Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. " +
                    "Пожалуйста, подойди ответственнее к этому занятию. " +
                    "В противном случае волонтеры приюта будут обязаны самолично проверять" +
                    " условия содержания животного";
        }
        message.setText(text);
        message.setChatId(chatId);
        try {
            telegramBot.prepareAndSendMessage(message);
            return HttpStatus.SC_OK;
        } catch (Exception e) {
            return HttpStatus.SC_CONFLICT;
        }


    }


}

