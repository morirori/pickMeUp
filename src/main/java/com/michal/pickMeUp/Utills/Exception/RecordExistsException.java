package com.michal.pickMeUp.Utills.Exception;


/**
 * Created by Michał on 21.08.2017.
 */
public class RecordExistsException extends Exception {
    String message= "You have already joined this ride";

    public String traceback(){
        return message;
    }

}
