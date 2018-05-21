package com.michal.pickMeUp.Utills;

import javafx.scene.control.MenuItem;

import java.util.ArrayList;

/**
 * Created by Michał on 21.08.2017.
 */
public class MenuValuesSetter {
    private ArrayList<MenuItem> valuesList ;

    public ArrayList<MenuItem> setValues(Integer x_param, Integer y){
        valuesList = new ArrayList<>();
        for (Integer x=x_param  ; x<=y; x++ ){

            valuesList.add( new MenuItem(x.toString()));
        }

        return valuesList;
    }

}
