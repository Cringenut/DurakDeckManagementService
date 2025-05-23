package com.cringenut.deck_management_service.controller;

import com.cringenut.deck_management_service.model.Deck;
import com.cringenut.deck_management_service.service.DeckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("deck")
public class DeckController {

    @Autowired
    DeckService deckService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Deck createDeck(
            @RequestParam Integer size,
            @RequestParam Integer playerAmount) {
        return deckService.createDeck(size, playerAmount);
    }


}
