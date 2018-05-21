package com.michal.pickMeUp.Utills.Exception;


/**
 * Created by Michał on 20.08.2017.
 */
public class FinishedRideException extends Exception {
    String message= "Ride has already finished";
    String massege2= "You cant create fire with old date ";
    public String traceback(){
        return message;
    }
    public String traceback2(){
        return massege2;
    }

}
