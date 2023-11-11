package ru.taror.fun.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://www.tarot.com/tarot/decks/rider">Rider Waite Deck</a>
 */
@Component
public class RiderWaiteDeck implements Deck {
    private final List<Card> cards;

    public RiderWaiteDeck() {
        cards = new ArrayList<>(78);
        //Major Arcana
        cards.add(new Card("The Fool"));
        cards.add(new Card("The Magician"));
        cards.add(new Card("The High Priestess"));
        cards.add(new Card("The Empress"));
        cards.add(new Card("The Emperor"));
        cards.add(new Card("The Hierophant"));
        cards.add(new Card("The Lovers"));
        cards.add(new Card("The Chariot"));
        cards.add(new Card("Strength"));
        cards.add(new Card("The Hermit"));
        cards.add(new Card("Wheel of Fortune"));
        cards.add(new Card("Justice"));
        cards.add(new Card("The Hanged Man"));
        cards.add(new Card("Death"));
        cards.add(new Card("Temperance"));
        cards.add(new Card("The Devil"));
        cards.add(new Card("The Tower"));
        cards.add(new Card("The Star"));
        cards.add(new Card("The Moon"));
        cards.add(new Card("The Sun"));
        cards.add(new Card("Judgement"));
        cards.add(new Card("The World"));
        //The Wands
        cards.add(new Card("Ace of Wands"));
        cards.add(new Card("Two of Wands"));
        cards.add(new Card("Three of Wands"));
        cards.add(new Card("Four of Wands"));
        cards.add(new Card("Five of Wands"));
        cards.add(new Card("Six of Wands"));
        cards.add(new Card("Seven of Wands"));
        cards.add(new Card("Eight of Wands"));
        cards.add(new Card("Nine of Wands"));
        cards.add(new Card("Ten of Wands"));
        cards.add(new Card("Page of Wands"));
        cards.add(new Card("Knight of Wands"));
        cards.add(new Card("Queen of Wands"));
        cards.add(new Card("King of Wands"));
        //The Cups
        cards.add(new Card("Ace of Cups"));
        cards.add(new Card("Two of Cups"));
        cards.add(new Card("Three of Cups"));
        cards.add(new Card("Four of Cups"));
        cards.add(new Card("Five of Cups"));
        cards.add(new Card("Six of Cups"));
        cards.add(new Card("Seven of Cups"));
        cards.add(new Card("Eight of Cups"));
        cards.add(new Card("Nine of Cups"));
        cards.add(new Card("Ten of Cups"));
        cards.add(new Card("Page of Cups"));
        cards.add(new Card("Knight of Cups"));
        cards.add(new Card("Queen of Cups"));
        cards.add(new Card("King of Cups"));
        //The Swords
        cards.add(new Card("Ace of Swords"));
        cards.add(new Card("Two of Swords"));
        cards.add(new Card("Three of Swords"));
        cards.add(new Card("Four of Swords"));
        cards.add(new Card("Five of Swords"));
        cards.add(new Card("Six of Swords"));
        cards.add(new Card("Seven of Swords"));
        cards.add(new Card("Eight of Swords"));
        cards.add(new Card("Nine of Swords"));
        cards.add(new Card("Ten of Swords"));
        cards.add(new Card("Page of Swords"));
        cards.add(new Card("Knight of Swords"));
        cards.add(new Card("Queen of Swords"));
        cards.add(new Card("King of Swords"));
        //The Coins
        cards.add(new Card("Ace of Coins"));
        cards.add(new Card("Two of Coins"));
        cards.add(new Card("Three of Coins"));
        cards.add(new Card("Four of Coins"));
        cards.add(new Card("Five of Coins"));
        cards.add(new Card("Six of Coins"));
        cards.add(new Card("Seven of Coins"));
        cards.add(new Card("Eight of Coins"));
        cards.add(new Card("Nine of Coins"));
        cards.add(new Card("Ten of Coins"));
        cards.add(new Card("Page of Coins"));
        cards.add(new Card("Knight of Coins"));
        cards.add(new Card("Queen of Coins"));
        cards.add(new Card("King of Coins"));
    }

    @Override
    public List<Card> getCards() {
        return cards;
    }
}
