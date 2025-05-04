package com.cringenut.deck_management_service.model;

import com.cringenut.deck_management_service.enums.Suit;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Stack;

@Data
public class GameSetup {

    private Deck deck;
    private List<List<Card>> playerHands;
    private Suit trumpSuit;

}
