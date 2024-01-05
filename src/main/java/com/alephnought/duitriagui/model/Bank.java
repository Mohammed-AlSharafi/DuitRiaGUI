/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import com.alephnought.duitriagui.GameboardController;
import javafx.scene.paint.Color;

import java.util.Scanner;
public class Bank {

    //overloaded method to check if player can pay for an item with loan given.
    public static boolean canPay(int balance, int item, boolean loan) {
        if (loan) {
            return balance + Constants.LOAN >= item;
        }
        else {
            return balance >= item;
        }
    }

    public static void buyProperty(Player player, Cell cell) {
            cell.setOwner(player);
            player.deductBalance(cell.getPrice());
            GameLogic.setOutputText(player.getName() + " bought " + cell.getName() + " for RM" + cell.getPrice());
    }

    public static void sellProperty(Player player) {
        Cell chosenCell;

        Cell[] ownedProperties = GameLogic.getOwnedProperties(player);
        Set[] ownedSets = GameLogic.getOwnedSets(player);

        //check if player has any property
        if (ownedProperties.length <= 0) {
            GameboardController.showErrorDialog("You don't have any property to sell!");
            return;
        }

        //prompt user to choose a property.
        chosenCell = GameboardController.showListDialog(ownedProperties, "Sell Property", "Choose a property to sell");

        //if property belongs to owned sets
        if (chosenCell.belongsTo(ownedSets)) {
            Set set = GameLogic.getSet(chosenCell);
            if (set.getHouseNumber() > 0) {
                GameboardController.showErrorDialog("You can not sell a property from an enhanced set");
                return;
            }

        }
        chosenCell.setOwner(null);
        player.addBalance(chosenCell.getPrice() / 2);
        GameLogic.setOutputText(player.getName() + " sold " + chosenCell.getName() + " for RM" + chosenCell.getPrice() / 2);
    }

    public static void buyHouse(Player player) {
        Set chosenSet;
        Cell chosenCell;
        Cell[] chosenSetCells = null;
        Set[] ownedSets = GameLogic.getOwnedSets(player);
        boolean allHousesAreFour;

        if (player.getLapNumber() < 3) {
            GameboardController.showErrorDialog("you can only purchase houses on the third round and above.");
            return;
        }
        if (!canPay(player.getBalance(), Constants.HOUSE_PRICE, false)) {
            GameboardController.showErrorDialog("you can not afford a house.");
            return;
        }
        if (ownedSets.length == 0) {
            GameboardController.showErrorDialog("you do not own any sets");
            return;
        }

        chosenSet = GameboardController.showListDialog(ownedSets, "Buy House", "Choose a set to buy a house");

        //Make sure chosenSet is not null
        if(chosenSet == null){
            return;
        }

        chosenSetCells = chosenSet.getCells();

        //check if all houses in set have 4 houses.
        allHousesAreFour = true;
        for (Cell cell : chosenSetCells) {
            allHousesAreFour = allHousesAreFour && (cell.getHouseNumber() == 4);
        }

        if (allHousesAreFour) {
            //if no more houses can be placed on set.
            GameboardController.showErrorDialog("You can not build any more houses on this set.");
            return;
        }
        //check if player has already been given the choice to build evenly or not.
        if (chosenSet.getBuildEvenly() == null) {
                //using Boolean wrapper class to allow null value.
                Boolean buildEvenly = GameboardController.showChoiceDialog("do you want to build evenly?");
                chosenSet.setBuildEvenly(buildEvenly);
                if (buildEvenly == null) {
                    return;
                }
        }
//        if player wants to build evenly
        if (chosenSet.getBuildEvenly()) {
            chosenSet.sortCells();
            chosenSetCells[0].addHouse();
            player.deductBalance(Constants.HOUSE_PRICE);
            GameLogic.setOutputText(player.getName() + " bought a house on " + chosenSetCells[0].getName() + " for RM" + Constants.HOUSE_PRICE);
        }
//        if player does not want to build evenly.
        else if (!chosenSet.getBuildEvenly()) {

//            prompt user to choose a cell.
                chosenCell = GameboardController.showListDialog(chosenSetCells, "Buy House", "Choose a property to buy a house");
                if(chosenCell == null){
                    return;
                }

            if (chosenCell.getHouseNumber() < 4) {
                chosenCell.addHouse();
                GameLogic.setOutputText(player.getName() + " bought a house on " + chosenCell.getName() + " for RM" + Constants.HOUSE_PRICE);
                player.deductBalance(Constants.HOUSE_PRICE);
            } else {
                GameboardController.showErrorDialog("you can not build any more houses on this property");
            }
        }
    }

    public static void sellHouse(Player player) {
        Cell chosenCell;
        Cell[] ownedEnhancedProperties = GameLogic.getOwnedEnhancedProperties(player);

        if (ownedEnhancedProperties.length == 0) {
            GameboardController.showErrorDialog("you do not have houses to sell");
            return;
        }
//        prompt user to choose a property.
        chosenCell = GameboardController.showListDialog(ownedEnhancedProperties, "Sell House", "Choose a property to sell a house");

//        make sure chosenCell is not null
        if(chosenCell == null){
            return;
        }

        chosenCell.removeHouse();
        player.addBalance(Constants.HOUSE_PRICE / 2);
        GameLogic.setOutputText(player.getName() + " sold a house on " + chosenCell.getName() + " for RM" + Constants.HOUSE_PRICE / 2);
    }

    public static int calculateRent(Cell cell) {
        int rent = cell.getRent();
        Player cellOwner = cell.getOwner();

        Set[] ownedSets = GameLogic.getOwnedSets(cellOwner);

        if (cell.belongsTo(ownedSets)) {
            rent *= 2;
        }

        if (cell.getHouseNumber() > 1) {
            rent += Constants.HOUSE_RENT * (cell.getHouseNumber() - 1);
        }

        return rent;
    }

    public static void payRent(Player player, Cell cell) {
        int rent = calculateRent(cell);

        //if the player can afford rent
        if (canPay(player.getBalance(), rent, true)) {
            player.deductBalance(rent);
            Player cellOwner = cell.getOwner();
            cellOwner.addBalance(rent);
            GameLogic.setOutputText(player.getName() + " paid " + cellOwner.getName() + " RM" + rent + " for "+ cell.getName());
        } //if the player can't afford the rent
        else {
            bankruptPlayer(player, cell);
        }
    }
    
    public static void payRent(Player player, Cell cell, int rent){
        //if the player can afford rent
        if (canPay(player.getBalance(), rent, true)) {
            player.deductBalance(rent);
            Player cellOwner = cell.getOwner();
            cellOwner.addBalance(rent);
            GameLogic.setOutputText(player.getName() + " paid " + cellOwner.getName() + " RM" + rent + " for "+ cell.getName());
        } //if the player can't afford the rent
        else {
            bankruptPlayer(player, cell);
        }
    }

    public static void bankruptPlayer(Player player, Cell cell) {
        Cell[] ownedProperties = GameLogic.getOwnedProperties(player);
        for (Cell ownedProperty : ownedProperties) {
            if(cell != null){
                ownedProperty.setOwner(cell.getOwner() != null ? cell.getOwner() : null);
            }else{
                ownedProperty.setOwner(null);
            }
            ownedProperty.resetHouseNumber();
        }
        if(player.getIsForfeited()){
            GameLogic.forfeitedPlayers.add("Forfeited - " + player.getName());
        }else{
            GameLogic.bankruptPlayers.add("Bankrupted - " + player.getName());
        }
        player.setInGame(false);
        player.setIsBankrupt(true);
        player.setBoardPosition(-10, -10); // get player out of the board.
    }
}
