package com.cringenut.deck_management_service.model;

import com.cringenut.deck_management_service.enums.Suit;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GameSetup {

    private Map<String, List<Card>> playerHands;
    private List<Card> remainingDeck;
    private Suit trumpSuit;

}
