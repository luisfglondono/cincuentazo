package com.example.cincuentazo.models;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private ArrayList<PlayerModel> players;
    private ArrayList<ArrayList<Cards>> cardsMatrix;
    private ArrayList<ArrayList<Cards>> playedCards;
    private Cards playedCard;
    private int total;
    private int currentTurn = 0;
    private int cardsSelected = 0;
    private Cards givenCard = null;
    private boolean gameEnded = false;

    public void setGameEnded(boolean gameEnded) {this.gameEnded = gameEnded;}
    public boolean getGameEnded() {return this.gameEnded;}
    public void setGivenCard(Cards givenCard) {this.givenCard = givenCard;}
    public Cards getGivenCard() {return this.givenCard;}
    public int getCardsSelected() {return cardsSelected;}
    public void setCardsSelected() {this.cardsSelected++;}
    public int getCurrentTurn() {return this.currentTurn;}
    public void setCurrentTurn(int currentTurn) {this.currentTurn = currentTurn;}
    public int getTotal() {return total;}
    public void setTotal(int total) {this.total = total;}
    public Cards getPlayedCard() {return playedCard;}
    public void setPlayedCard(Cards playedCard) {this.playedCard = playedCard;}

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
    public void setPlayers(ArrayList<PlayerModel> players) {
        this.players = players;
        startThreads();
        startGame();
    }
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
    public void startGame() {

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
        while (true) {
            if (this.players.size() == 1) {
                System.out.println("El juego ha terminado. Solo queda un jugador.");
                this.setGameEnded(true);
                break;
            }
            if (canPlayCard(this.players.get(this.getCurrentTurn())))
            {
                PlayerModel player = this.players.get(this.getCurrentTurn());
                player.setTotal(this.getTotal());
                player.setGivenCard(this.getGivenCard());
                player.activate(this);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("El controlador del juego fue interrumpido.");
                    break;
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
            else {
                System.out.println("El jugador " + this.players.get(this.getCurrentTurn()).getNickname() + " ha sido eliminado.");
                this.returnPlayerCardsToMatrix(this.players.get(this.getCurrentTurn()));
                this.setCurrentTurn((this.getCurrentTurn() + 1) % this.players.size());
                this.players.remove(this.getCurrentTurn());
                this.printCardsMatrixIds();
            }

        }
    }
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
    public ArrayList<Cards> getDeck() {
        ArrayList<Cards> deck = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            deck.add(getRandomCard());
        }
        return deck;
    }
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
