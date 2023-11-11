package ru.taror.fun.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Deck {
    List<Card> getCards();

    default List<Card> divination() {
        final List<Card> items = getCards();
        final List<Card> randomItems = new ArrayList<>();
        final Random random = new Random();

        while (randomItems.size() < 3) {
            final Card item = items.get(random.nextInt(items.size()));
            if (!randomItems.contains(item)) {
                randomItems.add(item);
            }
        }

        return randomItems;
    }

}
