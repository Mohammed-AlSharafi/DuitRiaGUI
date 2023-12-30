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
    private ImageView diceImage2;
    @FXML
    private Label Houses2;
    @FXML
    private Label Houses4;
    @FXML
    private Label Houses7;
    @FXML
    private Label Houses9;
    @FXML
    private Label Houses10;
    @FXML
    private Label Houses12;
    @FXML
    private Label Houses14;
    @FXML
    private Label Houses15;
    @FXML
    private Label Houses17;
    @FXML
    private Label Houses19;
    @FXML
    private Label Houses20;
    @FXML
    private Label Houses22;
    @FXML
    private Label Houses24;
    @FXML
    private Label Houses25;
    @FXML
    private Label Houses27;
    @FXML
    private Label Houses29;
    @FXML
    private Label Houses30;
    @FXML
    private Label Houses32;
    @FXML
    private Label Houses33;
    @FXML
    private Label Houses35;
    @FXML
    private Label Houses38;
    @FXML
    private Label Houses40;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gameLogic = new GameLogic(this);
        gameLogic.sortPlayers();
        for(Player player : GameLogic.players){
            setBoardOutput(boardOutputLabel.getText() + "\n" + player.getName() + " rolled a " + player.getOrderRoll());
        }
        gameLogic.nextTurn();
        setBoardOutput(boardOutputLabel.getText()+ "\n" + GameLogic.currentPlayer.getName() + " starts first!");
        int[] specialCells = {0, 2, 4, 7, 10, 17, 20, 22, 30, 33, 36, 38};
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

    //show a dialog with a list of items, returns the selected item
    public static <T> T showListDialog(T[] itemList, String title, String headerText) {
        Dialog<T> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(headerText);

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
        } else {
            return false;
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
        Bank.bankruptPlayer(GameLogic.currentPlayer, null);
        gameLogic.nextTurn();
    }

    public boolean contains (int[] array, int value){
        for(int i = 0; i < array.length; i++){
            if(array[i] == value){
                return true;
            }
        }
        return false;
    }

    public void setHouseCounterLbl(int cellNumber, String string){
        Label label = getHouseCounterLblByIndex(cellNumber);
        label.setText(string);
    }
    @FXML
    public Label getHouseCounterLblByIndex(int number) {
        try {
            // Use reflection to get the label by name
            String labelName = "Houses" + number;
            return (Label) getClass().getDeclaredField(labelName).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Handle the case where the label with the given number doesn't exist
        }
    }
}
