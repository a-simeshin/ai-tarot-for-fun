package ru.tarot.fun.tarot.rest.dto.normal.deck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.taror.fun.dto.Card;
import ru.taror.fun.dto.RiderWaiteDeck;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RiderWaiteDeckTest {

    private RiderWaiteDeck riderWaiteDeck;

    @BeforeEach
    void setUp() {
        riderWaiteDeck = new RiderWaiteDeck();
    }

    @Test
    void testGetCardsWhenCalledThenReturnListOfCards() {
        // Act
        List<Card> cards = riderWaiteDeck.getCards();
        // Assert
        assertNotNull(cards, "Cards list should not be null");
        assertEquals(78, cards.size(), "Cards list size should be 78");
    }

    @Test
    void testDivination3RandomCards() {
        // Act
        List<Card> divinated = riderWaiteDeck.divination();
        // Assert
        assertNotNull(divinated, "Cards list should not be null");
        assertEquals(3, divinated.size(), "Cards after divination list size should be 3");
    }
}
