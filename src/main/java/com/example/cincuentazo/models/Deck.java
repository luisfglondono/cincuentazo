package com.example.cincuentazo.models;

import java.util.ArrayList;

public class Deck {
    private int lenght;
    private ArrayList<Cards> deck;
    public Deck() {
        this.lenght = 0;
        this.deck = new ArrayList<>();
    }

    public ArrayList<Cards> getDeck() {
        return deck;
    }

    public Cards getCardAt(int index) {return deck.get(index);}
    public void removeCardAt(int index) {
        deck.set(index, null);
    }
    public void setCardAt(int index, Cards card) {
        deck.set(index, card);
    }
    public int getLenght() {return lenght;}

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
    public void setDeck(ArrayList<Cards> deck) {
        this.lenght = deck.size();
        this.deck = deck;
    }
}
