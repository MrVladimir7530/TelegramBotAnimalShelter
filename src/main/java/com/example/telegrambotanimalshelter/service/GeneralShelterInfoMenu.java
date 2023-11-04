package com.example.telegrambotanimalshelter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GeneralShelterInfoMenu implements CommandHandler {

    /**
     * Метод для обработки входящего сообщения от пользователя и возврата сообщения для отправки
     * Меню общей информации о приютах
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of ShelterInformationMenu was called");

        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createKeyboardMarkup(update));
        message.setText(createText());

        return message;
    }

    /**
     * Метод для создания и возврата виртуальной клавиутары
     * @param update
     * @return InlineKeyboardMarkup
     */
    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton generalInfoAboutShelter = new InlineKeyboardButton("Рассказать о приюте");
        InlineKeyboardButton addressAndScheduleOfShelter = new InlineKeyboardButton("Расписание работы, адрес и схема проезда");
        InlineKeyboardButton contactInfoOfShelterSecurity = new InlineKeyboardButton("Контакты охраны для оформления пропуска на авто");
        InlineKeyboardButton generalSafetyRecommendations = new InlineKeyboardButton("Техника безопасности на территории приюта");
        InlineKeyboardButton getAndSaveContactInfoAboutUser = new InlineKeyboardButton("Оставить контакты для обратной связи");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");

        // в названии кнопки обязательно использовать Cat или Dog !!
        if (update.getCallbackQuery().getData().equals("CAT_INFO")) {
            generalInfoAboutShelter.setCallbackData("GENERAL_INFO_ABOUT_CAT_SHELTER");
            addressAndScheduleOfShelter.setCallbackData("ADDRESS_AND_SCHEDULE_OF_CAT_SHELTER");
            contactInfoOfShelterSecurity.setCallbackData("SECURITY_INFO_OF_CAT_SHELTER");
            generalSafetyRecommendations.setCallbackData("CAT_SAFETY_RECOMMENDATIONS");
        } else {
            generalInfoAboutShelter.setCallbackData("GENERAL_INFO_ABOUT_DOG_SHELTER");
            addressAndScheduleOfShelter.setCallbackData("ADDRESS_AND_SCHEDULE_OF_DOG_SHELTER");
            contactInfoOfShelterSecurity.setCallbackData("SECURITY_INFO_OF_DOG_SHELTER");
            generalSafetyRecommendations.setCallbackData("DOG_SAFETY_RECOMMENDATIONS");
        }
        getAndSaveContactInfoAboutUser.setCallbackData("UserContacts");
        callVolunteer.setCallbackData("Call_Volunteer");

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
        rowInline5.add(getAndSaveContactInfoAboutUser);
        rowInline.add(rowInline5);

        List<InlineKeyboardButton> rowInline6 = new ArrayList<>();
        rowInline6.add(callVolunteer);
        rowInline.add(rowInline6);

        inlineKeyboardMarkup.setKeyboard(rowInline);

        return inlineKeyboardMarkup;
    }
    private String createText() {
        String text;
        text = "Вы находитесь с разделе общей информации о приюте. Пожалуйста, выберите, о чем вы бы хотели узнать подробнее";
        return text;
    }
}
