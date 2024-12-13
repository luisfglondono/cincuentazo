package com.example.cincuentazo.models;

import java.util.ArrayList;
/**
 * The Deck class represents a deck of cards in the game.
 * It manages the collection of cards and provides methods to manipulate the deck.
 */
public class Deck {
    /**
     * The length of the deck.
     */
    private int lenght;
    /**
     * The list of cards in the deck.
     */
    private ArrayList<Cards> deck;
    /**
     * Constructs a new Deck with an initial length of 0 and an empty list of cards.
     */
    public Deck() {
        this.lenght = 0;
        this.deck = new ArrayList<>();
    }
    /**
     * Gets the list of cards in the deck.
     *
     * @return the list of cards in the deck
     */
    public ArrayList<Cards> getDeck() {return deck;}
    /**
     * Gets the card at the specified index in the deck.
     *
     * @param index the index of the card to retrieve
     * @return the card at the specified index
     */
    public Cards getCardAt(int index) {return deck.get(index);}
    /**
     * Removes the card at the specified index in the deck by setting it to null.
     *
     * @param index the index of the card to remove
     */
    public void removeCardAt(int index) {deck.set(index, null);}
    /**
     * Sets the card at the specified index in the deck.
     *
     * @param index the index to set the card at
     * @param card the card to set at the specified index
     */
    public void setCardAt(int index, Cards card) {deck.set(index, card);}
    /**
     * Gets the length of the deck.
     *
     * @return the length of the deck
     */
    public int getLenght() {return lenght;}
    /**
     * Prints the attributes of the deck, specifically the IDs of the cards in the deck.
     */
    public void printDeckAttributes() {
        ArrayList<String> cardIds = new ArrayList<>();
        for (int i = 0; i < deck.size(); i++) {
            Cards card = getCardAt(i);
            if (card != null) {
                cardIds.add(card.getId());

            } else {
                cardIds.add("null");
            }
        }
        System.out.println("Id de las cartas en el mazo: " + cardIds);
    }
    /**
     * Sets the deck with the specified list of cards and updates the length.
     *
     * @param deck the list of cards to set in the deck
     */
    public void setDeck(ArrayList<Cards> deck) {
        this.lenght = deck.size();
        this.deck = deck;
    }
}
