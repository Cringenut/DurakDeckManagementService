package com.cringenut.deck_management_service.model;

import com.cringenut.deck_management_service.enums.Suit;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class Deck {

    List<Card> cards = new ArrayList<>();

}
