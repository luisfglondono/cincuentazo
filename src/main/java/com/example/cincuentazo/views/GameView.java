package com.example.cincuentazo.views;

import com.example.cincuentazo.controllers.GameController;
import com.example.cincuentazo.controllers.PlayerController;
import com.example.cincuentazo.models.PlayerModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class GameView extends Stage {
    /**
     * The controller associated with this view.
     */
    private GameController gameController;


    public GameView(ArrayList<PlayerModel> players) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cincuentazo/game-view.fxml"));
        Parent root = loader.load();
        this.gameController = loader.getController();
        this.setTitle("Cincuentazo");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/cincuentazo/images/icon.png")));
        this.setScene(scene);
        this.show();
        this.gameController.getGameModel().setPlayers(players);
    }
    /**
     * Gets the controller associated with this view.
     *
     * @return the GameController instance
     */
    public GameController getGameController() {
        return this.gameController;
    }

    public static GameView getInstance(ArrayList<PlayerModel> players) throws IOException {
        return GameView.GameViewHolder.INSTANCE = new GameView(players);
    }
    /**
     * Holder class for the singleton instance of GameView.
     */
    private static class GameViewHolder {
        private static GameView INSTANCE;
    }
}
