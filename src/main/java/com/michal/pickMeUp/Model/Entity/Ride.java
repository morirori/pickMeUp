package com.michal.pickMeUp.Model.Entity;

import com.google.maps.errors.ApiException;
import com.michal.pickMeUp.Utills.GoogleConnector;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Michał on 19.08.2017.
 */
public class Ride {


    private Integer id;
    private String startPoint;
    private String endPoint;

    private Boolean isFinished;
    private Date start;
    private Integer freeSpaces;
    private Float cost;


    private String distace;
    private String time;
    private HashMap<String, User> rideParticipants = new HashMap<>();

    public Ride() {
    }

    public Ride(String startPoint, String endPoint, Boolean isFinished, Date start, Integer freeSpaces, Float cost,String distance, String time) {

        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.isFinished = isFinished;
        this.start = start;
        this.freeSpaces = freeSpaces;
        this.cost = cost;
        this.distace=distance;
        this.time=time;
        //this.rideParticipants = rideParticipants;
    }

    public Ride(String startPoint, String endPoint, Boolean isFinished, Date date, Integer freeSpaces, Float cost) {

        try {
            this.startPoint=startPoint;
            this.endPoint=endPoint;
            this.isFinished = isFinished;
            this.start = date;
            this.freeSpaces = freeSpaces;
            this.cost = cost;
            GoogleConnector connector= new GoogleConnector();
            HashMap<String,String> outputFromGoogle=connector.getElements(startPoint,endPoint);
            this.distace=outputFromGoogle.get("distance");
            this.time=outputFromGoogle.get("time");


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    public Boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Boolean getFinished(){return this.isFinished;}

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getFreeSpaces() {
        return freeSpaces;
    }

    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
    }

    public void setFreeSpaces(){
        int counter=0;
        for (HashMap.Entry<String,User> entry : this.rideParticipants.entrySet()){
            if(entry.getValue()==null){
                counter=counter+1;
            }
        }
        this.freeSpaces=counter;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void addParicipante(String role, User user) {
        if (role == "owner") {
            this.rideParticipants.replace(role, user);

        } else if (role == "particpant") {
            for (HashMap.Entry<String, User> entry : this.rideParticipants.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();

                if (key != "owner" && value ==null) {
                    this.rideParticipants.replace(role, user);

                }
            }
        }

    }

    public void showRide(){
        for (HashMap.Entry<String,User> entry : this.rideParticipants.entrySet()){
            if (entry.getValue()!=null) {

                rideParticipants.get(entry.getKey()).show();

            }
        }
        System.out.print("Start Point - "+ this.getStartPoint());
        System.out.print("Enc point - "+ this.getEndPoint());
        System.out.print("Start time" + " - " + getStart().toString());
        System.out.print("Number of participants" + " - " + getFreeSpaces());
        System.out.print("finished" + " - " + getFinished());
        System.out.print("oost" + " - " + getCost());
        System.out.print("distance" + " - " + getDistace());
        System.out.print("time" + " - " + getTime());
    }

    public String getDistace() {
        return distace;
    }

    public void setDistace(String distace) {
        this.distace = distace;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
