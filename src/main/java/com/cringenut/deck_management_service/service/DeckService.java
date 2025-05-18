package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.enums.Rank;
import com.cringenut.deck_management_service.enums.Suit;
import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DeckService {

    // Use player amount later to determine deck min size
    public Deck createDeck(Integer size, Integer playerAmount) {
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
}
