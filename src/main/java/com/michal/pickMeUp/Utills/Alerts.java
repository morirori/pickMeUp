package com.michal.pickMeUp.Utills;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Created by Michał on 14.08.2017.
 */
public class Alerts {
    Alert alert;
    public Alerts(String message, Alert.AlertType type) {
        alert = new Alert(type);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }
}
