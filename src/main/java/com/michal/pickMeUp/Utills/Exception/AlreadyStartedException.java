package com.michal.pickMeUp.Utills.Exception;
/**
 * Created by Michał on 20.08.2017.
 */
public class AlreadyStartedException extends Exception {
    String message= "Ride has aleardy started";

    public String traceback(){
        return message;
    }
}
