/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.alephnought.duitriagui;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import com.alephnought.duitriagui.model.Bank;
import com.alephnought.duitriagui.model.GameLogic;
import com.alephnought.duitriagui.model.Player;
import com.alephnought.duitriagui.model.Set;
import com.alephnought.duitriagui.model.Cell;
import com.alephnought.duitriagui.model.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author joney
 */
public class GameboardController implements Initializable {

    @FXML
    private ImageView diceImage1;
    @FXML
    private AnchorPane gameBoardPane;
    @FXML
    private Label NamePlayer1;
    @FXML
    private Label NamePlayer2;
    @FXML
    private Label NamePlayer3;
    @FXML
    private Label NamePlayer4;
    @FXML
    private Pane Level1;
    @FXML
    private Pane Level2;
    @FXML
    private Label BuildHouses;
    @FXML
    private Pane Level3;
    @FXML
    private Label NumberOfHousesToBuild;
    @FXML
    private Slider sliderToBuildHouses;
    @FXML
    private Button deal1;
    @FXML
    private ImageView diceImage2;
    @FXML
    private Circle Player1;
    @FXML
    private Circle Player2;
    @FXML
    private Circle Player3;
    @FXML
    private Circle Player4;
    @FXML
    private Label LandABuyOrNot;
    @FXML
    private Pane level3Rents;
    @FXML
    private Label LandARentEmpty;
    @FXML
    private Label LandARent1House;
    @FXML
    private Label LandARent2Houses;
    @FXML
    private Label LandARent3Houses;
    @FXML
    private Label LandARent4Houses;
    @FXML
    private Label LandBRentEmpty;
    @FXML
    private Label LandBRent1House;
    @FXML
    private Label LandBRent2Houses;
    @FXML
    private Label LandBRent3Houses;
    @FXML
    private Label LandBRent4Houses;
    @FXML
    private Label LandCRentEmpty;
    @FXML
    private Label LandCRent1House;
    @FXML
    private Label LandCRent2Houses;
    @FXML
    private Label LandCRent3Houses;
    @FXML
    private Label LandCRent4Houses;
    @FXML
    private Label LandDRentEmpty;
    @FXML
    private Label LandDRent1House;
    @FXML
    private Label LandDRent2Houses;
    @FXML
    private Label LandDRent3Houses;
    @FXML
    private Label LandDRent4Houses;
    @FXML
    private Label LandERentEmpty;
    @FXML
    private Label LandERent1House;
    @FXML
    private Label LandERent2Houses;
    @FXML
    private Label LandERent3Houses;
    @FXML
    private Label LandERent4Houses;
    @FXML
    private Label LandFRentEmpty;
    @FXML
    private Label LandFRent1House;
    @FXML
    private Label LandFRent2Houses;
    @FXML
    private Label LandFRent3Houses;
    @FXML
    private Label LandFRent4Houses;
    @FXML
    private Label LandGRentEmpty;
    @FXML
    private Label LandGRent1House;
    @FXML
    private Label LandGRent2Houses;
    @FXML
    private Label LandGRent3Houses;
    @FXML
    private Label LandGRent4Houses;
    @FXML
    private Label Land9RentEmpty;
    @FXML
    private Label Land9Rent1House;
    @FXML
    private Label Land9Rent2Houses;
    @FXML
    private Label Land9Rent3Houses;
    @FXML
    private Label Land9Rent4Houses;
    @FXML
    private Label Land16RentEmpty;
    @FXML
    private Label Land16Rent1House;
    @FXML
    private Label Land9;
    @FXML
    private Label Land8;
    @FXML
    private Label Land7;
    @FXML
    private Label Land6;
    @FXML
    private Label Land5;
    @FXML
    private Label Land4;
    @FXML
    private Label Land3;
    @FXML
    private Label Land2;
    @FXML
    private Label Land1;
    @FXML
    private Label Land21;
    @FXML
    private Label Land22;
    @FXML
    private Label Land23;
    @FXML
    private Label Land24;
    @FXML
    private Label Land25;
    @FXML
    private Label Land26;
    @FXML
    private Label Land27;
    @FXML
    private Label Land28;
    @FXML
    private Label Land29;
    @FXML
    private Label Land19;
    @FXML
    private Label Land18;
    @FXML
    private Label Land17;
    @FXML
    private Label Land16;
    @FXML
    private Label Land15;
    @FXML
    private Label Land14;
    @FXML
    private Label Land13;
    @FXML
    private Label Land12;
    @FXML
    private Label Land11;
    @FXML
    private Label Land20;
    @FXML
    private Label Land31;
    @FXML
    private Label Land32;
    @FXML
    private Label Land33;
    @FXML
    private Label Land34;
    @FXML
    private Label Land35;
    @FXML
    private Label Land36;
    @FXML
    private Label Land37;
    @FXML
    private Label Land38;
    @FXML
    private Label Land39;
    @FXML
    private Label Land0;
    @FXML
    private Label Land10;
    @FXML
    private Label Land30;
    @FXML
    private Label Names;
    @FXML
    private Button BuyYes;
    @FXML
    private Button BuyNo;
    @FXML
    private Button BuildNo;
    @FXML
    private Button BuildYes;
    @FXML
    private Label Land16Rent2Houses;
    @FXML
    private Label Land16Rent3Houses;
    @FXML
    private Label Land16Rent4Houses;
    @FXML
    private Label Land18RentEmpty;
    @FXML
    private Label Land18Rent1House;
    @FXML
    private Label Land18Rent2Houses;
    @FXML
    private Label Land18Rent3Houses;
    @FXML
    private Label Land18Rent4Houses;
    @FXML
    private Label Land19RentEmpty;
    @FXML
    private Label Land19Rent1House;
    @FXML
    private Label Land19Rent2Houses;
    @FXML
    private Label Land19Rent3Houses;
    @FXML
    private Label Land19Rent4Houses;
    @FXML
    private Label Land24RentEmpty;
    @FXML
    private Label Land24Rent1House;
    @FXML
    private Label Land24Rent2Houses;
    @FXML
    private Label Land24Rent3Houses;
    @FXML
    private Label Land24Rent4Houses;
    @FXML
    private Label Land29RentEmpty;
    @FXML
    private Label Land29Rent1House;
    @FXML
    private Label Land29Rent2Houses;
    @FXML
    private Label Land29Rent3Houses;
    @FXML
    private Label Land29Rent4Houses;
    @FXML
    private Label Land34RentEmpty;
    @FXML
    private Label Land34Rent1House;
    @FXML
    private Label Land34Rent2Houses;
    @FXML
    private Label Land34Rent3Houses;
    @FXML
    private Label Land34Rent4Houses;
    @FXML
    private Label Land37RentEmpty;
    @FXML
    private Label Land37Rent1House;
    @FXML
    private Label Land37Rent2Houses;
    @FXML
    private Label Land37Rent3Houses;
    @FXML
    private Label Land37Rent4Houses;
    @FXML
    private Label Land39RentEmpty;
    @FXML
    private Label Land39Rent1House;
    @FXML
    private Label Land39Rent2Houses;
    @FXML
    private Label Land39Rent3Houses;
    @FXML
    private Label Land39Rent4Houses;
    @FXML
    private Label HousesCounterLand1;
    @FXML
    private Label HousesCounterLand3;
    @FXML
    private Label HousesCounterLand6;
    @FXML
    private Label HousesCounterLand8;
    @FXML
    private Label HousesCounterLand9;
    @FXML
    private Label HousesCounterLand11;
    @FXML
    private Label HousesCounterLand12;
    @FXML
    private Label HousesCounterLand13;
    @FXML
    private Label HousesCounterLand14;
    @FXML
    private Label HousesCounterLand16;
    @FXML
    private Label HousesCounterLand18;
    @FXML
    private Label HousesCounterLand19;
    @FXML
    private Label HousesCounterLand21;
    @FXML
    private Label HousesCounterLand23;
    @FXML
    private Label HousesCounterLand24;
    @FXML
    private Label HousesCounterLand25;
    @FXML
    private Label HousesCounterLand26;
    @FXML
    private Label HousesCounterLand27;
    @FXML
    private Label HousesCounterLand28;
    @FXML
    private Label HousesCounterLand29;
    @FXML
    private Label HousesCounterLand31;
    @FXML
    private Label HousesCounterLand32;
    @FXML
    private Label HousesCounterLand34;
    @FXML
    private Label HousesCounterLand37;
    @FXML
    private Label HousesCounterLand39;
    @FXML
    private Circle playerColorCircle;
    @FXML
    private Label playerNameLbl;
    @FXML
    private Label playerBalanceLbl;
    @FXML
    private Label boardOutputLabel;
    @FXML
    private Button RollDiceBtn;
    @FXML
    private ListView chooseSetList;
    @FXML
    private ListView choosePropertyList;

    private GameLogic gameLogic;

    public static int userInput;


    public void setChooseSetList(Set[] sets) {
        String[] setNames = new String[sets.length];
        for(int i = 0; i < sets.length; i++) {
            setNames[i] = sets[i].getName();
        }
        chooseSetList.getItems().clear();
        chooseSetList.getItems().addAll(setNames);
    }

    public void setChoosePropertyList(Cell[] cells) {
        String[] cellNames = new String[cells.length];
        for(int i = 0; i < cells.length; i++) {
            cellNames[i] = cells[i].getName();
        }
        choosePropertyList.getItems().clear();
        choosePropertyList.getItems().addAll(cellNames);
    }

    public void setPlayerNameLbl(String text) {
        playerNameLbl.setText(text);
    }

    public void setPlayerBalanceLbl(String text) {
        playerBalanceLbl.setText(text);
    }

    public void setPlayerColorCircle(Color color){
        playerColorCircle.setFill(color);
    }
    public void setDiceImage(int diceNumber, int diceValue) {
        String imagePath = String.format("/dice%d.png", diceValue);
        URL imageURL = getClass().getResource(imagePath);

        if (imageURL != null) {
            javafx.scene.image.Image image = new javafx.scene.image.Image(imageURL.toExternalForm());
            if (diceNumber == 1) {
                diceImage1.setImage(image);
            } else if (diceNumber == 2){
                diceImage2.setImage(image);
            }
        } else {
            // Handle the case where the image cannot be loaded
//            System.out.println("Resource directory: " + getClass().getResource("/"));
            System.out.println("Failed to load image: " + imagePath);
        }
    }

    //show a dialog with yes or no buttons, returns true or false depending on button pressed.
    public static boolean showChoiceDialog(String choice) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choice Dialog");
        alert.setHeaderText(choice);
        alert.setContentText("Choose your option.");

        // Create "Yes" and "No" buttons
        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        // Set buttons
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Show and wait for the user's choice
        Optional<ButtonType> result = alert.showAndWait();

        // Handle the user's choice
        if (result.isPresent() && result.get() == buttonTypeYes) {
            return true;
            // Add your code for "Yes" option
        } else {
            return false;
            // Add your code for "No" option or do nothing
        }
    }

    public static void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //set the text of the board output label
    public void setBoardOutput(String text){
        boardOutputLabel.setText(text);
        boardOutputLabel.setTextAlignment(TextAlignment.CENTER);
    }
    public void onRollDiceBtnClicked(){
        Player player = GameLogic.currentPlayer;
        if (player.getCanMove()) {
            gameLogic.rollAndGetAction(player);
        } else {
            gameLogic.getAction(player);
        }
        gameLogic.nextTurn();
    }

    //show a dialog with a list of items, returns the selected item
    public static <T> T showListDialog(T[] itemList) {
        Dialog<T> dialog = new Dialog<>();
        dialog.setTitle("List Dialog");
        dialog.setHeaderText("Select an item:");

        // Set the button types
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Create a ListView to display the list of items
        ListView<T> listView = new ListView<>();
        listView.getItems().addAll(itemList);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Set the content of the dialog
        VBox vbox = new VBox();
        vbox.getChildren().add(listView);
        dialog.getDialogPane().setContent(vbox);

        // Convert the result to the selected item when the OK button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return listView.getSelectionModel().getSelectedItem();
            }
            return null;
        });

        // Show the dialog and wait for the user's response
        return dialog.showAndWait().orElse(null);
    }


    public void onBuyHouseBtnClicked(){
        Bank.buyHouse(GameLogic.currentPlayer);
    }
    public void onSellPropertyBtnClicked(){
        Bank.sellProperty(GameLogic.currentPlayer);
    }
    public void onSellHouseBtnClicked(){
        Bank.sellHouse(GameLogic.currentPlayer);
    }

    public void onForfeitBtnClicked(){
        System.out.println("Player forfeited");
        GameLogic.currentPlayer.forfeit();
        gameLogic.nextTurn();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameLogic = new GameLogic(this);
        gameLogic.sortPlayers();
        gameLogic.nextTurn();
        int[] specialCells = {0, 2, 4, 7, 10, 12, 19, 22, 24, 32, 35, 37, 40}; //fix
        for(int i = 0; i < Board.cells.length; i++){
            if(!contains(specialCells, i)){
                System.out.println(Board.cells[i].getName());
                Board.cells[i].createOwnerColoredCircle();
                gameBoardPane.getChildren().add(Board.cells[i].getOwnerColoredCircle());
            }
        }
        for (Player player : GameLogic.players){
            player.createCircle();
            gameBoardPane.getChildren().add(player.getCircle());
            player.updateBoardPosition();
        }
    }
    public boolean contains (int[] array, int value){
        for(int i = 0; i < array.length; i++){
            if(array[i] == value){
                return true;
            }
        }
        return false;
    }
}
