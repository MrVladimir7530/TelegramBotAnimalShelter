package com.example.telegrambotanimalshelter.services;


import com.example.telegrambotanimalshelter.components.VolunteerSendMessageService;
import com.example.telegrambotanimalshelter.models.Subscriber;
import com.example.telegrambotanimalshelter.model_services.SubscriberService;

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

@Service
@RequiredArgsConstructor
/**
 * Сервис для обработки команды /start, /cancel
 */
public class StartMenu implements CommandHandler {
    Logger log = LoggerFactory.getLogger(StartMenu.class);
    private final String YES_VOLUNTEER = "YES_VOLUNTEER";
    private final String NO_VOLUNTEER = "NO_VOLUNTEER";
    @Autowired
    private final SubscriberService subscriberService;

    private final VolunteerSendMessageService volunteerSendMessageService;
    //todo возможно надо удалить

    /**
     * Метод для обработки входящего обновления и возврата сообщения
     *
     * @param update
     * @return SendMessage
     */
    @Override
    public SendMessage process(Update update) {
        log.info("The process method of the StartMenu class was called");
        SendMessage message = new SendMessage();
        if (update.hasCallbackQuery()) {
            message.setChatId(update.getCallbackQuery().getMessage().getChatId());
            message.setText(chooseWay(update));

        } else {
            createUser(update);
            message.setChatId(update.getMessage().getChatId());
            message.setText(createText());
        }
        message.setReplyMarkup(createInlineKeyboard());
        return message;
    }

    /**
     * Метод для создания и возврата инлайн клавиатуры
     *
     * @return InlineKeyboardMarkup
     */
    private InlineKeyboardMarkup createInlineKeyboard() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton catButton = new InlineKeyboardButton("Приют для кошек");
        InlineKeyboardButton dogButton = new InlineKeyboardButton("Приют для собак");
        InlineKeyboardButton reportButton = new InlineKeyboardButton("Отправить отчет");
        InlineKeyboardButton volunteerInputButton = new InlineKeyboardButton("Войти как волонтер");

        catButton.setCallbackData("CAT");
        dogButton.setCallbackData("DOG");
        reportButton.setCallbackData("REPORT");
        volunteerInputButton.setCallbackData("VOLUNTEER_INPUT");


        List<InlineKeyboardButton> catButtonList = new ArrayList<>();
        catButtonList.add(catButton);
        List<InlineKeyboardButton> dogButtonList = new ArrayList<>();
        dogButtonList.add(dogButton);
        List<InlineKeyboardButton> reportButtonList = new ArrayList<>();
        reportButtonList.add(reportButton);
        List<InlineKeyboardButton> volunteerButtonList = new ArrayList<>();
        reportButtonList.add(volunteerInputButton);

        List<List<InlineKeyboardButton>> startButtonList = new ArrayList<>();
        startButtonList.add(catButtonList);
        startButtonList.add(dogButtonList);
        startButtonList.add(reportButtonList);
        startButtonList.add(volunteerButtonList);


        inlineKeyboardMarkup.setKeyboard(startButtonList);
        return inlineKeyboardMarkup;


    }

    private String chooseWay(Update update) {
        String text = update.getCallbackQuery().getData();
        switch (text) {
            case YES_VOLUNTEER:
                volunteerSendMessageService.sendMessageToVolunteer(update.getCallbackQuery().getMessage().getChatId());
                return "Ваши котакты переданы волонтеру, скоро с Вами свяжутся";
            case NO_VOLUNTEER:
                return "Вы перешли в стартовое меню";
            default:
                return "комманда не распознана";
        }
    }

    private String createText() {
        return "Привет. Вы находитесь в стартовом меню бота приюта для животных." +
                " Пожалуйста, выберете приют или нажмите кнопку отправки отчета.";
    }

    private void createUser(Update update) {

        Subscriber subscriber = new Subscriber();
        subscriber.setChatId(update.getMessage().getChatId());
        subscriber.setName(update.getMessage().getFrom().getFirstName());
        subscriber.setUserName(update.getMessage().getFrom().getUserName());
        subscriberService.create(subscriber);

    }
}