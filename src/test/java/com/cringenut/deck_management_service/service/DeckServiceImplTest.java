package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DeckServiceTest {

    DeckService deckService = new DeckService();

    @Test
    void testCreateDeckReturnsNonNull() {
        Deck deck = deckService.createDeck(36, 4);
        assertNotNull(deck, "Deck should not be null");
    }

    @Test
    void testCreateDeckReturnsDeckWithCards() {
        Deck deck = deckService.createDeck(52, 4);
        assertNotNull(deck.getCards(), "Deck cards list should not be null");
        assertFalse(deck.getCards().isEmpty(), "Deck should contain cards");
    }

    @Test
    void testGeneratedDeckHasCorrectNumberOfCards36() {
        Deck deck = deckService.createDeck(36, 4);
        assertEquals(36, deck.getCards().size(), "Deck should contain 36 cards");
    }

    @Test
    void testGeneratedDeckHasCorrectNumberOfCards52() {
        Deck deck = deckService.createDeck(52, 4);
        assertEquals(52, deck.getCards().size(), "Deck should contain 52 cards");
    }

    @Test
    void testAllCardsAreUnique() {
        Deck deck = deckService.createDeck(52, 4);
        List<Card> cards = deck.getCards();
        long uniqueCards = cards.stream()
                .distinct()
                .count();
        assertEquals(cards.size(), uniqueCards, "All cards should be unique");
    }

}
