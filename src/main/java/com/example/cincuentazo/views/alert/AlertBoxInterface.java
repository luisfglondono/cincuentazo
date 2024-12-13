package com.example.cincuentazo.views.alert;

import javafx.scene.control.Alert;

/**
 * Interface for displaying alert dialogs.
 */
public interface AlertBoxInterface {
    /**
     * Displays an alert dialog with the specified title, header, message, and alert type.
     *
     * @param title   the title of the alert dialog
     * @param header  the header text of the alert dialog
     * @param message the content message of the alert dialog
     * @param Type    the type of the alert dialog (e.g., INFORMATION, ERROR)
     */
    public void showAlert(String title, String header, String message, Alert.AlertType Type);
}
