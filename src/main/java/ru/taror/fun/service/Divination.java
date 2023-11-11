package ru.taror.fun.service;

import ru.taror.fun.dto.Card;

import java.util.List;

public interface Divination {

    DivinationResult divination(String request);

    byte[] getImage(Card card);

    record DivinationResult(List<Card> responseCards, String explanation) {}
}
