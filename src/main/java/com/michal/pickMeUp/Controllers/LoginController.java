package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Utills.DBConnectionManager;
import com.michal.pickMeUp.Model.DAO.UserDAO;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Alerts;
import com.michal.pickMeUp.Utills.Exception.EmptyFieldException;
import com.michal.pickMeUp.Utills.Sender;
import com.michal.pickMeUp.Utills.StageCreator;
import com.michal.pickMeUp.Utills.UserLoginVerifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michał on 14.08.2017.
 */
public class LoginController implements Initializable {

    @FXML
    TextField emailTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    Button cancelButton;
    @FXML
    Button loginButton;

    private StageCreator creator = new StageCreator();
    DBConnectionManager manager;

//    @FXML
//    private void initialize(){
//
//    }

    @FXML
    public void loginButtonHandler(){
        try {
            String providedEmail= emailTextField.getText();
            String providedPassword = passwordTextField.getText();
            String querry = "email LIKE \"" + providedEmail + "\" and password LIKE \"" + providedPassword + "\"";
            UserLoginVerifier verifier = new UserLoginVerifier();
            boolean dataCorretnes = verifier.verify(providedEmail,providedPassword);
            manager= new DBConnectionManager();
            manager.establishConnection();
            UserDAO userDAO = new UserDAO();
            userDAO.selectUser(querry);
            Boolean isEmpty = userDAO.selectUser(querry);
            if (dataCorretnes  && !isEmpty ){

                Alerts alert = new Alerts("Succesful login", Alert.AlertType.INFORMATION);
                User user =userDAO.userCreator(querry);
                Sender.setSender(user);
                StageCreator creator = new StageCreator();
                creator.create("view/TabPane.fxml", "Home page",1060,400);
                Stage parentStage = (Stage) loginButton.getScene().getWindow();
                parentStage.close();


            }
            else{
                Alerts alert = new Alerts("No user matching to provided data", Alert.AlertType.INFORMATION);
                emailTextField.setText("");
                passwordTextField.setText("");

            }

        } catch (EmptyFieldException e) {
            Alerts alert = new Alerts(e.traceback(), Alert.AlertType.ERROR);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void cancelButtonHandler(){
        try {
            creator.create("view/mainStage.fxml","Main stage",205, 330);
            Stage parentStage = (Stage)loginButton.getScene().getWindow();
            parentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
