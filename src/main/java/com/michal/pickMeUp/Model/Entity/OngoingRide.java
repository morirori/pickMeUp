package com.michal.pickMeUp.Model.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Michał on 21.08.2017.
 */
public class OngoingRide {

    private String distance;
    private String time;
    private Ride ride ;
    private String name;
    private String startPoint;
    private String endPoint;
    private Date date;
    private Float cost;
    private String phone;
    private ArrayList<User> passangerList = new ArrayList<>();

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

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public OngoingRide(String name, String startPoint, String endPoint, Date date, Float cost, String phone, String distance, String time) {
        this.name = name;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.date = date;
        this.cost = cost;
        this.phone = phone;
        this.time = time;
        this.distance = distance;
    }




}
