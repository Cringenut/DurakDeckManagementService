package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.enums.Rank;
import com.cringenut.deck_management_service.enums.Suit;
import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import com.cringenut.deck_management_service.model.GameSetup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckService {

    // Use player amount later to determine deck min size
    public Deck generateDeck(Integer size, Integer playerAmount) {
        Stack<Card> deckCards = new Stack<>();
        // Remove ranks that are not used in the game
        List<Rank> totalRanks = new ArrayList<>(
                List.of(Rank.values())
                        .subList(
                                List.of(Rank.values()).size()-size/4, // Remove first X ranks
                                List.of(Rank.values()).size()));

        for (Suit suit : Suit.values()) {
            for (Rank rank : totalRanks) {
                Card card = new Card(rank, suit);
                deckCards.add(card);
            }
        }
        // Shuffle cards
        Collections.shuffle(deckCards);

        Deck deck = new Deck();

        // Set cards and choose the trump suit
        deck.setCards(deckCards);
        deck.setTrumpSuit(deckCards.get(0).getSuit());

        return deck;
    }

    public List<List<Card>> dealPlayerHands(Deck deck, Integer playerAmount) {
        List<List<Card>> playerHands = new ArrayList<>();

        // Initialize each player's hand
        for (int i = 0; i < playerAmount; i++) {
            playerHands.add(new ArrayList<>());
        }

        // Deal 6 cards to each player
        for (int i = 0; i < playerAmount; i++) {
            for (int j = 0; j < 6; j++) {
                if (!deck.getCards().isEmpty()) {
                    playerHands.get(i).add(deck.getCards().pop());
                }
            }
        }

        return playerHands;
    }

    public GameSetup createGame(Integer size, Integer playerAmount) {
        Deck deck = generateDeck(size, playerAmount);
        List<List<Card>> playerHands = dealPlayerHands(deck, playerAmount);

        GameSetup gameSetup = new GameSetup();
        gameSetup.setDeck(deck);
        gameSetup.setPlayerHands(playerHands);
        gameSetup.setTrumpSuit(deck.getTrumpSuit());


        return gameSetup;
    }
}
