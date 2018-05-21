package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Sender;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 21.08.2017.
 */
public class InspectUserWindowController implements Initializable{
    @FXML private TextField nameTxt;
    @FXML private TextField phoneTxt;
    @FXML private TextField carTxt;

    public void exitButtonActionHandler(){
        Stage parentStage = (Stage)phoneTxt.getScene().getWindow();
        parentStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user= Sender.getSender();
        nameTxt.setText(user.getName());
        phoneTxt.setText(user.getPhone());
        carTxt.setText(user.getCar());
    }

}
