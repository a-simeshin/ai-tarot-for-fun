package ru.taror.fun.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import ru.taror.fun.dto.Card;
import ru.taror.fun.dto.RiderWaiteDeck;
import ru.taror.fun.openai.ChatGptDivinationExplanationRepository;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class DivinationWithRiderWaiteDeck implements Divination {

    private final RiderWaiteDeck riderWaiteDeck;
    private final ChatGptDivinationExplanationRepository chatGpt;

    public DivinationWithRiderWaiteDeck(RiderWaiteDeck riderWaiteDeck,
                                        ChatGptDivinationExplanationRepository chatGptDivinationExplanationRepository) {
        this.riderWaiteDeck = riderWaiteDeck;
        this.chatGpt = chatGptDivinationExplanationRepository;
    }

    @Override
    public DivinationResult divination(String request) {
        try {
            log.info("Starting divination for request = {}", request);
            final List<Card> threeRandomCards = riderWaiteDeck.divination();
            final String explanation = chatGpt.explain(threeRandomCards, request);
            return new DivinationResult(threeRandomCards, explanation);
        } catch (Exception probablySocketTimeoutException) {
            log.warn("Open AI lagging", probablySocketTimeoutException.getCause());
            return divination(request);
        }
    }

    @SneakyThrows
    public byte[] getImage(Card card) {
        ClassPathResource classPathResource = new ClassPathResource("riderwaitedeck/" + card.name() + ".jpg");
        InputStream inputStream = classPathResource.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }
}
