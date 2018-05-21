package com.michal.pickMeUp.Model.DAO;

import com.michal.pickMeUp.Model.Entity.Ride;
import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.DBConnectionManager;
import com.michal.pickMeUp.Utills.DateAndTimeParser;
import com.michal.pickMeUp.Utills.Exception.*;
import com.michal.pickMeUp.Utills.RideWraper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Michał on 19.08.2017.
 */
@SuppressWarnings("SqlDialectInspection")
public class RideDAO {
    private DBConnectionManager manager;
    private Statement statement;
    private Ride ride;
    public RideDAO() {
        manager = new DBConnectionManager();

    }
    public void createRide(User owner, String startPoint, String endPoint, Date start, float cost, Boolean isFinished, int freeSpaces) throws FinishedRideException {
        try {
            HashMap<String, User> participantsMap = new HashMap<>();
            owner.show();
            participantsMap.put("owner", owner);
            this.ride = new Ride(startPoint,endPoint,isFinished,start,freeSpaces,cost);
            String estimatedTime= ride.getTime();
            String distance=ride.getDistace();
            this.ride.addParicipante("owner", owner);
            manager.establishConnection();
            statement = manager.connection.createStatement();
            String query="SELECT UserID FROM users WHERE email LIKE \"%" + owner.getEmail() + "%\";";
            ResultSet rs =statement.executeQuery(query);
            rs.next();
            String userID =rs.getString("UserID");
            java.sql.Date startToInsert= new java.sql.Date(start.getTime());
            java.sql.Time time = new java.sql.Time(start.getTime());
            startToInsert.setTime(start.getTime());
            Date currentDate = Calendar.getInstance().getTime();
            if (currentDate.after(startToInsert)) {throw new FinishedRideException();}
            else {

                String values = "(" + new Integer(userID) + ",\"" + startPoint + "\",\"" + endPoint + "\",\"" + startToInsert + "\",\"" + time + "\"," + freeSpaces + "," + cost + "," + isFinished + ",\"" + distance + "\",\"" + estimatedTime + "\");";
                query = "INSERT INTO `rides` (`UserID`,`startPoint`,`endPoint`,`startDate`,`startTime`, `freeSpaces`, `cost`, `isFinished`, `distance`,`esitmatedTime`) VALUES " + values;
                statement = manager.connection.createStatement();
                statement.executeUpdate(query);
                manager.closeConnection();
            }
        }


        catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void joinRide(User user,String id ) throws AlreadyStartedException, NoFreeSpacesException, FinishedRideException, WrongIDException, RecordExistsException {
        try {

            manager.establishConnection();
            statement=manager.connection.createStatement();

            String query = "SELECT * FROM rides WHERE RideID ="+ Integer.parseInt(id); //selecting rideID
            ResultSet rs=statement.executeQuery(query);
            if (!rs.next()) {
                throw  new WrongIDException();
            }
            else{
                Integer freeSpaces = Integer.parseInt(rs.getString("freeSpaces"));
                Date startDate = new DateAndTimeParser().parse(rs.getString("startDate"), rs.getString("startTime"));
                Boolean isFinished = Boolean.parseBoolean(rs.getString("isFinished"));
                Integer rideID = Integer.parseInt(id);
                Date currentDate = Calendar.getInstance().getTime();
                query = "SELECT UserID FROM users WHERE email LIKE \"%" + user.getEmail() + "%\";"; //wybieram IDmojegoUsera
                rs = statement.executeQuery(query);
                rs.first();
                Integer participantID = Integer.parseInt(rs.getString("UserID"));
                query = "SELECT * FROM userrides WHERE UserID = " + participantID + " AND  RideID = " + id+" ;";
                rs = statement.executeQuery(query);
                Boolean recordExits = rs.next();


                if (freeSpaces < 1 || currentDate.after(startDate) || isFinished || recordExits) {
                    if (freeSpaces < 1) {throw new NoFreeSpacesException();}
                    if (currentDate.after(startDate)) {throw new AlreadyStartedException();}
                    if (isFinished) {throw new FinishedRideException();}
                    if (recordExits){throw new RecordExistsException();
                    }
                }
                else {
                    query = "UPDATE rides SET freeSpaces=freeSpaces-1 WHERE RideID = " + Integer.parseInt(id);
                    statement.executeUpdate(query);
                    query = "SELECT * FROM userrides where Particiapnt is null AND RideID = " + rideID +";";
                    rs = statement.executeQuery(query);
                    rs.first();

                    user.show();
                    Integer userID =  Integer.parseInt( rs.getString("UserID"));
                    statement.execute(query);
                    query = "INSERT INTO `userrides`(`RideId`,`UserID`,`Particiapnt`) VALUES  (" + rideID + ", " + userID + "," + participantID+");";
                    statement.execute(query);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    public List<RideWraper> getRide(){
        List<RideWraper> rides =new ArrayList<>();
        DateAndTimeParser parser = new DateAndTimeParser();
        try {
            manager.establishConnection();
            statement = manager.connection.createStatement();
            String query= "SELECT * FROM rides ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String rideID =rs.getString("RideID");
                String userID =rs.getString("UserID");
                String startDate =rs.getString("startDate");
                String startTime =rs.getString("startTime");
                String distance = rs.getString("distance");
                String estimatedTime=rs.getString("esitmatedTime");
                Date date =parser.parse(startDate,startTime);
                Integer freeSpaces =Integer.parseInt(rs.getString("freeSpaces"));
                Float cost =Float.parseFloat(rs.getString("cost"));
                Boolean isFinished =Boolean.parseBoolean(rs.getString("isFinished"));
                String startPoint =rs.getString("startPoint");
                String endPoint =rs.getString("endPoint");
                Ride tempRide = new Ride(startPoint,endPoint,isFinished,date,freeSpaces,cost,distance,estimatedTime);
                RideWraper rideWraper=new RideWraper(tempRide,rideID,userID);
                rides.add(rideWraper);

            }
            manager.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rides;

    }



}
