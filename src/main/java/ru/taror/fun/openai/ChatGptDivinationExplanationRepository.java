package ru.taror.fun.openai;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.taror.fun.dto.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ChatGptDivinationExplanationRepository {

    private static final String PROMT = """
            Представь, что ты профессиональная гадалка на картах Таро.
            К тебе приходят люди с каким-либо вопросом, либо описанием жизненной ситуации.
            Они вытягивают из колоды 3 карты.
            
            Пример:
            Вопрос: У меня есть парень, но я не знаю, поженимся мы или нет.
            Карты: The Lovers,Strength,Ten of Cups
            Результат:
            ```
            1. The Lovers - означает любовники или любящие люди, что точно говорит о взаимных чувствах вашей пары.
            2. Strength - точно и однозначно говорит о силе ваших чувств и наверняка будущем крепком браке.
            3. Ten of Cups в то же время намекает на скорое и большое празднование свадьбы, обильных торжествах с большим количеством гостей.
            
            Карты The Lovers и Strength усиливают друг друга, а значит вас точно ждет счастливый и успешный брак.
            ```
            
            Тебе нужно объснить содержимое трех карт на основе заданного вопроса или описания жизненной ситуации.
            Обратно вернуть в чат нужно ТОЛЬКО РЕЗУЛЬТАТ!
            
            Вопрос: %request%
            Cards: %cards%
            """;

    final OpenAiService openAiService;

    public ChatGptDivinationExplanationRepository(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    public String explain(List<Card> cards, String request) {
        log.info("Asking AI to divination for request={} with cards={}", request, cards);

        String cardsWithComma = cards.stream().map(Card::name).collect(Collectors.joining(","));
        String promt = PROMT.replace("%request%", request).replace("%cards%", cardsWithComma);

        List<ChatMessage> messages = new ArrayList<>();
        ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(), promt);
        messages.add(userMessage);

        final ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .messages(messages)
                .model("gpt-3.5-turbo")
                .build();
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(completionRequest);
        String content = chatCompletion.getChoices().get(0).getMessage().getContent();
        log.info("Got from AI:\n{}", content);
        return content;
    }
}
