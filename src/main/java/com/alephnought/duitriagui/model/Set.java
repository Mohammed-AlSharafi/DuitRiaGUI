/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Mohammed
 */
public class Set {

    private String name;
    private Cell[] cells;
    private Boolean buildEvenly = null;

    public Set(String name, Cell[] set) {
        this.name = name;
        this.cells = set;
    }

    public void setBuildEvenly(boolean buildEvenly) {
        this.buildEvenly = buildEvenly;
    }

    public String getName() {
        return name;
    }
    
    public Cell[] getCells(){
        return cells;
    }

    public Boolean getBuildEvenly() {
        return buildEvenly;
    }
    
    public void sortCells(){
        Arrays.sort(cells, Comparator.comparing(Cell::getHouseNumber));
    }
    
    public int getHouseNumber(){
        int houseNumber = 0;
        for(Cell cell : cells){
            houseNumber += cell.getHouseNumber();
        }
        return houseNumber;
    }
}
