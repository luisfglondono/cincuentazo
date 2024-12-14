package com.example.cincuentazo.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing CardsTest.
 */
class CardsTest {
    /**
     * Test to validate the value of the card.
     */
    @Test
    void testGetCardValueWithA() {
        Cards card = new Cards("Diamonds", 1, "A", 10);

        assertEquals(1, card.getFirstValue());
        assertEquals(10, card.getSecondValue());
    }

    /**
     * Test to validate the value of the card.
     */
    @Test
    void testGetCardValueWith9() {
        Cards card = new Cards("Hearts", 9, "9", 0);

        assertEquals(9, card.getFirstValue());
    }

    /**
     * Test to validate the value of the card.
     */
    @Test
    void testGetCardValueWithQ() {
        Cards card = new Cards("Spades", -10, "Q", 0);

        assertEquals(-10, card.getFirstValue());
    }


}