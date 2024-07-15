package com.example.project_sudokugame_00.GameObject;

import javafx.scene.control.Alert;

public class Messenger {
    public static void showCompleteGDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null); // Optional header text, set to null to hide it
        alert.setContentText("You win, if you want to try again, press Start");
        // Display the alert dialog window
        alert.showAndWait();
    }
}
