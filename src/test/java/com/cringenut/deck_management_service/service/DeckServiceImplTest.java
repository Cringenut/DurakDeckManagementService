package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.enums.Suit;
import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import com.cringenut.deck_management_service.model.GameSetup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import java.util.*;

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
        for (HashMap<Suit, List<Card>> hand : gameSetup.getPlayerHands()) {
            int totalCards = hand.values().stream().mapToInt(List::size).sum();
            assertEquals(6, totalCards, "Each player should have 6 cards");
        }

        System.out.println("Game setup: " + gameSetup);
    }

    @Test
    void testDealPlayerHandsDistributesCardsCorrectly() {
        int deckSize = 36;
        int playerAmount = 4;
        int cardsPerPlayer = 6;

        Deck deck = deckService.generateDeck(deckSize, playerAmount);
        List<HashMap<Suit, List<Card>>> playerHands = deckService.dealPlayerHands(deck, playerAmount);

        // Validate total number of player hands
        assertNotNull(playerHands, "Player hands should not be null");
        assertEquals(playerAmount, playerHands.size(), "Should return correct number of player hands");

        // Track all dealt cards for uniqueness check
        Set<Card> allDealtCards = new HashSet<>();

        for (HashMap<Suit, List<Card>> hand : playerHands) {
            assertNotNull(hand, "Each hand should not be null");

            // Total cards in this player's hand
            int totalCards = hand.values().stream().mapToInt(List::size).sum();
            assertEquals(cardsPerPlayer, totalCards, "Each player should receive 6 cards");

            // Add cards to set for uniqueness check
            hand.values().forEach(allDealtCards::addAll);
        }

        // Ensure all dealt cards are unique
        assertEquals(playerAmount * cardsPerPlayer, allDealtCards.size(), "All dealt cards should be unique");

        // Check remaining cards in deck
        int expectedRemaining = deckSize - (playerAmount * cardsPerPlayer);
        assertEquals(expectedRemaining, deck.getCards().size(), "Deck should have expected number of remaining cards");

        System.out.println("Tump suit: " + deck.getTrumpSuit());
        System.out.println("\n--- Player Hands ---");
        int playerIndex = 1;
        for (HashMap<Suit, List<Card>> hand : playerHands) {
            System.out.println("Player " + playerIndex + ":");
            for (Suit suit : Suit.values()) {
                List<Card> cards = hand.getOrDefault(suit, new ArrayList<>());
                if (!cards.isEmpty()) {
                    System.out.println("  " + suit + ": " + cards);
                }
            }
            playerIndex++;
        }
    }
}
