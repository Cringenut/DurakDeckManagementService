package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckServiceImplTest {

    DeckService deckService = new DeckService();

    @Test
    void testGenerateDeckReturnsNonNull() {
        ResponseEntity<Deck> response = deckService.generateDeck(36, 4);
        assertNotNull(response, "Response should not be null");
        for (Card card : response.getBody().getCards()) {
            System.out.println(card.getSuit() + " " + card.getRank());
        }
    }

    @Test
    void testGenerateDeckReturnsDeckWithCards() {
        ResponseEntity<Deck> response = deckService.generateDeck(52, 4);
        Deck deck = response.getBody();
        assertNotNull(deck, "Deck should not be null");
        assertNotNull(deck.getCards(), "Deck cards should not be null");
        assertFalse(deck.getCards().isEmpty(), "Deck should contain cards");
    }

    @Test
    void testGeneratedDeckHasCorrectNumberOfCards() {
        ResponseEntity<Deck> response = deckService.generateDeck(52, 4);
        Deck deck = response.getBody();
        assertEquals(52, deck.getCards().size(), "Deck should contain 52 cards");
    }

    @Test
    void testAllCardsAreUnique() {
        ResponseEntity<Deck> response = deckService.generateDeck(52, 4);
        Deck deck = response.getBody();
        List<Card> cards = deck.getCards();
        long uniqueCards = cards.stream()
                .distinct()
                .count();
        assertEquals(cards.size(), uniqueCards, "All cards should be unique");
    }
}
