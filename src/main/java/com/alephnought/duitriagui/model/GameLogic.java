/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alephnought.duitriagui.model;

import com.alephnought.duitriagui.GameboardController;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public class GameLogic {

    private static GameboardController gameboardController;
    public GameLogic(GameboardController gameboardController){
        GameLogic.gameboardController = gameboardController;
    }
    public static ArrayList<Player> players = new ArrayList<>();
    public static Player currentPlayer;
    public static Cell currentCell;
    public static ArrayList<Player> scores = new ArrayList<>();
    Fate fate = new Fate(this);
    Action action = new Action(this);
    public static void setOutputText(String text){
        gameboardController.setBoardOutput(text);
    }

    public static void setDiceImage(int diceNumber, int diceValue){
        gameboardController.setDiceImage(diceNumber, diceValue);
    }

    public static void setPlayerNameLbl(String name){
        gameboardController.setPlayerNameLbl(name);
    }
    public static void setPlayerBalanceLbl(int balance){
        gameboardController.setPlayerBalanceLbl(Integer.toString(balance));
    }
    public static void setPlayerColorCircle(Color color){
        gameboardController.setPlayerColorCircle(color);
    }

    public void sortPlayers(){
        sortPlayers(players);
    }
    public void nextTurn() {
        if(numberOfPlayersInGame(players) <= 1){
            getScoreBoard();
        }
        if (currentPlayer == null) {
            // If currentPlayer is not set, start with the first player
            currentPlayer = players.get(0);
        } else {
            // Find the index of the current player in the array
            int currentIndex = players.indexOf(currentPlayer);

            // Move to the next player, or start a new round if at the end
            currentPlayer = findNextPlayerWithInGame((currentIndex + 1) % players.size());

            // Check if a new round is starting
            if (currentIndex == players.size() - 1) {
                // Perform any actions needed at the start of a new round
                System.out.println("Starting a new round!");
            }
        }
        setPlayerNameLbl(currentPlayer.getName());
        setPlayerBalanceLbl(currentPlayer.getBalance());
        setPlayerColorCircle(currentPlayer.getCircleColor());
    }

    private Player findNextPlayerWithInGame(int startIndex) {
        int index = startIndex;
        while (index != startIndex - 1) {
            Player nextPlayer = players.get(index);
            if (nextPlayer.getInGame()) {
                return nextPlayer;
            }
            index = (index + 1) % players.size();
        }
        return null; // If no player with getInGame() == true is found, return null
    }

    public void getAction(Player player) {
        action.getAction(player);
    }

    public void rollAndGetAction(Player player) {
        int[] rolledNumber = Dice.rollDice();
        int totalDiceNumber = rolledNumber[0] + rolledNumber[1];
        player.addToPosition(totalDiceNumber);

        System.out.println(player.getName() + " rolled a " + totalDiceNumber);
        System.out.println(GameLogic.getCellByPosition(player.getPosition()).getName());
        getAction(player);
    }

    public void rollAndGetAction(Player player, int[] rolledNumber) {
        int totalDiceNumber = rolledNumber[0] + rolledNumber[1];
        player.addToPosition(totalDiceNumber);

        System.out.println(player.getName() + " rolled a " + totalDiceNumber);
        System.out.println(GameLogic.getCellByPosition(player.getPosition()).getName());
        getAction(player);
    }

    public static void initializePlayers(String[] playerNames) {
        //initializing a Player object for each player.
        for (int i = 0; i < playerNames.length; i++) {
            String playerName = playerNames[i];
            Player player = new Player(playerName, Constants.COLORS[i]);
            players.add(player);
        }
    }

    public void sortPlayers(ArrayList<Player> players) {
        System.out.println("Let's roll the dice to determine who starts first!");
        for (Player player : players) {
            int[] rolledNumber = Dice.rollDice();
            int totalDiceNumber = rolledNumber[0] + rolledNumber[1];
            player.setOrderRoll(totalDiceNumber);
            System.out.println(player.getName() + " rolled a " + totalDiceNumber);
        }

        // sorts the players based on their roll number in descending order
        players.sort(Comparator.comparingInt(Player::getOrderRoll).reversed());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public static int numberOfPlayersInGame(List<Player> players) {
        int counter = 0;
        for (Player player : players) {
            if (player.getInGame()) {
                counter++;
            }
        }
        return counter;
    }

    public static Cell getCellByPosition(int position) {
        return Board.cells[position - 1];
    }

    public static Cell[] getOwnedProperties(Player player) {

        List<Cell> ownedProperties = new ArrayList<>();

        for (Cell cell : Board.cells) {
            if (cell.getOwner() == player) {
                ownedProperties.add(cell);
            }
        }
        return ownedProperties.toArray(new Cell[0]);
    }

    public static Set[] getOwnedSets(Player player) {
        List<Set> ownedSets = new ArrayList<>();
        List<Cell> ownedProperties = Arrays.asList(getOwnedProperties(player));
        for (Set set : Board.sets) {
            if (ownedProperties.containsAll(Arrays.asList(set.getCells()))) {
                ownedSets.add(set);
            }
        }
        return ownedSets.toArray(new Set[0]);
    }

    public static Cell[] getOwnedEnhancedProperties(Player player) {
        Cell[] OwnedProperties = getOwnedProperties(player);

        List<Cell> ownedEnchancedProperties = new ArrayList<>();

        for (Cell cell : OwnedProperties) {
            if (cell.getHouseNumber() > 0) {
                ownedEnchancedProperties.add(cell);
            }
        }

        return ownedEnchancedProperties.toArray(new Cell[0]);
    }

    public static Set getSet(Cell cell) {
        Set[] sets = Board.sets;
        for (Set set : sets) {
            if (Arrays.asList(set.getCells()).contains(cell)) {
                return set;
            }
        }
        return null;
    }

    public static int getNearestRailroadPosition(Player player){
        int nearestRailroad;
        int railroad1 = 26;
        int railroad2 = 36;

        //check which railroad is closer.
        if ((Math.abs(player.getPosition() - railroad1) < (Math.abs(player.getPosition() - railroad2)))) {
            // if railroad1 is closer.
            return railroad1;
        } else {
            // if railroad 2 is closer.
            return railroad2;
        }
    }

    public void getScoreBoard() {
        Collections.sort(scores, Comparator.comparingInt(Player::getBalance).reversed());

        System.out.println("Scoreboard: ");

        int counter = 1;
        for (Player player : scores) {
            System.out.println(counter + ". " + player.getName());
            counter++;
        }
        System.exit(0);
    }
}
