package com.example.cincuentazo.models;

import java.util.ArrayList;
import java.util.Random;

public class PlayerModel extends Thread {
    private Deck deck;
    private String nickname;
    private boolean active = false;
    private int total = 0;
    private Cards playedCard;
    private Cards givenCard;

    public PlayerModel() {
        deck = new Deck();
    }
    public void setDeck(ArrayList<Cards> deck) {
        this.deck.setDeck(deck);
    }
    public Deck getDeck() {
        return deck;
    }
    public void setNickname(String nickname) {this.nickname = nickname;}
    public String getNickname() {return nickname;}
    public void setTotal(int total) {this.total = total;}
    public int getTotal() {return total;}
    public Cards getPlayedCard() {return playedCard;}
    public void setPlayedCard(Cards playedCard) {this.playedCard = playedCard;}
    public Cards givenCard() {return givenCard;}
    public void setGivenCard(Cards givenCard) {this.givenCard = givenCard;}

    public synchronized void playCard(int total) {
        ArrayList<Cards> deck = getDeck().getDeck();
        System.out.println("Mazo antes de jugar una carta:");
        this.deck.printDeckAttributes();
        for (int i = 0; i < deck.size(); i++) {
            Cards card = deck.get(i);
            if (card != null) {
                int cardValue = card.getId().equals("A") ? card.getSecondValue() : card.getFirstValue();
                if ((total + cardValue) <= 50) {
                    System.out.println(getNickname() + " jugó la carta " + card.getId());
                    setPlayedCard(card);
                    if (card.getId().equals("A")) {
                        this.total += card.getSecondValue();
                    } else {
                        this.total += card.getFirstValue();
                    }
                    this.deck.setCardAt(i, null);
                    System.out.println("Mazo despues de jugar una carta:");
                    this.deck.printDeckAttributes();
                    break;
                }
            }
        }
    }

    public synchronized void takeCard(Cards card) {
        ArrayList<Cards> deck = getDeck().getDeck();
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == null) {
                System.out.println(getNickname() + " tomó la carta " + card.getId());
                this.deck.setCardAt(i, card);
                System.out.println("Mazo despues de tomar una carta:");
                this.deck.printDeckAttributes();
                break;
            }
        }

    }
    @Override
    public void run() {
        while (true) {
            if (!active) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(getNickname() + " fue interrumpido.");
                    return;
                }
                continue;
            }

            try {
                System.out.println(getNickname() + " está jugando.");
                System.out.println(getTotal() + " es la cuenta total que tiene " + getNickname());
                Thread.sleep(3000);
                long startTime = System.currentTimeMillis();
                playCard(this.getTotal());
                takeCard(this.givenCard());
                long elapsedTime = System.currentTimeMillis() - startTime;

                if (elapsedTime < 2000) {
                    Thread.sleep(2000 - elapsedTime);
                }
            } catch (InterruptedException e) {
                System.out.println(getNickname() + " fue interrumpido durante su turno.");
                return;
            }

            active = false;
        }
    }
    public synchronized void activate(GameModel gameModel) {
        if (deck.getLenght() == 4) {
            this.active = true;
        }
    }
}
