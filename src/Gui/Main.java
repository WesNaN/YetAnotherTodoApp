package Gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Placeholder for Mainmethod for gui
 */
public class Main extends Application{

    public static void main(String args[]) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage)
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Startscreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 275));
        primaryStage.show();
    }
}
