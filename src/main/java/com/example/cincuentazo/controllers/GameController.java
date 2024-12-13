package com.example.cincuentazo.controllers;

import com.example.cincuentazo.models.GameModel;
import com.example.cincuentazo.models.PlayerModel;
import com.example.cincuentazo.views.GameView;
import com.example.cincuentazo.views.HelloView;
import com.example.cincuentazo.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The GameController class is responsible for managing the game logic and interactions.
 * It handles the game flow, player actions, and transitions between views.
 */
public class GameController {

    /**
     * The model representing the game's state and logic.
     */
    private GameModel gameModel;
    /**
     * Button to start the game.
     */
    @FXML
    private Button buttonStartGame;
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

    /**
     * Starts the game in a new thread, managing the game loop and player actions.
     */
    public void startGame() {
        new Thread(() -> {
            this.gameModel.firstPlay();
            while (true) {
                try {
                    if (this.gameModel.getPlayersSize() == 1) {
                        System.out.println("El juego ha terminado. Solo queda un jugador.");
                        this.gameModel.setGameEnded(true);
                        System.out.println("El ganador es: " + this.gameModel.getPlayer(0).getNickname());
                        break;
                    }
                    if (this.gameModel.canPlayCard(this.gameModel.getPlayer(this.gameModel.getCurrentTurn()))) {
                        this.gameModel.playerPlay();
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
