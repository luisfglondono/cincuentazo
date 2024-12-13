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
    private Button buttonNewGame;

    public GameModel getGameModel() {return this.gameModel;}

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
