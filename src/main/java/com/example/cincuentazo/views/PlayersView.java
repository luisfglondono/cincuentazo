package com.example.cincuentazo.views;

import com.example.cincuentazo.controllers.PlayerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The PlayersView class represents the view for the players in the application.
 * It extends the Stage class and is responsible for loading and displaying the players' view.
 */
public class PlayersView extends Stage {
    /**
     * The controller associated with this view.
     */
    private PlayerController playerController;

    /**
     * Constructs a new PlayersView and initializes its components.
     * Loads the FXML file, sets the controller, title, icon, and scene, and displays the stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public PlayersView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/cincuentazo/players-view.fxml"));
        Parent root = loader.load();
        this.playerController = loader.getController();
        this.setTitle("Cincuentazo");
        Scene scene = new Scene(root);
        this.getIcons().add(new Image(getClass().getResourceAsStream("/com/example/cincuentazo/images/icon.png")));
        this.setScene(scene);
        this.show();
    }
    /**
     * Gets the controller associated with this view.
     *
     * @return the playerController instance
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }
    /**
     * Returns the singleton instance of PlayersView.
     * If the instance does not exist, it creates a new one.
     *
     * @return the singleton instance of PlayersView
     * @throws IOException if the FXML file cannot be loaded
     */
    public static PlayersView getInstance() throws IOException {
        return PlayerViewHolder.INSTANCE = new PlayersView();
    }
    /**
     * Holder class for the singleton instance of PlayersView.
     */
    private static class PlayerViewHolder {
        private static PlayersView INSTANCE;
    }
}
