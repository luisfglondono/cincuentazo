package com.example.cincuentazo.controllers;

import com.example.cincuentazo.views.PlayersView;
import com.example.cincuentazo.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The HelloController class is responsible for handling the initial view's actions.
 * It manages the transition to the players' view and displays information about the game.
 */
public class HelloController {
    /**
     * Button to start the game.
     */
    @FXML
    private Button buttonGreen;
    /**
     * Button to display information about the game.
     */
    @FXML
    private Button buttonOrange;
    /**
     * Handles the action when the green button is clicked.
     * Transitions to the players' view.
     *
     * @param event the action event triggered by the green button
     */
    @FXML
    void onActionButtonGreen(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayersView playersView = new PlayersView();
                playersView.show();
                ((Stage) buttonGreen.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * Handles the action when the orange button is clicked.
     * Displays an alert with information about the game.
     *
     * @param event the action event triggered by the orange button
     */
    @FXML
    void onActionButtonOrange(ActionEvent event) {
        new AlertBox().showAlert(
                "Bienvenido a Cincuentazo",
                "Descripción",
                "Cincuentazo es un juego de cartas donde los jugadores (un humano y entre 1 y 3 máquinas) deben evitar que la suma de las cartas en la mesa exceda los 50 puntos. Cada jugador comienza con 4 cartas y, en su turno, selecciona una carta para jugar según las reglas del juego, actualizando la suma en la mesa. Si un jugador no puede realizar un movimiento válido, queda eliminado. El juego finaliza cuando solo queda un jugador, quien es declarado ganador.",
                Alert.AlertType.INFORMATION
        );
    }

}
