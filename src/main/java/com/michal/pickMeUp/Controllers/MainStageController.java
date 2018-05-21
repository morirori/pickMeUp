package com.michal.pickMeUp.Controllers;
import com.michal.pickMeUp.Utills.StageCreator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainStageController implements Initializable{
    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;

    StageCreator creator;

    public MainStageController(){

    }

//    @FXML
//    void initialize(){
//    }

    @FXML
    void onActionHandler() {
        try {
            creator = new StageCreator();
            creator.create("view/login.fxml", "login",560 ,350);
            Stage parentStage = (Stage)registerButton.getScene().getWindow();
            parentStage.close();
        } catch (IOException e) {

        }
    }

    @FXML
    void onRegisterHandler() {
        try {
            creator = new StageCreator();
            creator.create("view/registration.fxml", "Register",550,700);
            Stage parentStage = (Stage)registerButton.getScene().getWindow();
            parentStage.close();
        } catch (IOException e) {

        }
    }

    @FXML
    void onExitHandler(){
        Platform.exit();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
