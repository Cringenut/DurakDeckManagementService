package com.cringenut.deck_management_service.service;

import com.cringenut.deck_management_service.enums.Rank;
import com.cringenut.deck_management_service.enums.Suit;
import com.cringenut.deck_management_service.model.Card;
import com.cringenut.deck_management_service.model.Deck;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {


    public ResponseEntity<Deck> generateDeck(Integer size, Integer playerAmount) {
        List<Card> deckCards = new ArrayList<Card>();
        List<Rank> ranks = new ArrayList<>(
                List.of(Rank.values())
                        .subList(
                                size/4,
                                List.of(Rank.values()).size()));

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                deckCards.add(card);
            }
        }

        return null;
    }
}
