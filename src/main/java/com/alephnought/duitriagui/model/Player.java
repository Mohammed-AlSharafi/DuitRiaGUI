/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import javafx.scene.shape.Circle;

/**
 *
 * @author Mohammed
 */
public class Player {

    private String name;
    private int balance;
    private int position; //setting default starting position
    private int lapNumber;
    private int orderRoll; //for sorting the player's based on first roll.
    private Circle circle;
    private boolean canMove;
    private boolean isJailed;
    private boolean inGame;

    public Player(String name){ //constructor setting starting values
        this.name = name;
        balance = Constants.INITIAL_BALANCE;
        position = 1; //setting default starting position
        lapNumber = 1;
        canMove = true;
        isJailed = false;
        inGame = true;
    }

    //creates a circle for each player
    public void createCircle(){
        this.circle = new Circle();
        this.circle.setRadius(15);
        this.circle.setTranslateZ(-200);
    }

    public void setBoardPosition(int x, int y){
        circle.setCenterX(x);
        circle.setCenterY(y);
    }

    public void updateBoardPosition(){
        Cell currentCell = GameLogic.getCellByPosition(position);
        System.out.println(currentCell.getName());
        int[] position = currentCell.getPosition();
        setBoardPosition(position[0], position[1]);
    }

    public Circle getCircle() {
        return circle;
    }

    //functions
    public void goToJail() {
        setPosition(11);
        isJailed = true;
        canMove = false;
    }

    public void bailFromJail() {
        isJailed = false;
        canMove = true;
    }

    public void forfeit() {
        inGame = false;
    }

    //setters and getters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public void deductBalance(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setPosition(int position) {

        this.position = position;
        updateBoardPosition();
    }

    public void addToPosition(int number) {
        //conditions on new lap
        if (position + number > 40) {
            //add one to lap number on a new lap and get salary
            lapNumber++;
            balance += Constants.SALARY;
        }

        // make sure player's position is within 1-40
        position = ((position + number - 1) % 40) + 1;
        GameLogic.currentCell = GameLogic.getCellByPosition(position);
        updateBoardPosition();
    }

    public void deductFromPosition(int number) {
        // Deduct the specified amount from the player's position
        position -= number;

        // Ensure player's position is within the valid range (1-40)
        if (position < 1) {
            // If position is less than 1, wrap around to the end of the board
            position += 40;
        }
        GameLogic.currentCell = GameLogic.getCellByPosition(position);
        updateBoardPosition();
    }

    public int getPosition() {
        return position;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public void setOrderRoll(int orderRoll) {
        this.orderRoll = orderRoll;
    }

    public int getOrderRoll() {
        return orderRoll;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean getCanMove() {
        return canMove;
    }

    public boolean getIsJailed() {
        return isJailed;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public boolean getInGame() {
        return inGame;
    }
}
