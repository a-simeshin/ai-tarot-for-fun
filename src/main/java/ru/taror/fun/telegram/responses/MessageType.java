package ru.taror.fun.telegram.responses;

import com.pengrad.telegrambot.model.Message;

public enum MessageType {
    AUDIO, VOICE, TEXT;

    public static MessageType getType(Message message) {
        if (message.voice() != null) return VOICE;
        if (message.audio() != null) return AUDIO;
        if (message.text() != null ) return TEXT;
        return TEXT;
    }
}
