package com.michal.pickMeUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

        @Override
        public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainStage.fxml"));
            primaryStage.setTitle("Main stage");
            primaryStage.setScene(new Scene(root, 205, 330));
            primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
}
