package com.michal.pickMeUp.Controllers;

import com.michal.pickMeUp.Model.DAO.UserRidesDAO;
import com.michal.pickMeUp.Model.Entity.OngoingRide;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Sender;
import com.michal.pickMeUp.Utills.StageCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Michał on 19.08.2017.
 */
public class ProfileTabController implements Initializable {
    User user;

    @FXML private TextField nameField;

    @FXML private TextField lastNameField;

    @FXML private TextField phoneNumberField;

    @FXML private Button refreshBtn;

    @FXML private TextField carField;

    @FXML TableColumn<OngoingRide, String> nameTab = new TableColumn<OngoingRide, String>();

    @FXML TableColumn<OngoingRide, String> startTab=new TableColumn<OngoingRide, String>();

    @FXML TableColumn<OngoingRide, String> endTab = new TableColumn<OngoingRide, String>();

    @FXML TableColumn<OngoingRide,Date> dateTab= new TableColumn<OngoingRide, Date>();

    @FXML TableColumn<OngoingRide,Float> costTab =  new TableColumn<OngoingRide, Float>();

    @FXML TableColumn<OngoingRide,String> distanceTab=  new TableColumn<OngoingRide, String>();

    @FXML TableColumn<OngoingRide,String> timeTab=  new TableColumn<OngoingRide, String>();

    @FXML TableColumn<OngoingRide,String> phoneTab=  new TableColumn<OngoingRide, String>();


    @FXML public TableView tabView;
    private  ObservableList<OngoingRide> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = Sender.getSender();
        nameField.setText(user.getName());
        lastNameField.setText(user.getLastName());
        phoneNumberField.setText(user.getPhone());
        carField.setText(user.getCar());
        showOngoingRides();
        endTab.setText("End Point");
    }

    @FXML
    public void logOutActionHandler(){
        try {
            Sender.setSender(null);
            StageCreator creator = new StageCreator();
            creator.create("view/MainStage.fxml", "Main stage",206, 330);
            Stage parentStage = (Stage) nameField.getScene().getWindow();
            parentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showOngoingRides(){
        UserRidesDAO dao = new UserRidesDAO();
        List<OngoingRide> ongoingRides = dao.getRecord(user);
        data.clear();
        data.addAll(ongoingRides);
        tabView.getColumns().clear();
        nameTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("name"));
        startTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("startPoint"));
        endTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("endPoint"));
        dateTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, Date>("date"));
        costTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, Float>("cost"));
        phoneTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("phone"));
        distanceTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("distance"));
        timeTab.setCellValueFactory(new PropertyValueFactory<OngoingRide, String>("time"));
        tabView.setItems(data);
        tabView.getColumns().addAll(nameTab
                            ,dateTab
                            ,costTab
                            ,phoneTab
                            ,startTab
                            ,distanceTab
                            ,timeTab
                            ,endTab);

    }

    @FXML
    public void refreshActionHandler() {
        showOngoingRides();
    }
}


