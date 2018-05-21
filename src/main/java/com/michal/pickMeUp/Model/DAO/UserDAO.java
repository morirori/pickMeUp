package com.michal.pickMeUp.Model.DAO;

import com.michal.pickMeUp.Model.Entity.User;
import com.michal.pickMeUp.Utills.DBConnectionManager;
import com.michal.pickMeUp.Utills.Exception.WrongIDException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by Michał on 14.08.2017.
 */
public class UserDAO {
    DBConnectionManager manager;
    Statement  statement;
    public UserDAO() {
        manager = new DBConnectionManager();
    }

    public void insertUser(User user){
        try {

            HashMap<String,String> map=user.getMap();
            String columns ="";
            String values ="";
            int mapSize=map.size();
            int counter=0;
            for (HashMap.Entry<String, String> entry : map.entrySet()){
                if (counter == mapSize-1) {
                    columns += " `" + entry.getKey() + "`) ";
                    values += " \"" + entry.getValue() + "\") ";
                }
                else {
                    columns += " `" + entry.getKey() + "`, ";
                    values += " \"" + entry.getValue() + "\", ";
                }
                counter++;
            }
            manager.establishConnection();
            String query = "INSERT INTO `users` ( " + columns  + "VALUES (" + values +";";
            statement = manager.connection.createStatement();
            statement.executeUpdate(query);
            manager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String query) throws WrongIDException, SQLException {
            boolean isEmpty=true;
            User user;
            manager.establishConnection();
            statement = manager.connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            if (!result.next()){
                throw new WrongIDException();
            }
            else {

                user = new User(result.getString("name")
                        , result.getString("lastName")
                        , result.getString("password")
                        , result.getString("phone")
                        , result.getString("car")
                        , result.getString("email")
                        , result.getString("adres")
                        ,Integer.parseInt(result.getString("UserID"))
                );
                manager.closeConnection();
            }
        return  user;

    }



    public boolean selectUser(String whereExpression ){
        boolean isEmpty=true;
        try {
            manager.establishConnection();
            String query = "SELECT * FROM  users  WHERE " + whereExpression + " ;";
            statement = manager.connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            isEmpty = result.first();
            manager.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  !isEmpty;
    }

    public User userCreator(String expression){
        try {
            HashMap<String,String > values = new HashMap<>();
            manager.establishConnection();
            String query = "SELECT * FROM  users  WHERE " + expression + " ;";
            statement = manager.connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();

            values.put("name",result.getString("name"));
            values.put("lastName",result.getString("lastName"));
            values.put("password",result.getString("password"));
            values.put("phone",result.getString("phone"));
            values.put("car",result.getString("car"));
            values.put("email",result.getString("email"));
            values.put("adres",result.getString("adres"));
            User toReturn = new User(values);
            manager.closeConnection();
            return  toReturn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

