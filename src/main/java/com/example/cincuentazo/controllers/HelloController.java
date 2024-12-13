package com.example.cincuentazo.controllers;

import com.example.cincuentazo.views.PlayersView;
import com.example.cincuentazo.views.alert.AlertBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button buttonGreen;

    @FXML
    private Button buttonOrange;

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
