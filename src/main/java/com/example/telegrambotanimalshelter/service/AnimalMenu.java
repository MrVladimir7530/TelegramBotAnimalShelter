package com.example.telegrambotanimalshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для обработки команд /CAT, /DOG.
 */
@Service
public class AnimalMenu implements CommandHandler {
    Logger log = LoggerFactory.getLogger(AnimalMenu.class);

    /**
     * Метод для обработки входящего обновления и возврата сообщения
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the AnimalMenu class was called"+"CallBackData = "
                +update.getCallbackQuery().getData()+" "+update.getCallbackQuery().getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(update.getCallbackQuery().getFrom().getId());
        message.setReplyMarkup(createKeyboardMarkup(update));
        message.setText(createText());

        return message;
    }

    /**
     * Метод для создания и возврата инлайн клавиатуры
     * @param update
     * @return InlineKeyboardMarkup
     */
    private InlineKeyboardMarkup createKeyboardMarkup(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton getInfoAboutShelter = new InlineKeyboardButton("Узнать информацию о приюте");
        InlineKeyboardButton informationAboutAnimalAdoption = new InlineKeyboardButton("Как взять животное из приюта");
        InlineKeyboardButton callVolunteer = new InlineKeyboardButton("Позвать волонтера");

        if (update.getCallbackQuery().getData().equals("CAT")) {
            getInfoAboutShelter.setCallbackData("CAT_INFO");
            informationAboutAnimalAdoption.setCallbackData("HOW_TO_TAKE_CAT");

        } else {

            getInfoAboutShelter.setCallbackData("DOG_INFO");
            informationAboutAnimalAdoption.setCallbackData("HOW_TO_TAKE_DOG");
        }
        callVolunteer.setCallbackData("Call_Volunteer");

        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
        catButtonList.add(getInfoAboutShelter);
        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
        dogButtonList.add(informationAboutAnimalAdoption);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(callVolunteer);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(catButtonList);
        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);

        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return  inlineKeyboardMarkup;


    }

    private String createText() {
        return "Вы находитесь в меню информации о приюте. Выберете интересующию вас информацию ";
    }

}
