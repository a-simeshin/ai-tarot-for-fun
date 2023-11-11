package ru.taror.fun.openai;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class ChatGptConfiguration {

    @Bean
    public OpenAiService openAiService(@Value("${chat-gpt.api-key}") String key) {
        return new OpenAiService(key, Duration.of(60, ChronoUnit.SECONDS));
    }
}
