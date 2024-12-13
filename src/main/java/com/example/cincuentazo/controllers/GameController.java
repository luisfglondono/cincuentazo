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


public class GameController {
    public GameController() {
        this.gameModel = new GameModel();
    }
    private GameModel gameModel;

    @FXML
    private Button buttonStartGame;

    @FXML
    private Button buttonNewGame;

    public GameModel getGameModel() {return this.gameModel;}
    public void startGame() {
        new Thread(() -> {
            this.gameModel.firstPlay();
            while (true) {
                try {
                    if (this.gameModel.getPlayersSize() == 1) {
                        System.out.println("El juego ha terminado. Solo queda un jugador.");
                        this.gameModel.setGameEnded(true);
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
                    break; // Exit the loop on error
                }
            }
        }).start();
    }
    /**
    public void startGame() {
        this.gameModel.firstPlay();
        while (true) {
            if (this.gameModel.getPlayersSize() == 1) {
                System.out.println("El juego ha terminado. Solo queda un jugador.");
                this.gameModel.setGameEnded(true);
                break;
            }
            if (this.gameModel.canPlayCard(this.gameModel.getPlayer((this.gameModel.getCurrentTurn()))))
            {
                this.gameModel.playerPlay();
            }
            else {
                this.gameModel.deletePlayer();
            }

        }
    }
     **/
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
