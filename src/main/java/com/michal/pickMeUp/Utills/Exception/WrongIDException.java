package com.michal.pickMeUp.Utills.Exception;


/**
 * Created by Michał on 21.08.2017.
 */
public class WrongIDException extends Exception {

    String message= "There is no object matching to provied ID";

    public String traceback(){
        return message;
    }

}
