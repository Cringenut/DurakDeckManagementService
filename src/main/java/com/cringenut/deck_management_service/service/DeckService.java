package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.enums.Rank;
import com.cringenut.deck_management_service.enums.Suit;
import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import com.cringenut.deck_management_service.model.GameSetup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeckService {


    public Deck generateDeck(Integer size, Integer playerAmount) {
        List<Card> deckCards = new ArrayList<>();
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

    public ResponseEntity<GameSetup> generateGame(Integer size, Integer playerAmount) {


        return null;
    }
}
