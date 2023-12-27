/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public class Cell {

    private String name;
    private int price;
    private int[] position = new int[2];
    private Player owner;
    private int houseNumber = 0;

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
    }

    public Player getOwner() {
        return owner;
    }

    public void addHouse() {
        houseNumber++;
    }

    public void removeHouse() {
        houseNumber--;
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
}
