package com.example.cincuentazo.views;

import com.example.cincuentazo.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * The HelloView class represents the initial view in the application.
 * It extends the Stage class and is responsible for loading and displaying the initial view.
 */
public class HelloView extends Stage {
    /**
     * Constructs a new HelloView and initializes its components.
     * Loads the FXML file, sets the title, icon, and scene, and displays the stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    public HelloView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.setTitle("Cincuentazo - Inicio");
        this.getIcons().add(new Image(
                getClass().getResourceAsStream("/com/example/cincuentazo/images/icon.png")
        ));
        this.setScene(scene);
        this.show();
    }
    /**
     * Holder class for the singleton instance of HelloView.
     */
    private static class HelloViewHolder {
        private static HelloView INSTANCE;
    }
    /**
     * Returns the singleton instance of HelloView.
     * If the instance does not exist, it creates a new one.
     *
     * @return the singleton instance of HelloView
     * @throws IOException if the FXML file cannot be loaded
     */
    public static HelloView getInstance() throws IOException {

        if (HelloViewHolder.INSTANCE == null) {
            return HelloViewHolder.INSTANCE = new HelloView();
        } else {
            return HelloViewHolder.INSTANCE;
        }
    }
}
