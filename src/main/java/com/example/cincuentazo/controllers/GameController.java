package com.example.cincuentazo.controllers;

import com.example.cincuentazo.models.Cards;
import com.example.cincuentazo.models.Deck;
import com.example.cincuentazo.models.GameModel;
import com.example.cincuentazo.models.PlayerModel;
import com.example.cincuentazo.views.HelloView;
import com.example.cincuentazo.views.alert.AlertBox;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The GameController class is responsible for managing the game logic and interactions.
 * It handles the game flow, player actions, and transitions between views.
 */
public class GameController {

    @FXML
    private ImageView card0;

    @FXML
    private ImageView card1;

    @FXML
    private ImageView card2;

    @FXML
    private ImageView card3;

    @FXML
    private ImageView cardBack;

    @FXML
    private ImageView cardGame;

    private int cardIndex;

    private boolean played = false;

    /**
     * The model representing the game's state and logic.
     */
    private GameModel gameModel;
    /**
     * Button to start the game.
     */
    @FXML
    private Button buttonStartGame;

    @FXML
    private Label points;
    /**
     * Button to start a new game.
     */
    @FXML
    private Button buttonNewGame;

    /**
     * Constructs a new GameController and initializes the game model.
     */
    public GameController() {this.gameModel = new GameModel();}

    /**
     * Gets the game model associated with this controller.
     *
     * @return the GameModel instance
     */
    public GameModel getGameModel() {return this.gameModel;}

    public void initialize() {
        card0.setOnMousePressed(this::handleMousePressed);
        card1.setOnMousePressed(this::handleMousePressed);
        card2.setOnMousePressed(this::handleMousePressed);
        card3.setOnMousePressed(this::handleMousePressed);

        cardBack.setOnMousePressed(this::handleMousePressed);
        points.setText("hola");
    }

    public void updateCards() {
        ArrayList<Cards> deckPlayer = gameModel.getPlayer(0).getDeck().getDeck();

        ArrayList<Image> images = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            images.add(null);
        }

        for (int i = 0; i < deckPlayer.size(); i++) {
            if (deckPlayer.get(i) != null) {
                Image imageCard = new Image(getClass().getResourceAsStream(deckPlayer.get(i).getImage()));

                images.set(i, imageCard);
            } else {
                images.set(i, null);
            }
        }

        card0.setImage(images.get(0));
        card1.setImage(images.get(1));
        card2.setImage(images.get(2));
        card3.setImage(images.get(3));

        Image imageCard = new Image(getClass().getResourceAsStream(gameModel.getPlayedCard().getImage()));

        cardGame.setImage(imageCard);

        if (gameModel.getPlayedCard() != null) {
            Platform.runLater(() -> points.setText(String.valueOf(gameModel.getTotal())));
        }
    }

    public void handleMousePressed(MouseEvent event) {
        PlayerModel player = gameModel.getPlayer(0);

        Object source = event.getSource();

        if (gameModel.getCurrentTurn() == 0 && played == false && buttonStartGame.isDisabled()) {

            if (source == card0) {
                cardIndex = 0;
            } else if (source == card1) {
                cardIndex = 1;
            } else if (source == card2) {
                cardIndex = 2;
            } else {
                cardIndex = 3;
            }

            Cards cardPressed = player.getDeck().getCardAt(cardIndex);

            int valueCard;

            if (cardPressed.getId().equals("A") && 10 + gameModel.getTotal() < 50) {
                AlertBox alertBox = new AlertBox();
                valueCard = alertBox.showValueA();
            } else {
                valueCard = cardPressed.getFirstValue();
            }

            if (valueCard + gameModel.getTotal() <= 50) {

                player.setJugador(true);
                player.setGivenCard(gameModel.getGivenCard());
                player.setTotal(gameModel.getTotal());


                gameModel.getPlayer(0).activate(gameModel);

                player.setPlayedCard(cardPressed);
                gameModel.updateData();

                player.getDeck().setCardAt(cardIndex, null);

                System.out.println("Valor carta" + valueCard);
                System.out.println("total: " + gameModel.getTotal());

                player.setTotal(player.getTotal() + valueCard);

                gameModel.setTotal(player.getTotal());

                played = true;
            }
        }

        if (source == cardBack && played) {
            player.getDeck().setCardAt(cardIndex, gameModel.getGivenCard());

            gameModel.setCurrentTurn((gameModel.getCurrentTurn() + 1) % gameModel.getPlayersSize());

            updateCards();

            played = false;
        }
    }

    /**
     * Starts the game in a new thread, managing the game loop and player actions.
     */
    public void startGame() {

        new Thread(() -> {
            this.gameModel.firstPlay();
            updateCards();
            while (true) {
                try {
                    if (this.gameModel.getPlayersSize() == 1) {
                        System.out.println("El juego ha terminado. Solo queda un jugador.");
                        this.gameModel.setGameEnded(true);
                        System.out.println("El ganador es: " + this.gameModel.getPlayer(0).getNickname());
                        break;
                    }
                    if (this.gameModel.canPlayCard(this.gameModel.getPlayer(this.gameModel.getCurrentTurn()))) {
                        updateCards();
                        if (this.gameModel.getCurrentTurn() != 0) {
                            this.gameModel.playerPlay();
                        }
                    } else {

                        this.gameModel.deletePlayer();
                    }
                } catch (Exception e) {
                    System.err.println("An error occurred during the game: " + e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }
    /**
     * Handles the action when the start game button is clicked.
     * Displays a confirmation alert and starts the game if confirmed.
     *
     * @param event the action event triggered by the start game button
     */
    @FXML
    void onActionStartGame(ActionEvent event) {
        buttonStartGame.setDisable(true);
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres empezar a jugar? (El tiempo empezará a correr y deberas lanzar y tomar una carta)");
        if (confirmed) {
            this.startGame();
        } else {
            buttonStartGame.setDisable(false);
        }
    }
    /**
     * Handles the action when the new game button is clicked.
     * Displays a confirmation alert and transitions to the initial view if confirmed.
     *
     * @param event the action event triggered by the new game button
     */
    @FXML
    void onActionNewGameButton(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar un nuevo juego?");
        if (confirmed)
        {
            try
            {
                this.gameModel = null;
                HelloView hiView = HelloView.getInstance();
                hiView.show();
                ((Stage) buttonNewGame.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }



}
