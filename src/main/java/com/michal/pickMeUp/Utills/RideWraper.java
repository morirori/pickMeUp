package com.michal.pickMeUp.Utills;

import com.michal.pickMeUp.Model.Entity.Ride;

import java.util.Date;

/**
 * Created by Michał on 20.08.2017.
 */
public class RideWraper {
    private Ride ride ;
    private String rideID;
    private String userID;
    private String startPoint;
    private String endPoint;
    private String isFinished;
    private Date start;
    private String distance;

    public String getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(String isFinished) {
        this.isFinished = isFinished;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;
    private Integer freeSpaces;
    private Float cost;


    public RideWraper(Ride ride, String rideID, String userID){
        this.ride=ride;
        this.rideID=rideID;
        this.distance=this.ride.getDistace();
        this.time=this.ride.getTime();
        this.userID=userID;
        this.startPoint = this.ride.getStartPoint();
        this.endPoint = this.ride.getEndPoint();
        this.start=this.ride.getStart();
        this.freeSpaces =this.ride.getFreeSpaces();
        this.cost=this.ride.getCost();
        if (ride.getFinished()){
            this.isFinished="Already finished";
        }
        else{
            this.isFinished="Not finished yet";
        }
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
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

    public String getFinished() {
        return isFinished;
    }

    public void setFinished(String finished) {
        isFinished = finished;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Integer getFreeSpaces() {
        return freeSpaces;
    }

    public void setFreeSpaces(Integer freeSpaces) {
        this.freeSpaces = freeSpaces;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public Ride getRirde() {
        return ride;
    }

    public void setRirde(Ride rirde) {
        this.ride = rirde;
    }

    public String getRideID() {
        return rideID;
    }

    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

}
