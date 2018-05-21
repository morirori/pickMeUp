package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.DAO.RideDAO;
import com.michal.pickMeUp.Model.DAO.UserDAO;
import com.michal.pickMeUp.Model.DAO.UserRidesDAO;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Alerts;
import com.michal.pickMeUp.Utills.Exception.*;
import com.michal.pickMeUp.Utills.RideWraper;
import com.michal.pickMeUp.Utills.Sender;
import com.michal.pickMeUp.Utills.StageCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Michał on 19.08.2017.
 */
public class RideTabController implements Initializable {

    User user;
    @FXML
    TextField rideIDButton;
    @FXML
    TextField userIDTxt;
    @FXML
    TableColumn<RideWraper, String> rideIDsTable= new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper, String> ownerTable=new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper, String> startTable = new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper,String> endTable= new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper,String> finishedTable =  new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper,Date> startTimeTable=  new TableColumn<RideWraper, Date>();
    @FXML
    TableColumn<RideWraper,Integer> freeSpacesTable= new TableColumn<RideWraper, Integer>();
    @FXML
    TableColumn<RideWraper,Float> costTable=  new TableColumn<RideWraper, Float>();
    @FXML
    TableColumn<RideWraper,String> timeTxtBox=  new TableColumn<RideWraper, String>();
    @FXML
    TableColumn<RideWraper,String> distanceTxtBox=  new TableColumn<RideWraper, String>();



    @FXML TableView<RideWraper> rideTabView;
    ObservableList<RideWraper> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user= Sender.getSender();
        showExistingRides();

    }

    private void showExistingRides(){
        RideDAO newRide = new RideDAO();
        List<RideWraper> rides =  newRide.getRide();
        data.clear();
        data.addAll(rides);
        rideTabView.getColumns().clear();
        finishedTable.setCellValueFactory(new PropertyValueFactory<>("isFinished"));
        rideIDsTable.setCellValueFactory( new PropertyValueFactory<>("rideID"));
        ownerTable.setCellValueFactory( new PropertyValueFactory<>("userID"));
        startTable.setCellValueFactory( new PropertyValueFactory<>("startPoint"));
        endTable.setCellValueFactory( new PropertyValueFactory<>("endPoint"));
        startTimeTable.setCellValueFactory( new PropertyValueFactory<>("start"));
        freeSpacesTable.setCellValueFactory( new PropertyValueFactory<>("freeSpaces"));
        costTable.setCellValueFactory( new PropertyValueFactory<>("cost"));
        timeTxtBox.setCellValueFactory(new PropertyValueFactory<>("time"));
        distanceTxtBox.setCellValueFactory(new PropertyValueFactory<>("distance"));
        rideTabView.setItems(data);
        rideTabView.getColumns().addAll(rideIDsTable
                ,ownerTable
                ,startTable
                ,endTable
                ,finishedTable
                ,startTimeTable
                ,freeSpacesTable
                ,costTable
                ,distanceTxtBox
                ,timeTxtBox);
    }

    public void createRideActionHandler(){
        try {
            StageCreator creator = new StageCreator();
            creator.create("view/createRideWindow.fxml","create",800, 400);
            Sender.setSender(this.user);
            Stage parentStage = (Stage)rideIDButton.getScene().getWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void joinRideActionHandler() {
        try {
            RideDAO joinRide = new RideDAO();
            UserRidesDAO dao = new UserRidesDAO();
            String rideID = rideIDButton.getText();
            joinRide.joinRide(user, rideID);
            showExistingRides();
        } catch (AlreadyStartedException ex) {
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        } catch (FinishedRideException ex) {
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        } catch (NoFreeSpacesException ex) {
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        } catch (WrongIDException ex) {
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        } catch (RuntimeException ex){
            ex.printStackTrace();
        } catch (RecordExistsException e) {
            Alerts alert = new Alerts(e.traceback(), Alert.AlertType.ERROR);
        }
    }

    public void refreshActionHandler(){
        showExistingRides();
    }


    public void inspectUserHandler(){
        try {
            UserDAO userToInspect= new UserDAO();
            User originalUser=Sender.getSender();
            User user =userToInspect.getUser("SELECT * from Users WHERE UserID = " + Integer.parseInt(userIDTxt.getText()));
            Sender.setSender(user);
            StageCreator creator = new StageCreator();
            creator.create("view/inspectUserWindow.fxml","create",400, 250);
            Sender.setSender(originalUser);
        } catch (WrongIDException ex) {
            Alerts alert = new Alerts(ex.traceback(), Alert.AlertType.ERROR);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
