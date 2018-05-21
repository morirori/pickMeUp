package com.michal.pickMeUp.Utills.Exception;

/**
 * Created by Michał on 15.08.2017.
 */
public class EmptyFieldException extends Exception {
    String message= "Fields cannot be empty";

    public String traceback(){
        System.out.print(message);
        return message;
    }
}
