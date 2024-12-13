package com.example.cincuentazo.controllers;

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

public class PlayerController {

    @FXML
    private Label informationLabel;

    private ArrayList<PlayerModel> players = new ArrayList<>();

    @FXML
    void onActionHelpButton(ActionEvent event) {
        new AlertBox().showAlert(
                "Instrucciones",
                "",
                "Debes seleccionar una cantidad de jugadores, (una vez que seleccione una cantidad de jugadores no podrá cambiarla).",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    void onActionPlayer1Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = new PlayerModel();
                player1.setNickname("Jugador 1");
                PlayerModel player2 = new PlayerModel();
                player2.setNickname("Jugador 2");
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

    @FXML
    void onActionPlayer2Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = new PlayerModel();
                player1.setNickname("Jugador 1");
                PlayerModel player2 = new PlayerModel();
                player2.setNickname("Jugador 2");
                PlayerModel player3 = new PlayerModel();
                player3.setNickname("Jugador 3");
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

    @FXML
    void onActionPlayer3Button(ActionEvent event) {
        AlertBox alertBox = new AlertBox();
        boolean confirmed = alertBox.showConfirmation("Confirmacion", "¿Estas seguro que quieres comenzar a jugar?");
        if (confirmed)
        {
            try
            {
                PlayerModel player1 = new PlayerModel();
                player1.setNickname("Jugador 1");
                PlayerModel player2 = new PlayerModel();
                player2.setNickname("Jugador 2");
                PlayerModel player3 = new PlayerModel();
                player3.setNickname("Jugador 3");
                PlayerModel player4 = new PlayerModel();
                player4.setNickname("Jugador 4");
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
