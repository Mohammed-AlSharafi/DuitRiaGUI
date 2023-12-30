/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Arrays;
import java.util.List;
public class Cell {

    private String name;
    private int price;
    private int[] position = new int[2];
    private Player owner;
    private int houseNumber = 0;

    private Circle ownerColoredCircle;

    public Cell(String name, int price, int positionX, int positionY) {
        this.name = name;
        this.price = price;
        this.position[0] = positionX;
        this.position[1] = positionY;
    }

    ;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getRent() {
        return price/10;
    }

    public void setOwner(Player player) {
        owner = player;
        if(player!=null){
            ownerColoredCircle.setFill(player.getCircleColor());
        }else{
            ownerColoredCircle.setFill(Color.BLACK);
        }
    }
    public void createOwnerColoredCircle() {
        ownerColoredCircle = new Circle();
        ownerColoredCircle.setRadius(3);
        ownerColoredCircle.setCenterX(position[0]);
        ownerColoredCircle.setCenterY(position[1]-5);
    }

    public Circle getOwnerColoredCircle() {
        return ownerColoredCircle;
    }
    public Player getOwner() {
        return owner;
    }

    public void addHouse() {
        houseNumber++;
        int position = GameLogic.getCellPosition(this);
        GameLogic.setHouseCounterLbl(position, houseNumber);
    }

    public void removeHouse() {
        houseNumber--;
        int position = GameLogic.getCellPosition(this);
        GameLogic.setHouseCounterLbl(position, houseNumber);
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void resetHouseNumber() {
        houseNumber = 0;
    }

    public boolean belongsTo(Set[] sets) {
        List<Set> setsList = Arrays.asList(sets);
        for (Set set : setsList) {
            if (Arrays.asList(set.getCells()).contains(this)) {
                return true;
            }
        }
        return false;
    }
    public int[] getPosition(){
        return position;
    }

    @Override       //override toString method to print the name of the cell in GUI list
    public String toString() {
        return name;
    }
}
