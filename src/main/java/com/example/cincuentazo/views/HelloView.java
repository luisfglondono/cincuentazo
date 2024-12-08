package com.example.cincuentazo.views;

import com.example.cincuentazo.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloView extends Stage {
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

    private static class HelloViewHolder {
        private static HelloView INSTANCE;
    }

    public static HelloView getInstance() throws IOException {

        if (HelloViewHolder.INSTANCE == null) {
            return HelloViewHolder.INSTANCE = new HelloView();
        } else {
            return HelloViewHolder.INSTANCE;
        }
    }
}
