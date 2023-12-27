/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;
import java.util.Random;

/**
 *
 * @author Mohammed
 */
public class Dice {

    public static int[] rollDice(){
        Random random = new Random();
        int[] diceNumber = new int[2];
        diceNumber[0] = random.nextInt(6) + 1;
        GameLogic.setDiceImage(1, diceNumber[0]);

        diceNumber[1] = random.nextInt(6) + 1;
        GameLogic.setDiceImage(2, diceNumber[1]);
        
        return diceNumber;
    }
}
