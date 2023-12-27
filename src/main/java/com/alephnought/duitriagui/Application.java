package com.alephnought.duitriagui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("StartScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 485, 294);

        // Get the controller from the FXMLLoader
        StartScreenController controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage); // Set the primary stage for InitializePlayers

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}