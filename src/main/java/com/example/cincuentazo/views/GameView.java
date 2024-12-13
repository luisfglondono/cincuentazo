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

/**
 * The GameView class represents the view for the game in the application.
 * It extends the Stage class and is responsible for loading and displaying the game's view.
 */
public class GameView extends Stage {
    /**
     * The controller associated with this view.
     */
    private GameController gameController;

    /**
     * Constructs a new GameView and initializes its components.
     * Loads the FXML file, sets the controller, title, icon, and scene, and displays the stage.
     * Also sets the players in the game model.
     *
     * @param players the list of players to be set in the game model
     * @throws IOException if the FXML file cannot be loaded
     */
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
    /**
     * Returns the singleton instance of GameView.
     * If the instance does not exist, it creates a new one.
     *
     * @param players the list of players to be set in the game model
     * @return the singleton instance of GameView
     * @throws IOException if the FXML file cannot be loaded
     */
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
