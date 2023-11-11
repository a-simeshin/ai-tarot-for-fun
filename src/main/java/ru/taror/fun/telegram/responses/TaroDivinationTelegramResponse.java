package ru.taror.fun.telegram.responses;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.*;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import ru.taror.fun.service.Divination;

import java.io.InputStream;

public class TaroDivinationTelegramResponse {

    private final Divination divination;
    private final TelegramBot telegramBot;

    public TaroDivinationTelegramResponse(Divination divination, TelegramBot telegramBot) {
        this.divination = divination;
        this.telegramBot = telegramBot;
    }

    public void generate(Update update) {
        if (update.message().text() == null || update.message().text().isEmpty()) {
            return;
        }

        final long chatId = update.message().chat().id();
        telegramBot.execute(new SendChatAction(chatId, ChatAction.typing));

        final String loadingMessage = "Расклад требует времени, подождите немного...";

        final SendAnimation sendLoading = new SendAnimation(update.message().chat().id(), loadingGif());
        sendLoading.caption(loadingMessage);
        sendLoading.replyToMessageId(update.message().messageId());
        final SendResponse executedLoading = telegramBot.execute(sendLoading);

        Divination.DivinationResult divination1 = divination.divination(update.message().text());
        String explanation = divination1.explanation();

        final DeleteMessage deleteMessage = new DeleteMessage(update.message().chat().id(), executedLoading.message().messageId());
        telegramBot.execute(deleteMessage);

        final String first = StringUtils.substringBetween(explanation,"1.", "2.");
        final SendPhoto sendPhoto = new SendPhoto(update.message().chat().id(), divination.getImage(divination1.responseCards().get(0)));
        sendPhoto.caption(first);
        sendPhoto.replyToMessageId(update.message().messageId());
        telegramBot.execute(sendPhoto);

        String second = StringUtils.substringBetween(explanation,"2.", "3.");
        final SendPhoto sendPhoto2 = new SendPhoto(update.message().chat().id(), divination.getImage(divination1.responseCards().get(1)));
        sendPhoto2.caption(second);
        sendPhoto2.replyToMessageId(update.message().messageId());
        telegramBot.execute(sendPhoto2);

        String third = StringUtils.substringBetween(explanation,"3.", "\n\n");
        if (third != null && !third.isEmpty()) {
            final SendPhoto sendPhoto3 = new SendPhoto(update.message().chat().id(), divination.getImage(divination1.responseCards().get(2)));
            sendPhoto3.caption(third);
            sendPhoto3.replyToMessageId(update.message().messageId());
            telegramBot.execute(sendPhoto3);
        }

        String result = StringUtils.substringAfterLast(explanation,"\n");
        SendMessage resultSendMessage = new SendMessage(update.message().chat().id(), result);
        resultSendMessage.replyToMessageId(update.message().messageId());
        telegramBot.execute(resultSendMessage);
    }

    @SneakyThrows
    private byte[] loadingGif() {
        ClassPathResource classPathResource = new ClassPathResource("loading.gif");
        InputStream inputStream = classPathResource.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }
}
