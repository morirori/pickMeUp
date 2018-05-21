package com.michal.pickMeUp.Model.DAO;

import com.michal.pickMeUp.Model.Entity.OngoingRide;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.DBConnectionManager;
import com.michal.pickMeUp.Utills.DateAndTimeParser;
import com.michal.pickMeUp.Utills.RideWraper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Michał on 21.08.2017.
 */
public class UserRidesDAO {

    DBConnectionManager manager;
    Statement statement;

    public UserRidesDAO() {
        manager = new DBConnectionManager();
    }


    public void insertRecord(User user, String rideID) {
        try {
            manager.establishConnection();
            statement = manager.connection.createStatement();
            UserDAO dao = new UserDAO();
            String querry = "SELECT UserID FROM users WHERE email LIKE  \"%" + user.getEmail() + "%\";";
            ResultSet rs = statement.executeQuery(querry);
            rs.next();
            querry = "INSERT INTO `userrides` (`RideID`,`UserID`) VALUES (" + Integer.parseInt(rideID) + "," + Integer.parseInt(rs.getString("UserID")) + ");";
            statement.execute(querry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OngoingRide> getRecord(User user) {
        List<OngoingRide> ongoingRides= new ArrayList<>();
        try {
            manager.establishConnection();
            statement = manager.connection.createStatement();
            DateAndTimeParser parser = new DateAndTimeParser();
            java.util.Date currentDate;
            currentDate= Calendar.getInstance().getTime();
            String query = " SELECT UserID FROM users WHERE email LIKE \"%"+ user.getEmail()+"%\";";
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            String userID = rs.getString("UserID");
            List<RideWraper> rides = new ArrayList<>();
            query="SELECT users.name, rides.startPoint, rides.endPoint, rides.distance, rides.esitmatedTime , rides.startDate, rides.startTime, rides.cost, users.phone FROM rides join userrides using(RideID,UserID) natural join  users \n" +
                    "WHERE rides.startDate > \" " +currentDate +"\" AND (userrides.UserID = " + userID  + " OR   userrides.Particiapnt = " + userID + ");";
            rs =statement.executeQuery(query);

            while (rs.next()){
                java.util.Date date =parser.parse(rs.getString("startDate"),rs.getString("startTime"));
                OngoingRide ride = new OngoingRide(rs.getString("name")
                        ,rs.getString("startPoint")
                        ,rs.getString("endPoint")
                        ,date
                        ,Float.parseFloat(rs.getString("cost"))
                        ,rs.getString("phone")
                        ,rs.getString("distance")
                        ,rs.getString("esitmatedTime"));
                ongoingRides.add(ride);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    return ongoingRides;
    }
}

