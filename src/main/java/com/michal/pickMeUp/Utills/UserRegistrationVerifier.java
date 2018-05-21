package com.michal.pickMeUp.Utills;


import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.Exception.EmptyFieldException;
import com.michal.pickMeUp.Utills.Exception.WrongPasswordException;

import java.util.HashMap;

/**
 * Created by Michał on 14.08.2017.
 */
public class UserRegistrationVerifier {

    public boolean verify(User user) throws EmptyFieldException,WrongPasswordException {
        HashMap<String, String> map = user.getMap();
        boolean toReturn=false;
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key == "password" ) {
                if (value.length() > 10 && !(value.isEmpty())) {
                    toReturn = true;
                }
                else{
                    throw new WrongPasswordException();
                }
            }
            else if (value.isEmpty()){
                throw new EmptyFieldException();
            }
            else {
                toReturn = true;
            }
        }
        return toReturn;
    }
}
