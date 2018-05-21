package com.michal.pickMeUp.Model.DAO;

import com.michal.pickMeUp.Utills.DBConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Michał on 21.08.2017.
 */
public class RandomQuerry {


    private DBConnectionManager manager;
    private Statement statement;

    public RandomQuerry(){
        manager= new DBConnectionManager();
    }

    public String  getValueFromUsers(String query, String field){
        String toReturn ="";
        try {
            manager.establishConnection();
            Statement statement = manager.connection.createStatement();

            ResultSet rs  =statement.executeQuery(query);
            rs.next();
            toReturn= rs.getString(field);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toReturn;
    }
}
