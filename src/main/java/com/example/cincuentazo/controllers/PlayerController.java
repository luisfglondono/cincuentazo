package com.example.cincuentazo.controllers;

import com.example.cincuentazo.factories.PlayerFactory;
import com.example.cincuentazo.models.PlayerModel;
import com.example.cincuentazo.views.GameView;
import com.example.cincuentazo.views.PlayersView;
import com.example.cincuentazo.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The PlayerController class is responsible for handling the player selection view.
 * It manages the creation of players and transitions to the game view.
 */
public class PlayerController {
    /**
     * Label to display information to the user.
     */
    @FXML
    private Label informationLabel;
    /**
     * Factory to create player instances.
     */
    private PlayerFactory playerFactory = new PlayerFactory();
    /**
     * List of players created for the game.
     */
    private ArrayList<PlayerModel> players = new ArrayList<>();

    /**
     * Displays an alert with instructions for the user.
     *
     * @param event the action event triggered by the help button
     */
    @FXML
    void onActionHelpButton(ActionEvent event) {
        new AlertBox().showAlert(
                "Instrucciones",
                "",
                "Debes seleccionar una cantidad de jugadores, (una vez que seleccione una cantidad de jugadores no podrá cambiarla).",
                Alert.AlertType.INFORMATION
        );
    }
    /**
     * Handles the action when the player 1 button is clicked.
     * Creates two players and transitions to the game view.
     *
     * @param event the action event triggered by the player 1 button
     */
    @FXML
    void onActionPlayer1Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = playerFactory.createPlayer("Jugador 1");
                PlayerModel player2 = playerFactory.createPlayer("Jugador 2");
                this.players.add(player1);
                this.players.add(player2);
                GameView gameView = new GameView(players);
                gameView.show();
                ((Stage) informationLabel.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * Handles the action when the player 2 button is clicked.
     * Creates three players and transitions to the game view.
     *
     * @param event the action event triggered by the player 2 button
     */
    @FXML
    void onActionPlayer2Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = playerFactory.createPlayer("Jugador 1");
                PlayerModel player2 = playerFactory.createPlayer("Jugador 2");
                PlayerModel player3 = playerFactory.createPlayer("Jugador 3");
                this.players.add(player1);
                this.players.add(player2);
                this.players.add(player3);
                GameView gameView = new GameView(players);
                gameView.show();
                ((Stage) informationLabel.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * Handles the action when the player 3 button is clicked.
     * Creates four players and transitions to the game view.
     *
     * @param event the action event triggered by the player 3 button
     */
    @FXML
    void onActionPlayer3Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = playerFactory.createPlayer("Jugador 1");
                PlayerModel player2 = playerFactory.createPlayer("Jugador 2");
                PlayerModel player3 = playerFactory.createPlayer("Jugador 3");
                PlayerModel player4 = playerFactory.createPlayer("Jugador 4");
                this.players.add(player1);
                this.players.add(player2);
                this.players.add(player3);
                this.players.add(player4);
                GameView gameView = new GameView(players);
                gameView.show();
                ((Stage) informationLabel.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
