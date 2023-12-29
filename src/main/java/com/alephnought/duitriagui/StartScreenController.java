package com.alephnought.duitriagui;

import com.alephnought.duitriagui.model.GameLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;

public class StartScreenController {

    @FXML
    private Stage primaryStage;
    @FXML
    private Label welcomeText;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton radio2Players;
    @FXML
    private RadioButton radio3Players;
    @FXML
    private RadioButton radio4Players;
    @FXML
    private GridPane playerRows;
    @FXML
    private HBox player1Row;
    @FXML
    private HBox player2Row;
    @FXML
    private HBox player3Row;
    @FXML
    private HBox player4Row;
    @FXML
    private TextField Name1;
    @FXML
    private TextField Name2;
    @FXML
    private TextField Name3;
    @FXML
    private TextField Name4;

    @FXML
    private void initialize() {
        playerRows.setVisible(false);

    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void onStartButtonClicked() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            int numberOfPlayers = 0;

            if (selectedRadioButton == radio2Players) {
                numberOfPlayers = 2;
            } else if (selectedRadioButton == radio3Players) {
                numberOfPlayers = 3;
            } else if (selectedRadioButton == radio4Players) {
                numberOfPlayers = 4;
            }
            String[] playerNames = new String[numberOfPlayers];
            for (int i = 0; i < playerNames.length; i++) {
                TextField currentTextField = getTextFieldByIndex(i+1);
                if (currentTextField != null && currentTextField.isVisible() && currentTextField.isEditable()) {
                    String playerName = currentTextField.getText();
                    playerNames[i] = playerName;
                }
            }
        GameLogic.initializePlayers(playerNames);
        try {
            // Load the FXML file for the second window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Gameboard.fxml"));
            Parent root = loader.load();

            // Create the stage for the second window
            Stage secondStage = new Stage();
            secondStage.setTitle("DuitRia");
            secondStage.setScene(new Scene(root));

            primaryStage.close();

            // Show the second window
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }}
    @FXML
    private void handleRadioButtonAction() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            playerRows.setVisible(true);
            if (selectedRadioButton == radio2Players){
                player3Row.setVisible(false);
                player4Row.setVisible(false);
            } else if (selectedRadioButton == radio3Players) {
                player3Row.setVisible(true);
                player4Row.setVisible(false);
            }else if (selectedRadioButton == radio4Players) {
                player3Row.setVisible(true);
                player4Row.setVisible(true);
            }
        }
    }
@FXML
    private TextField getTextFieldByIndex(int index) {
        try {
            // Use reflection to get the TextField dynamically
            String textFieldName = "Name" + index;
            Field field = StartScreenController.class.getDeclaredField(textFieldName);
            field.setAccessible(true);
            return (TextField) field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}