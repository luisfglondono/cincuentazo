package com.example.cincuentazo.models;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
class CardsTest {
    @Test
    void testGetCardValueWithA() {
        Cards card = new Cards("Diamonds", 1, "A", 10);

        assertEquals(1, card.getFirstValue());
        assertEquals(10, card.getSecondValue());
    }

    @Test
    void testGetCardValueWith9() {
        Cards card = new Cards("Hearts", 9, "9", 0);

        assertEquals(9, card.getFirstValue());
    }

    @Test
    void testGetCardValueWithQ() {
        Cards card = new Cards("Spades", -10, "Q", 0);

        assertEquals(-10, card.getFirstValue());
    }


}