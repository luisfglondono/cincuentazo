package com.example.cincuentazo.views.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * The AlertBox class provides methods to display different types of alert dialogs.
 * It implements the AlertBoxInterface.
 */
public class AlertBox implements AlertBoxInterface {
    /**
     * Enum representing the types of alerts that can be shown.
     */
    public enum AlertType {
        INFORMATION,
        ERROR
    }
    /**
     * Displays an alert dialog with the specified title, header, message, and alert type.
     *
     * @param title   the title of the alert dialog
     * @param header  the header text of the alert dialog
     * @param message the content message of the alert dialog
     * @param type    the type of the alert dialog (e.g., INFORMATION, ERROR)
     */
    @Override
    public void showAlert(String title, String header, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Displays a confirmation dialog with the specified title and message.
     * Returns true if the user selects "Yes", and false otherwise.
     *
     * @param title   the title of the confirmation dialog
     * @param message the content message of the confirmation dialog
     * @return true if the user selects "Yes", false otherwise
     */
    public boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType buttonTypeYes = new ButtonType("Si");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonTypeYes;
    }

    public int showValueA() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Elige una opción");
        alert.setHeaderText("¿Qué número prefieres?");
        alert.setContentText("Selecciona 10 o 1:");
        ButtonType buttonType10 = new ButtonType("10");
        ButtonType buttonType1 = new ButtonType("1");

        alert.getButtonTypes().setAll(buttonType10, buttonType1);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonType10 ? 10 : 1;
    }
}

