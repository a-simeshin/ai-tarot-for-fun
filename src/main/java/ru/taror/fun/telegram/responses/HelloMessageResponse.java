package ru.taror.fun.telegram.responses;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendMessage;

public class HelloMessageResponse {

    private static final String HELLO_MESSAGE = """
            Привет!
            Я профессиональный провидец, напитавший свои чакры сотнями часов курсов от гуру просвещения и марафонов желаний.
            Мой основной инструмент - карты Таро.
            Расскажи, про что мне погадать тебе сегодня?
            """;

    private final TelegramBot telegramBot;

    public HelloMessageResponse(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void proceed(Update update) {
        final long chatId = update.message().chat().id();
        telegramBot.execute(new SendChatAction(chatId, ChatAction.typing));
        SendMessage sendMessage = new SendMessage(update.message().chat().id(), HELLO_MESSAGE);
        telegramBot.execute(sendMessage);
    }
}
