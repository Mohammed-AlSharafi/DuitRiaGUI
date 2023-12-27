/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import java.util.Scanner;

/**
 *
 * @author Mohammed
 */
public class Bank {

    private final GameLogic gameLogic;

    //constructor to get GameLogic methods here and store it in the variable gameLogic
    public Bank(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    Scanner input = new Scanner(System.in);
    int choice;

    public boolean canPay(int balance, int item) {
        return balance >= item ? true : false;
    }

    public boolean canPay(int balance, int item, boolean loan) {
        if (loan) {
            return balance + Constants.LOAN >= item ? true : false;
        } 
        //check later
        else {
            return balance  >= item ? true : false;
        }
    }

    public void buyProperty(Player player, Cell cell) {
        cell.setOwner(player);
        player.deductBalance(cell.getPrice());
    }

    public void sellProperty(Player player) {
        Cell[] ownedProperties = gameLogic.getOwnedProperties(player);
        Set[] ownedSets = gameLogic.getOwnedSets(player);
        Cell chosenCell;

        //check if player has any property
        if (ownedProperties == null || ownedProperties.length <= 0) {
            System.out.println("You don't have any property to sell!");
            return;
        }

        //list owned properties
        for (int i = 0; i < ownedProperties.length; i++) {
            System.out.println((i + 1) + ". " + ownedProperties[i].getName());
        }

        //prompt to choose property to sell
        do {
            System.out.println("Choose a Property from the above list, [-1] to cancel");
            choice = input.nextInt();
        } while ((choice <= 0 || choice > ownedProperties.length) && choice != -1);
        if (choice == -1) {
            return;
        }
        chosenCell = ownedProperties[choice - 1];

        //if property belongs to owned sets
        if (chosenCell.belongsTo(ownedSets)) {
            Set set = gameLogic.getSet(chosenCell);
            if (set.getHouseNumber() > 0) {
                System.out.println("You can not sell houses from a property of an enchanced set");
                return;
            }

        }

        chosenCell.setOwner(null);
        player.addBalance(chosenCell.getPrice() / 2);
    }

    public void buyHouse(Player player) {
        Set[] ownedSets = gameLogic.getOwnedSets(player);
        Set chosenSet;
        Cell[] chosenSetCells;
        Cell chosenCell;
        boolean allHousesAreFour;

        if (player.getLapNumber() < 3) {
            System.out.println("you can only purchase houses on the third round and above.");
            return;
        }
        if (!canPay(player.getBalance(), Constants.HOUSE_PRICE)) {
            System.out.println("you can not afford a house.");
            return;
        }
        if (ownedSets == null || ownedSets.length <= 0) {
            System.out.println("you do not own any sets");
            return;
        }

        //display list of owned sets.
        for (int i = 0; i < ownedSets.length; i++) {
            System.out.println((i + 1) + ". " + ownedSets[i].getName());
        }

        //prompt user to chose a set.
        do {
            System.out.println("Choose a set from the above list, [-1] to cancel");
            choice = input.nextInt();
        } while ((choice <= 0 || choice > ownedSets.length) && choice != -1);
        if (choice == -1) {
            return;
        }
        chosenSet = ownedSets[choice - 1];
        chosenSetCells = chosenSet.getCells();

        //check if all houses in set have 4 houses.
        allHousesAreFour = true;
        for (Cell cell : chosenSetCells) {
            allHousesAreFour = cell.getHouseNumber() == 4 ? true : false;
        }

        if (allHousesAreFour) {
            //if no more houses can be placed on set.
            System.out.println("You can not build any more houses on this set.");
            return;
        }
        //check if player has already been given the choice to build evenly or not.
        if (chosenSet.getBuildEvenly() == null) {
            do {
                System.out.println("do you want to build evenly? \n 1. Yes \n 2. No");
                choice = input.nextInt();

                if (choice == 1) {
                    chosenSet.setBuildEvenly(true);
                    break;
                } else if (choice == 2) {
                    chosenSet.setBuildEvenly(false);
                    break;
                }
            } while (true);

        }
        //if player wants to build evenly
        if (chosenSet.getBuildEvenly() == true) {
            chosenSet.sortCells();
            if (chosenSetCells.length > 0) {
                chosenSetCells[0].addHouse();
                player.deductBalance(Constants.HOUSE_PRICE);
                return;
            } else {
                System.out.println("Error: the set does not have any cells.");
                return;
            }
        } //if player does not want to build evenly.
        else if (chosenSet.getBuildEvenly() == false) {

            if (chosenSetCells == null || chosenSetCells.length <= 0) {
                System.out.println("no properties found in cell");
                return;
            }
            //display list of set cells.
            for (int i = 0; i < chosenSetCells.length; i++) {
                System.out.println((i + 1) + ". " + chosenSetCells[i].getName());
            }
            //prompt user to chose a cell.
            do {
                System.out.println("Choose a Property from the above list, [-1] to cancel");
                choice = input.nextInt();
            } while ((choice <= 0 || choice > chosenSetCells.length) && choice != -1);
            if (choice == -1) {
                return;
            }
            chosenCell = chosenSetCells[choice - 1];
            if (chosenCell.getHouseNumber() < 4) {
                chosenCell.addHouse();
                player.deductBalance(Constants.HOUSE_PRICE);
                return;
            } else {
                System.out.println("you can not build any more houses on this property");
                return;
            }
        }
    }

    public void sellHouse(Player player, Cell cell) {
        Cell[] ownedEnchancedProperties = gameLogic.getOwnedEnhancedProperties(player);
        Cell chosenCell;
        if (ownedEnchancedProperties == null || ownedEnchancedProperties.length <= 0) {
            System.out.println("you do not have houses to sell");
            return;
        }
        //display list of owned enchanced properties.
        for (int i = 0; i < ownedEnchancedProperties.length; i++) {
            System.out.println((i + 1) + ". " + ownedEnchancedProperties[i].getName());
        }

        //prompt user to chose a property.
        do {
            System.out.println("Choose a property from the above list, [-1] to cancel");
            choice = input.nextInt();
        } while ((choice <= 0 && choice > ownedEnchancedProperties.length) && choice != -1);
        if (choice == -1) {
            return;
        }
        chosenCell = ownedEnchancedProperties[choice - 1];
        chosenCell.removeHouse();
        player.addBalance(Constants.HOUSE_PRICE / 2);
    }

    public int calculateRent(Cell cell) {
        int rent = cell.getRent();
        Player cellOwner = cell.getOwner();

        Set[] ownedSets = gameLogic.getOwnedSets(cellOwner);

        if (cell.belongsTo(ownedSets)) {
            rent *= 2;
        }

        if (cell.getHouseNumber() > 1) {
            rent += Constants.HOUSE_RENT * (cell.getHouseNumber() - 1);
        }

        return rent;
    }

    public void payRent(Player player, Cell cell) {
        int rent = calculateRent(cell);

        //if the player can afford rent
        if (canPay(player.getBalance(), rent, true)) {
            player.deductBalance(rent);
            Player cellOwner = cell.getOwner();
            cellOwner.addBalance(rent);
            GameLogic.setOutputText(player.getName() + " paid " + cellOwner.getName() + " " + rent + " for "+ cell.getName());
        } //if the player can't afford the rent
        else {
            bankruptPlayer(player, cell);
        }
    }
    
    public void payRent(Player player, Cell cell, int rent){
        
        //if the player can afford rent
        if (canPay(player.getBalance(), rent, true)) {
            player.deductBalance(rent);
            Player cellOwner = cell.getOwner();
            cellOwner.addBalance(rent);
            GameLogic.setOutputText(player.getName() + " paid " + cellOwner.getName() + " " + rent + " for "+ cell.getName());
        } //if the player can't afford the rent
        else {
            bankruptPlayer(player, cell);
        }
    }

    public void bankruptPlayer(Player player, Cell cell) {
        System.out.println(player.getName() + " went bankrupt!");
        Cell[] ownedProperties = gameLogic.getOwnedProperties(player);
        for (Cell ownedProperty : ownedProperties) {
            ownedProperty.setOwner(cell.getOwner() != null ? cell.getOwner() : null);
            ownedProperty.resetHouseNumber();
        }
        gameLogic.scores.add(player);
        player.setInGame(false);
    }
}
