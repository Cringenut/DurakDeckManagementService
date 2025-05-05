package com.cringenut.deck_management_service.model;

import com.cringenut.deck_management_service.enums.Rank;
import com.cringenut.deck_management_service.enums.Suit;
import lombok.Data;

@Data
public class Card {

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private Rank rank;
    private Suit suit;

    @Override
    public String toString() {
        return (rank.getValue() + suit.getSymbol());
    }

}
