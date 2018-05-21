package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.DAO.RandomQuerry;
import com.michal.pickMeUp.Model.DAO.RideDAO;
import com.michal.pickMeUp.Model.DAO.UserRidesDAO;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Alerts;
import com.michal.pickMeUp.Utills.Exception.FinishedRideException;
import com.michal.pickMeUp.Utills.MenuValuesSetter;
import com.michal.pickMeUp.Utills.Sender;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Michał on 21.08.2017.
 */
public class CreateRideController implements Initializable{

    private  ObservableList<MenuItem> yearData = FXCollections.observableArrayList();
    private ObservableList<MenuItem> montthData = FXCollections.observableArrayList();
    private ObservableList<MenuItem> dayData = FXCollections.observableArrayList();
    private ObservableList<MenuItem> hourData = FXCollections.observableArrayList();
    private ObservableList<MenuItem> minuteData = FXCollections.observableArrayList();
    @FXML private  TextField endPointTxtField;
    @FXML private  TextField freeSpacesTxtField;
    @FXML private  TextField costTxtField;
    @FXML private  MenuButton yearMenu;
    @FXML private  MenuButton monthMenu;
    @FXML private  MenuButton dayMenu;
    @FXML private  MenuButton hourMenu;
    @FXML private  MenuButton minuteMenu;
    @FXML private  Button createButton;
    @FXML private TextField startingPointTxtField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MenuValuesSetter setter= new MenuValuesSetter();
        yearData.setAll(setter.setValues(2010,2020));
        montthData.setAll(setter.setValues(1,12));
        dayData.setAll(setter.setValues(1,31));
        hourData.setAll(setter.setValues(0,23));
        minuteData.setAll(setter.setValues(0,59));
        addActionHandler(yearData,yearMenu);
        addActionHandler(montthData,monthMenu);
        addActionHandler(dayData,dayMenu);
        addActionHandler(hourData,hourMenu);
        addActionHandler(minuteData,minuteMenu);
        yearMenu.getItems().addAll(yearData);
        monthMenu.getItems().addAll(montthData);
        dayMenu.getItems().addAll(dayData);
        hourMenu.getItems().addAll(hourData);
        minuteMenu.getItems().addAll(minuteData);

    }

    @FXML
    public void createButtonActionHandler() {
        try {
            String startingPoint = startingPointTxtField.getText();
            String endPoint = endPointTxtField.getText();
            Float cost = Float.parseFloat(costTxtField.getText());
            Integer freeSpaces = Integer.parseInt(freeSpacesTxtField.getText());
            Date date = new Date(Integer.parseInt(yearMenu.getText()) - 1900
                    , Integer.parseInt(monthMenu.getText())
                    , Integer.parseInt(dayMenu.getText())
                    , Integer.parseInt(hourMenu.getText())
                    , Integer.parseInt(minuteMenu.getText()));
            RideDAO newRide = new RideDAO();
            User user = Sender.getSender();
            newRide.createRide(user, startingPoint, endPoint, date, cost, true, freeSpaces);
            RandomQuerry dao = new RandomQuerry();
            String biggestRideID = dao.getValueFromUsers("SELECT RideID from rides ORDER BY RideID desc", "RideID");
            UserRidesDAO insert = new UserRidesDAO();
            Integer incik = Integer.parseInt(biggestRideID);
            insert.insertRecord(user, incik.toString());
            Stage parentStage = (Stage)createButton.getScene().getWindow();
            parentStage.close();
        } catch (FinishedRideException ex) {
            Alerts alert = new Alerts(ex.traceback2(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void cancelButtonActionHandler(){
        Stage parentStage = (Stage)createButton.getScene().getWindow();
        parentStage.close();
    }


    private void addActionHandler(ObservableList<MenuItem> array, MenuButton button){
        array.forEach(menuItem -> menuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (button.getText().equals("Year")) {
                    button.setText(menuItem.getText());
                }
                else if(button.getText().equals("Month")) {
                    button.setText(menuItem.getText());
                }
                else if (button.getText().equals("Day")) {
                    button.setText(menuItem.getText());
                }
                else if (button.getText().equals("Minute")){
                    button.setText(menuItem.getText());
                }
                else if (button.getText().equals("Hour")) {
                    button.setText(menuItem.getText());
                }

            }
        }));
    }



}
