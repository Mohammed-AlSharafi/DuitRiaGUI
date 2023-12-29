package com.alephnought.duitriagui.model;

import java.util.Random;


public class Fate {

    private final GameLogic gameLogic;
    private Random random = new Random();

    //constructor to get GameLogic methods here and store it in the variable gameLogic
    public Fate(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void drawFate(Player currentPlayer) {
        switch (random.nextInt(10)) {
            case 0:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Advance To Go\"");
                currentPlayer.addToPosition(40 - currentPlayer.getPosition() + 1);
                break;
            case 1:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Advance To Nearest Railroad\"");
                int nearestRailroad = GameLogic.getNearestRailroadPosition(currentPlayer);
                currentPlayer.setPosition(nearestRailroad);
                
                //if railroad is not owned
                if (GameLogic.currentCell.getOwner() == null) {
                    gameLogic.getAction(currentPlayer);
                //if current player is the owner
                } else if (GameLogic.currentCell.getOwner() == currentPlayer) {
                    return;
                    //if another player owns the railroad
                } else {
                    if (Bank.canPay(currentPlayer.getBalance(), 2 * GameLogic.currentCell.getRent(), true)) {
                        //player pays double rent as said per topic info.
                        Bank.payRent(currentPlayer, GameLogic.currentCell, (2 * Bank.calculateRent(GameLogic.currentCell)));
                    } else {
                        Bank.bankruptPlayer(currentPlayer, GameLogic.currentCell);
                    }
                }
                break;
            case 2:
                int totalBirthdayGift = 0;
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"It's your birthday, you get "+Constants.BIRTHDAY_GIFT+" from each player!\"");
                for (Player player : gameLogic.getPlayers()) {
                    if(Bank.canPay(player.getBalance(), Constants.BIRTHDAY_GIFT, true)){
                        player.deductBalance(Constants.BIRTHDAY_GIFT);
                    }
                    else{
                        //passing null so that the player doesn't give belongings to another player when bankrupt
                        Bank.bankruptPlayer(player, null);
                    }
                    totalBirthdayGift+=Constants.BIRTHDAY_GIFT;
                }
                currentPlayer.addBalance(Constants.BIRTHDAY_GIFT);
                break;
            case 3:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Bank Made an error in your favor. You get RM"+Constants.BANK_ERROR+"!\"");
                currentPlayer.addBalance(Constants.BANK_ERROR);
                break;
            case 4:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"You are set back three spaces.\"");
                currentPlayer.deductFromPosition(3);
                break;
            case 5:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"You have to go to jail.\"");
                currentPlayer.goToJail();
                break;
            case 6:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Make general repairs on all your houses.\n For each house pay RM "+Constants.BANK_ERROR+".\"");
                int totalPropertyRepairPrice;
                Cell[] ownedEnhancedProperties = GameLogic.getOwnedEnhancedProperties(currentPlayer);
                totalPropertyRepairPrice = Constants.PROPERTY_REPAIRS * ownedEnhancedProperties.length;
                if(Bank.canPay(currentPlayer.getBalance(), totalPropertyRepairPrice, true)){
                    currentPlayer.deductBalance(totalPropertyRepairPrice);
                }
                else{
                    Bank.bankruptPlayer(currentPlayer, GameLogic.currentCell);
                }
                currentPlayer.deductBalance(totalPropertyRepairPrice);
                break;
            case 7:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Pay hospital fee of RM"+Constants.HOSPITAL_FEES+"!\"");
                System.out.println("Pay hospital fee of RM250K");
                if (Bank.canPay(currentPlayer.getBalance(), Constants.HOSPITAL_FEES, true)) {
                    currentPlayer.deductBalance(Constants.HOSPITAL_FEES);
                } else {
                    Bank.bankruptPlayer(currentPlayer, GameLogic.currentCell);
                }
                break;
            case 8:
                GameLogic.setOutputText(currentPlayer.getName() + " drew a Fate card: \"Pay School fee of RM"+Constants.SCHOOL_FEES+"!\"");
                if (Bank.canPay(currentPlayer.getBalance(), Constants.SCHOOL_FEES, true)) {
                    currentPlayer.deductBalance(Constants.SCHOOL_FEES);
                } else {
                    Bank.bankruptPlayer(currentPlayer, GameLogic.currentCell);
                }
                break;
            case 9:
                GameLogic.setOutputText(currentPlayer.getName() + " Pay speeding fine of RM"+Constants.SPEEDING_FINE+"!\"");
                if (Bank.canPay(currentPlayer.getBalance(), Constants.SPEEDING_FINE, true)) {
                    currentPlayer.deductBalance(Constants.SPEEDING_FINE);
                } else {
                    Bank.bankruptPlayer(currentPlayer, GameLogic.currentCell);
                }
                break;
            default:
                break;
        }
    }
}
