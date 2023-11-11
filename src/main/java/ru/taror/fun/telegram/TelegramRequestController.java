package ru.taror.fun.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.taror.fun.service.DivinationWithRiderWaiteDeck;
import ru.taror.fun.telegram.responses.HelloMessageResponse;
import ru.taror.fun.telegram.responses.MessageType;
import ru.taror.fun.telegram.responses.TaroDivinationTelegramResponse;

import java.util.List;

@Slf4j
@Controller
public class TelegramRequestController implements UpdatesListener {

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private DivinationWithRiderWaiteDeck divinationWithRiderWaiteDeck;
    private TaroDivinationTelegramResponse taroDivinationTelegramResponse;
    private HelloMessageResponse helloMessageResponse;

    @PostConstruct
    void init() {
        telegramBot.setUpdatesListener(this);
        taroDivinationTelegramResponse = new TaroDivinationTelegramResponse(divinationWithRiderWaiteDeck, telegramBot);
        helloMessageResponse = new HelloMessageResponse(telegramBot);
    }

    @Override
    public int process(List<Update> list) {
        for (Update update : list) {
            if (update.message().from().isBot()) {
                continue;
            }

            log.info(
                    "from chat: {} from user-id: {} got {} message {}",
                    "@" + update.message().chat().username(),
                    update.message().from().id(),
                    MessageType.getType(update.message()),
                    MessageType.getType(update.message()) == MessageType.TEXT ? update.message().text() : ""
            );

            if (update.message().text() != null && update.message().text().equals("/start")) {
                helloMessageResponse.proceed(update);
                continue;
            }

            if (update.message().text() != null) {
                taroDivinationTelegramResponse.generate(update);
            }

//            if (!telegramUserAccessProvider.isFromAdminPrivateChat(update)) {
//                log.warn("Unknown access for user @" + update.message().chat().username());
//            }

//            if (update.message().voice() != null) {
//                final VoiceExtractor voiceExtractor = new VoiceExtractor(telegramBot);
//                voiceExtractor.extractVoice(update);
//            }

        }

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}