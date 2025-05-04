package com.cringenut.deck_management_service.model;

import com.cringenut.deck_management_service.enums.Suit;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@Data
public class Deck {

    private Stack<Card> cards = new Stack<>();
    private Suit trumpSuit = Suit.SPADES;

}
