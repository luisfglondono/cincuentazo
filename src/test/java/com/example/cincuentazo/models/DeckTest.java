package com.example.cincuentazo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    ArrayList<Cards> cardsList;
    Deck deck;

    Cards card0;
    Cards card1;
    Cards card2;

    @BeforeEach
    public void setUp() {
        cardsList = new ArrayList<>();
        deck = new Deck();

        card0 = new Cards("Diamonds", 1, "A", 10);
        card1 = new Cards("Hearts", 9, "9", 0);
        card2 = new Cards("Spades", -10, "Q", 0);

        cardsList.add(card0);
        cardsList.add(card1);
        cardsList.add(card2);

        deck.setDeck(cardsList);
    }

    @Test
    void testGetCardDeck() {
        assertEquals(card0, deck.getCardAt(0));
        assertEquals(card1, deck.getCardAt(1));
        assertEquals(card2, deck.getCardAt(2));
    }

    @Test
    void testSetCardDeck() {
        Cards card0 = new Cards("Diamonds", 1, "A", 10);

        deck.setCardAt(0, card0);

        assertEquals(card0, deck.getCardAt(0));
    }
}