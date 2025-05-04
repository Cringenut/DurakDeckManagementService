package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import com.cringenut.deck_management_service.model.GameSetup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckServiceTest {

    DeckService deckService = new DeckService();

    @Test
    void testGenerateDeckReturnsNonNull() {
        Deck deck = deckService.generateDeck(36, 4);
        assertNotNull(deck, "Deck should not be null");
    }

    @Test
    void testGenerateDeckReturnsDeckWithCards() {
        Deck deck = deckService.generateDeck(52, 4);
        assertNotNull(deck.getCards(), "Deck cards list should not be null");
        assertFalse(deck.getCards().isEmpty(), "Deck should contain cards");
    }

    @Test
    void testGeneratedDeckHasCorrectNumberOfCards36() {
        Deck deck = deckService.generateDeck(36, 4);
        assertEquals(36, deck.getCards().size(), "Deck should contain 36 cards");
    }

    @Test
    void testGeneratedDeckHasCorrectNumberOfCards52() {
        Deck deck = deckService.generateDeck(52, 4);
        assertEquals(52, deck.getCards().size(), "Deck should contain 52 cards");
    }

    @Test
    void testAllCardsAreUnique() {
        Deck deck = deckService.generateDeck(52, 4);
        List<Card> cards = deck.getCards();
        long uniqueCards = cards.stream()
                .distinct()
                .count();
        assertEquals(cards.size(), uniqueCards, "All cards should be unique");
    }

    @Test
    void testCreateGameSetupIsValid() {
        int deckSize = 36;
        int playerAmount = 4;

        GameSetup gameSetup = deckService.createGame(deckSize, playerAmount);

        // Assert game setup is not null
        assertNotNull(gameSetup, "Game setup should not be null");

        // Assert deck is present and correctly sized (after dealing 6 cards per player)
        assertNotNull(gameSetup.getDeck(), "Deck should not be null");
        int expectedRemainingCards = deckSize - (playerAmount * 6);
        assertEquals(expectedRemainingCards, gameSetup.getDeck().getCards().size(), "Remaining deck size should match");

        // Assert trump suit is set
        assertNotNull(gameSetup.getTrumpSuit(), "Trump suit should be set");

        // Assert player hands are created
        assertNotNull(gameSetup.getPlayerHands(), "Player hands should not be null");
        assertEquals(playerAmount, gameSetup.getPlayerHands().size(), "Player count should match");

        // Assert each player has exactly 6 cards
        for (List<Card> hand : gameSetup.getPlayerHands()) {
            assertEquals(6, hand.size(), "Each player should have 6 cards");
        }

        System.out.println("Game setup: " + gameSetup);
    }
}
