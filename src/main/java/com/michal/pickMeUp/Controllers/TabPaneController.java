package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Sender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 19.08.2017.
 */
public class TabPaneController implements Initializable {

    User user;
    @FXML private TabPane tabPane;
    // Inject tab content.
    @FXML private Tab profileTab;
    @FXML private Tab rideTab;
    @FXML private Tab settingsTab;
    // Inject controller
    @FXML private ProfileTabController profileTabController;
    @FXML private SettingsTabController settingsTabController;
    @FXML private RideTabController rideTabController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            user= Sender.getSender();
            Sender.setSender(user);
    }
}


