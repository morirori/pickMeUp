package com.michal.pickMeUp.Utills.Exception;

/**
 * Created by Michał on 15.08.2017.
 */
public class WrongPasswordException extends  Exception {
    String message = "Passowrd must contain more than 10 characters and cannot be empty";

    public String traceback(){
        return message;
    }
}
