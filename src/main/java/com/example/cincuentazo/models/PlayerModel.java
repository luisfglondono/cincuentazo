package com.example.cincuentazo.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * The PlayerModel class represents a player in the game.
 * It manages the player's deck, nickname, and actions during the game.
 */
public class PlayerModel extends Thread {
    /**
     * The deck of cards the player holds.
     */
    private Deck deck;
    /**
     * The nickname of the player.
     */
    private String nickname;
    /**
     * Indicates whether the player is active or not.
     */
    private boolean active = false;
    /**
     * The total points the player has accumulated.
     */
    private int total = 0;
    /**
     * The card the player has played.
     */
    private Cards playedCard;
    /**
     * The card given to the player.
     */
    private Cards givenCard;
    /**
     * Constructs a new PlayerModel with an empty deck.
     */
    public PlayerModel() {deck = new Deck();}
    /**
     * Sets the player's deck with the specified list of cards.
     *
     * @param deck the list of cards to set in the player's deck
     */
    public void setDeck(ArrayList<Cards> deck) {this.deck.setDeck(deck);}
    /**
     * Gets the player's deck.
     *
     * @return the player's deck
     */
    public Deck getDeck() {return deck;}
    /**
     * Sets the player's nickname.
     *
     * @param nickname the nickname of the player
     */
    public void setNickname(String nickname) {this.nickname = nickname;}
    /**
     * Gets the player's nickname.
     *
     * @return the nickname of the player
     */
    public String getNickname() {return nickname;}
    /**
     * Sets the total points the player has accumulated.
     *
     * @param total the total points of the player
     */
    public void setTotal(int total) {this.total = total;}
    /**
     * Gets the total points the player has accumulated.
     *
     * @return the total points of the player
     */
    public int getTotal() {return total;}
    /**
     * Gets the card the player has played.
     *
     * @return the card the player has played
     */
    public Cards getPlayedCard() {return playedCard;}
    /**
     * Sets the card the player has played.
     *
     * @param playedCard the card the player has played
     */
    public void setPlayedCard(Cards playedCard) {this.playedCard = playedCard;}
    /**
     * Gets the card given to the player.
     *
     * @return the card given to the player
     */
    public Cards givenCard() {return givenCard;}
    /**
     * Sets the card given to the player.
     *
     * @param givenCard the card given to the player
     */
    public void setGivenCard(Cards givenCard) {this.givenCard = givenCard;}
    /**
     * Plays a card from the player's deck that does not exceed the total points limit.
     *
     * @param total the current total points in the game
     */
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
    /**
     * Takes a card and adds it to the player's deck.
     *
     * @param card the card to be taken
     */
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
    /**
     * Runs the player's actions in a separate thread.
     * The player plays and takes cards while active.
     */
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
    /**
     * Activates the player if the deck has the required number of cards.
     *
     * @param gameModel the game model to interact with
     */
    public synchronized void activate(GameModel gameModel) {
        if (deck.getLenght() == 4) {
            this.active = true;
        }
    }
}
