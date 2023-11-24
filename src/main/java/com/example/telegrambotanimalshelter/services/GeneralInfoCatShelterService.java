
package com.example.telegrambotanimalshelter.services;

import com.example.telegrambotanimalshelter.models.Shelter;
import com.example.telegrambotanimalshelter.repositories.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для обработки команд общей информации о приютах
 */
@Service
@RequiredArgsConstructor
public class GeneralInfoCatShelterService implements CommandHandler {
    private final String CAT_INFO = "CAT_INFO";
    private final String GENERAL_INFO_ABOUT_CAT_SHELTER = "GENERAL_INFO_ABOUT_CAT_SHELTER";
    private final String ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER = "ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER";
    private final String SECURITY_INFO_OF_CAT_SHELTER = "SECURITY_INFO_OF_CAT_SHELTER";
    private final String CAT_SAFETY_RECOMMENDATIONS = "CAT_SAFETY_RECOMMENDATIONS";
    private final String LEAVE_CONTACTS = "LEAVE_CONTACTS";
    private final String CANCEL = "/cancel";
    private final String EXCEPTION = "Команда не распознана";

    Logger log = LoggerFactory.getLogger(GeneralInfoCatShelterService.class);

    private final ShelterRepository shelterRepository;

    /**
     * Метод для обработки входящего сообщения от пользователя и возврата сообщения для отправки
     * Меню общей информации о приюте кошек
     *
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of ShelterInformationMenu was called");

        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createKeyboardMarkup(update));
        String answer = chooseWay(update);
        message.setText(answer);

        return message;
    }

    /**
     * Метод для создания и возврата виртуальной клавиутары
     *
     * @param update
     * @return InlineKeyboardMarkup
     */
    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton generalInfoAboutShelter = new InlineKeyboardButton("Рассказать о приюте кошек");
        InlineKeyboardButton addressAndScheduleOfShelter = new InlineKeyboardButton("Расписание работы, адрес и схема проезда");
        InlineKeyboardButton contactInfoOfShelterSecurity = new InlineKeyboardButton("Контакты охраны для оформления пропуска на авто");
        InlineKeyboardButton generalSafetyRecommendations = new InlineKeyboardButton("Техника безопасности на территории приюта");
        InlineKeyboardButton getAndSaveUserPhone = new InlineKeyboardButton("Оставить контакты для обратной связи");
        InlineKeyboardButton exit = new InlineKeyboardButton("Вернуться в главное меню");
        //InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");

        generalInfoAboutShelter.setCallbackData(GENERAL_INFO_ABOUT_CAT_SHELTER);
        addressAndScheduleOfShelter.setCallbackData(ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER);
        contactInfoOfShelterSecurity.setCallbackData(SECURITY_INFO_OF_CAT_SHELTER);
        generalSafetyRecommendations.setCallbackData(CAT_SAFETY_RECOMMENDATIONS);

        getAndSaveUserPhone.setCallbackData(LEAVE_CONTACTS);
        exit.setCallbackData(CANCEL);
        //callVolunteer.setCallbackData("Call_Volunteer");

        List<List<InlineKeyboardButton>> rowInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        rowInline1.add(generalInfoAboutShelter);
        rowInline.add(rowInline1);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        rowInline2.add(addressAndScheduleOfShelter);
        rowInline.add(rowInline2);

        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        rowInline3.add(contactInfoOfShelterSecurity);
        rowInline.add(rowInline3);

        List<InlineKeyboardButton> rowInline4 = new ArrayList<>();
        rowInline4.add(generalSafetyRecommendations);
        rowInline.add(rowInline4);

        List<InlineKeyboardButton> rowInline5 = new ArrayList<>();
        rowInline5.add(getAndSaveUserPhone);
        rowInline.add(rowInline5);

        List<InlineKeyboardButton> rowInline6 = new ArrayList<>();
        rowInline6.add(exit);
        rowInline.add(rowInline6);

        inlineKeyboardMarkup.setKeyboard(rowInline);

        return inlineKeyboardMarkup;
    }

    private String createText() {
        String text;
        text = "Вы находитесь с разделе общей информации о приюте кошек. Пожалуйста, выберите, о чем вы бы хотели узнать подробнее";
        return text;
    }

    private String chooseWay(Update update) {
        String animalType = "Приют кошек";
        Shelter shelter = shelterRepository.findByName(animalType);
        String answer = update.getCallbackQuery().getData();
        switch (answer) {
            case CAT_INFO:
                return createText();
            case GENERAL_INFO_ABOUT_CAT_SHELTER:
                return shelter.getGeneralInfo();
            case ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER:
                return shelter.getInfo();
            case SECURITY_INFO_OF_CAT_SHELTER:
                return shelter.getSecurityContact();
            case CAT_SAFETY_RECOMMENDATIONS:
                return shelter.getSafetyPrecautions();
            default:
                return EXCEPTION;
        }
    }
}
