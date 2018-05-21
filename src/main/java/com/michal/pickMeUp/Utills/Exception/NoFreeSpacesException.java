package com.michal.pickMeUp.Utills.Exception;

/**
 * Created by Michał on 20.08.2017.
 */
public class NoFreeSpacesException  extends Exception{
    String message= "There is no free spaces in this ride";

    public String traceback(){
        return message;
    }

}
