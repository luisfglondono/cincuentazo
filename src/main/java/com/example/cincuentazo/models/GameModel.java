package com.example.cincuentazo.models;

import java.util.ArrayList;
import java.util.Random;

/**
 * The GameModel class represents the state and logic of the game.
 * It manages the players, cards, and game flow.
 */
public class GameModel {
    /**
     * The list of players in the game.
     */
    private ArrayList<PlayerModel> players;
    /**
     * The matrix of cards available in the game.
     */
    private ArrayList<ArrayList<Cards>> cardsMatrix;
    /**
     * The matrix of cards that have been played.
     */
    private ArrayList<ArrayList<Cards>> playedCards;
    /**
     * The card that is currently played.
     */
    private Cards playedCard;
    /**
     * The total points accumulated in the game.
     */
    private int total;
    /**
     * The index of the current player's turn.
     */
    private int currentTurn = 0;
    /**
     * The number of cards that have been selected.
     */
    private int cardsSelected = 0;
    /**
     * The card that is given to the player.
     */
    private Cards givenCard = null;
    /**
     * Indicates whether the game has ended.
     */
    private boolean gameEnded = false;
    /**
     * Gets the player at the specified index.
     *
     * @param index the index of the player
     * @return the PlayerModel instance
     */
    public PlayerModel getPlayer(int index) {return this.players.get(index);}
    /**
     * Gets the number of players in the game.
     *
     * @return the number of players
     */
    public int getPlayersSize() {return this.players.size();}
    /**
     * Sets the game ended status.
     *
     * @param gameEnded true if the game has ended, false otherwise
     */
    public void setGameEnded(boolean gameEnded) {this.gameEnded = gameEnded;}
    /**
     * Gets the game ended status.
     *
     * @return true if the game has ended, false otherwise
     */
    public boolean getGameEnded() {return this.gameEnded;}
    /**
     * Sets the given card.
     *
     * @param givenCard the card to be given
     */
    public void setGivenCard(Cards givenCard) {this.givenCard = givenCard;}
    /**
     * Gets the given card.
     *
     * @return the given card
     */
    public Cards getGivenCard() {return this.givenCard;}
    /**
     * Gets the number of cards selected.
     *
     * @return the number of cards selected
     */
    public int getCardsSelected() {return cardsSelected;}
    /**
     * Increments the number of cards selected by one.
     */
    public void setCardsSelected() {this.cardsSelected++;}
    /**
     * Gets the current turn.
     *
     * @return the current turn
     */
    public int getCurrentTurn() {return this.currentTurn;}
    /**
     * Sets the current turn.
     *
     * @param currentTurn the current turn
     */
    public void setCurrentTurn(int currentTurn) {this.currentTurn = currentTurn;}
    /**
     * Gets the total points.
     *
     * @return the total points
     */
    public int getTotal() {return total;}
    /**
     * Sets the total points.
     *
     * @param total the total points
     */
    public void setTotal(int total) {this.total = total;}
    /**
     * Gets the played card.
     *
     * @return the played card
     */
    public Cards getPlayedCard() {return playedCard;}
    /**
     * Sets the played card.
     *
     * @param playedCard the played card
     */
    public void setPlayedCard(Cards playedCard) {this.playedCard = playedCard;}
    /**
     * Constructs a new GameModel and initializes the card matrices.
     */
    public GameModel() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        cardsMatrix = new ArrayList<>();
        playedCards = new ArrayList<>();
        this.total = 0;

        for (String suit : suits) {
            ArrayList<Cards> suitCards = new ArrayList<>();
            for (int i = 1; i <= 13; i++) {
                String id;
                int firstValue;
                int secondValue = 0;

                switch (i) {
                    case 1:
                        id = "A";
                        firstValue = 10;
                        secondValue = 1;
                        break;
                    case 11:
                        id = "J";
                        firstValue = -10;
                        break;
                    case 12:
                        id = "Q";
                        firstValue = -10;
                        break;
                    case 13:
                        id = "K";
                        firstValue = -10;
                        break;
                    case 9:
                        id = String.valueOf(i);
                        firstValue = 0;
                        secondValue = 0;
                        break;
                    default:
                        id = String.valueOf(i);
                        firstValue = i;
                        break;
                }

                suitCards.add(new Cards(suit, firstValue, id, secondValue));
            }
            cardsMatrix.add(suitCards);
        }
        for (int row = 0; row < 4; row++) {
            ArrayList<Cards> rowCards = new ArrayList<>();
            for (int col = 0; col < 13; col++) {
                rowCards.add(null);
            }
            playedCards.add(rowCards);
        }
    }
    /**
     * Sets the players for the game and starts their threads.
     *
     * @param players the list of players to set
     */
    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
        startThreads();
    }
    /**
     * Starts the threads for each player and initializes their decks.
     */
    public void startThreads(){
        for (PlayerModel player : this.players) {
            player.setDeck(getDeck());
            printPlayerDeckIds(player);
            //printAllPlayerDeckAttributes(player);
            player.start();
            System.out.println(player.getNickname() + " started.");
        }
        System.out.println("Cantidad de objetos PlayerModel " + this.players.size());

    }
    /**
     * Executes the first play of the game by selecting a random card and updating the game state.
     */
    public void firstPlay(){
        Cards firstCard = getRandomCard();
        setPlayedCard(firstCard);
        System.out.println("Primera carta:");
        System.out.println("ID: " + firstCard.getId());
        if (firstCard.getId().equals("A")) {
            this.setTotal(this.getTotal() + this.getPlayedCard().getSecondValue());
        } else {
            this.setTotal(this.getTotal() + this.getPlayedCard().getFirstValue());
        }
        updateBothMatrix();
        this.setGivenCard(getRandomCard());
    }
    /**
     * Executes the current player's turn, updating the game state accordingly.
     */
    public void playerPlay(){
        PlayerModel player = this.players.get(this.getCurrentTurn());
        player.setTotal(this.getTotal());
        player.setGivenCard(this.getGivenCard());
        player.activate(this);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("El controlador del juego fue interrumpido.");

        }
        this.setCardsSelected();
        this.setPlayedCard(player.getPlayedCard());
        this.setTotal(player.getTotal());
        this.updateBothMatrix();
        if (allCardsTaken()) {
            reFillCardsMatrix();
        }
        this.printCardsMatrixIds();
        this.printPlayedCardsMatrixIds();
        this.setGivenCard(getRandomCard());
        this.setCurrentTurn((this.getCurrentTurn() + 1) % this.players.size());
    }

    public void updateData() {
        PlayerModel player = this.players.get(0);

        this.setCardsSelected();
        this.setPlayedCard(player.getPlayedCard());
        this.setTotal(player.getTotal());
        this.updateBothMatrix();
        if (allCardsTaken()) {
            reFillCardsMatrix();
        }
        this.printCardsMatrixIds();
        this.printPlayedCardsMatrixIds();
        this.setGivenCard(getRandomCard());
    }



    /**
     * Deletes the current player from the game and updates the game state.
     */
    public void deletePlayer(){
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Mazo del jugador eliminado:");
        this.players.get(this.getCurrentTurn()).getDeck().printDeckAttributes();
        System.out.println("Total de la mesa: " + this.getTotal());
        System.out.println(this.players.get(this.getCurrentTurn()).getNickname() + " ha sido eliminado.");
        System.out.println("------------------------------------------------------------------------------");
        this.returnPlayerCardsToMatrix(this.players.get(this.getCurrentTurn()));
        this.players.remove(this.getCurrentTurn());
        this.setCurrentTurn((this.getCurrentTurn()) % this.players.size());
        this.printCardsMatrixIds();
    }
    /**
     * Updates both the cards matrix and the played cards matrix with the current played card.
     */
    public void updateBothMatrix(){
        for (ArrayList<Cards> suitCards : cardsMatrix) {
            for (int i = 0; i < suitCards.size(); i++) {
                if (suitCards.get(i) == this.getPlayedCard()) {
                    suitCards.set(i, null);
                    break;
                }
            }
        }
        boolean placed = false;
        for (int row = 3; row >= 0 && !placed; row--) {
            while (playedCards.size() <= row) {
                playedCards.add(new ArrayList<>());
            }
            for (int col = 12; col >= 0 && !placed; col--) {
                while (playedCards.get(row).size() <= col) {
                    playedCards.get(row).add(null);
                }
                if (playedCards.get(row).get(col) == null) {
                    playedCards.get(row).set(col, this.getPlayedCard());
                    placed = true;
                }
            }
        }
    }
    /**
     * Gets a random card from the cards matrix.
     *
     * @return a random card
     */
    public Cards getRandomCard() {
        Random random = new Random();
        Cards card;
        do {
            int suitIndex = random.nextInt(cardsMatrix.size());
            int cardIndex = random.nextInt(cardsMatrix.get(suitIndex).size());
            card = cardsMatrix.get(suitIndex).get(cardIndex);
        } while (card == null || card.isTaken());
        card.setTaken(true);
        this.setCardsSelected();
        return card;
    }
    /**
     * Prints the IDs of the cards in the cards matrix.
     */
    public void printCardsMatrixIds() {
        System.out.println("Id de las cartas en la matriz cardsMatrix:");
        ArrayList<ArrayList<String>> idsByRow = new ArrayList<>();

        for (ArrayList<Cards> suitCards : cardsMatrix) {
            ArrayList<String> rowIds = new ArrayList<>();
            for (Cards card : suitCards) {
                if (card != null) {
                    rowIds.add(card.getId());
                } else {
                    rowIds.add("null");
                }
            }
            idsByRow.add(rowIds);
        }

        for (int i = 0; i < idsByRow.size(); i++) {
            System.out.println("Fila " + (i + 1) + ": " + idsByRow.get(i));
        }
        System.out.println("Fin de la matriz cardsMatrix");
    }
    /**
     * Prints the IDs of the cards in the played cards matrix.
     */
    public void printPlayedCardsMatrixIds() {
        System.out.println("Id de las cartas en la matriz playedCards:");
        ArrayList<ArrayList<String>> idsByRow = new ArrayList<>();

        for (ArrayList<Cards> suitCards : playedCards) {
            ArrayList<String> rowIds = new ArrayList<>();
            for (Cards card : suitCards) {
                if (card != null) {
                    rowIds.add(card.getId());
                } else {
                    rowIds.add("null");
                }
            }
            idsByRow.add(rowIds);
        }

        for (int i = 0; i < idsByRow.size(); i++) {
            System.out.println("Fila " + (i + 1) + ": " + idsByRow.get(i));
        }
        System.out.println("Fin de la matriz playedCards");
    }
    /**
     * Gets a deck of cards for a player.
     *
     * @return a list of cards representing the deck
     */
    public ArrayList<Cards> getDeck() {
        ArrayList<Cards> deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            deck.add(getRandomCard());
        }
        return deck;
    }
    /**
     * Prints the IDs of the cards in the player's deck.
     *
     * @param player the player whose deck IDs are to be printed
     */
    public void printPlayerDeckIds(PlayerModel player) {
        ArrayList<String> cardIds = new ArrayList<>();
        Deck deck = player.getDeck();

        for (int i = 0; i < deck.getLenght(); i++) {
            Cards card = deck.getCardAt(i);
            if (card != null) {
                cardIds.add(card.getId());
            } else {
                cardIds.add("null");
            }
        }

        System.out.println("IDs de las cartas en el mazo del " + player.getNickname() + ": " + cardIds);
    }
    /**
     * Checks if all cards have been taken from the cards matrix.
     *
     * @return true if all cards have been taken, false otherwise
     */
    public boolean allCardsTaken() {
        for (ArrayList<Cards> suitCards : cardsMatrix) {
            for (Cards card : suitCards) {
                if (card != null && !card.isTaken()) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns the player's cards to the cards matrix.
     *
     * @param player the player whose cards are to be returned
     */
    public void returnPlayerCardsToMatrix(PlayerModel player) {
        ArrayList<Cards> deck = player.getDeck().getDeck();
        for (Cards card : deck) {
            if (card != null) {
                card.setTaken(false);
                boolean placed = false;
                for (ArrayList<Cards> suitCards : cardsMatrix) {
                    for (int i = 0; i < suitCards.size(); i++) {
                        if (suitCards.get(i) == null) {
                            suitCards.set(i, card);
                            placed = true;
                            break;
                        }
                    }
                    if (placed) break;
                }
            }
        }
        player.getDeck().getDeck().clear(); // Clear the player's deck after returning cards to the matrix
    }
    /**
     * Refills the cards matrix with cards from the played cards matrix.
     */
    public void reFillCardsMatrix() {
        Cards lastCard = null;
        int lastRow = -1, lastCol = -1;

        for (int row = 3; row >= 0; row--) {
            for (int col = 12; col >= 0; col--) {
                Cards card = playedCards.get(row).get(col);
                if (card != null) {
                    lastCard = card;
                    lastRow = row;
                    lastCol = col;
                    card.setTaken(false);
                    boolean placed = false;
                    for (ArrayList<Cards> suitCards : cardsMatrix) {
                        for (int i = 0; i < suitCards.size(); i++) {
                            if (suitCards.get(i) == null) {
                                suitCards.set(i, card);
                                placed = true;
                                break;
                            }
                        }
                        if (placed) break;
                    }
                    playedCards.get(row).set(col, null);
                } else {
                    break;
                }
            }
        }

        if (lastCard != null) {
            playedCards.get(3).set(12, lastCard);
        }
        System.out.println("Se rellenó la matriz cardsMatrix con las cartas de la matriz playedCards.");
    }
    /**
     * Checks if the player can play a card.
     *
     * @param player the player to check
     * @return true if the player can play a card, false otherwise
     */
    public boolean canPlayCard(PlayerModel player) {
        ArrayList<Cards> deck = player.getDeck().getDeck();
        for (Cards card : deck) {
            if (card != null) {
                int cardValue = card.getId().equals("A") ? card.getSecondValue() : card.getFirstValue();
                if (this.total + cardValue <= 50) {
                    return true;
                }
            }
        }
        return false;
    }

}
