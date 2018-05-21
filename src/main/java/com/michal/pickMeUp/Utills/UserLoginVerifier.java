package com.michal.pickMeUp.Utills;


import com.michal.pickMeUp.Utills.Exception.EmptyFieldException;

/**
 * Created by Michał on 15.08.2017.
 */
public class UserLoginVerifier {

    public boolean verify(String email, String password) throws EmptyFieldException {
        if (email.isEmpty() || password.isEmpty()){
            throw new EmptyFieldException();
        }
        else{
            return true;
        }
    }
}
