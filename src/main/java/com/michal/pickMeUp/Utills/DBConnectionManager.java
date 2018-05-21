package com.michal.pickMeUp.Utills;


import com.michal.pickMeUp.Model.Entity.User;

import java.sql.*;

/**
 * Created by Michał on 14.08.2017.
 */
public class DBConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/projekt";
    private String username = "root";
    private String password = "76476912";
    public Connection connection;
    public void establishConnection() {
        try {
            //Class.forName(Driver).newInstance();
            connection= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

