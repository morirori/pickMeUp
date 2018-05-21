package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.DAO.UserDAO;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Alerts;
import com.michal.pickMeUp.Utills.Exception.EmptyFieldException;
import com.michal.pickMeUp.Utills.Exception.WrongPasswordException;
import com.michal.pickMeUp.Utills.Sender;
import com.michal.pickMeUp.Utills.StageCreator;
import com.michal.pickMeUp.Utills.UserRegistrationVerifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Created by Michał on 14.08.2017.
 */
public class RegistrationController implements Initializable{

    @FXML
    TextField nameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField phoneField;
    @FXML
    TextField carField;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passField;

    @FXML
    TextField adresField;
    StageCreator creator = new StageCreator();
    private HashMap<String, Object> fields = new HashMap<>();
    private HashMap<String, String> fieldValues = new HashMap<>();

//    @FXML
//    void initialize() {
//    }

    @FXML
    void signInButtonHandler() {
        try {
            Alerts alert;
            fieldValues.put("name", nameField.getText());
            fieldValues.put("lastName", lastNameField.getText());
            fieldValues.put("password", passField.getText());
            fieldValues.put("phone", phoneField.getText());
            fieldValues.put("car", carField.getText());
            fieldValues.put("email", emailField.getText());
            fieldValues.put("adres", adresField.getText());
            UserDAO dm = new UserDAO();
            User user = new User(fieldValues);
            UserRegistrationVerifier verifier = new UserRegistrationVerifier();
            String whereClouse = "email LIKE \"" + user.getEmail() + "\" or phone LIKE \"" + user.getPhone() + "\"";
            boolean isEmpty = dm.selectUser(whereClouse);


            if (verifier.verify(user) == true && isEmpty == true) {

                dm.insertUser(user);
                Sender.setSender(user);
                alert = new Alerts("Congratulation you have succesfully created your account", Alert.AlertType.INFORMATION);
                StageCreator creator = new StageCreator();
                creator.create("view/TabPane.fxml", "Home page",900,400);
                Stage parentStage = (Stage) emailField.getScene().getWindow();
                parentStage.close();

            } else {
                alert = new Alerts("User with provided phone or email already exists", Alert.AlertType.INFORMATION);
                clearFieldsValuesMap();

            }

        } catch (IOException ex) {
            ex.printStackTrace();

        } catch (WrongPasswordException ex){
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);

        } catch (EmptyFieldException ex){
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void backButtonHandler(){
        try {
            StageCreator creator = new StageCreator();
            creator.create("view/MainStage.fxml", "Main stage",205, 330);
            Stage parentStage = (Stage) phoneField.getScene().getWindow();
            parentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void clearFieldsValuesMap() {
        emailField.setText(" ");
        passField.setText("");
        phoneField.setText(" ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
