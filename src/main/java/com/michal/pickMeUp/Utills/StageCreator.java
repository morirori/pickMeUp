package com.michal.pickMeUp.Utills;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Michał on 14.08.2017.
 */
public class StageCreator {

   public  void create(String path, String title,int wight, int height) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        Stage registerStage = new Stage();
        registerStage.setTitle(title);
        registerStage.setScene(new Scene(root, wight, height));
        registerStage.show();
    }


}
