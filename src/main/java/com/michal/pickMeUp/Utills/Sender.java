package com.michal.pickMeUp.Utills;

import com.michal.pickMeUp.Model.Entity.User;

/**
 * Created by Michał on 15.08.2017.
 */
public class Sender {
    public static User user;

    public static User getSender(){
        return  user;
    }

    public static void setSender(User u){
        user=u;
    }
}
