
package com.alephnought.duitriagui.model;

import com.alephnought.duitriagui.GameboardController;

/**
 *
 * @author Mohammed
 */
public class Action {

    private final GameLogic gameLogic;

    //constructor to get GameLogic methods here and store it in the variable gameLogic
    public Action(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    public void getAction(Player player) {

        switch (player.getPosition()) {

            //case player is on Go
            case 1: {
                break;
            }

            //cases player is on Fate
            case 3:
            case 8:
            case 18:
            case 23:
            case 34:
            case 37: {
                gameLogic.fate.drawFate(player);
                break;
            }

            //case player is on Tax
            case 5:
            case 39: {
                if (Bank.canPay(player.getBalance(), Constants.TAX, true)) {
                    GameLogic.setOutputText(player.getName()+" paid "+Constants.TAX + " for tax");
                    player.deductBalance(Constants.TAX);
                } else {
                    Bank.bankruptPlayer(player, GameLogic.currentCell);
                }
                break;
            }

            //case player is on Jail
            case 11: {
                if (player.getIsJailed()) {
                    int[] rolledNumber = Dice.rollDice();
                    if (rolledNumber[0] == rolledNumber[1]) {
                        gameLogic.rollAndGetAction(player, rolledNumber);
                        GameLogic.setOutputText(player.getName() + " rolled a double " + rolledNumber[0] + " so player got out of jail!");
                        player.bailFromJail();
                    } else {
                        if (Bank.canPay(player.getBalance(), Constants.BAIL, true)) {
                            player.deductBalance(Constants.BAIL);
                            GameLogic.setOutputText(player.getName() + " failed to roll a double. Player fined "+ Constants.BAIL + " as bail.");
                            player.bailFromJail();
                        } else {
                            Bank.bankruptPlayer(player, GameLogic.currentCell);
                        }
                    }
                    gameLogic.rollAndGetAction(player);
                }
                break;
            }

            //case player is on Parking
            case 21: {
                if (!player.getCanMove()) {
                        boolean isPlayerMove = GameboardController.showChoiceDialog("Do you want to move?");
                        if (isPlayerMove) {
                            gameLogic.rollAndGetAction(player);
                            player.setCanMove(true);
                        }
                        break;
                } else {
                    player.setCanMove(false);
                }
                break;
            }

            //case player is on 'Go To Jail'
            case 31: {
                player.goToJail();
                break;
            }

            //case player is on any property
            default: {
                int price = GameLogic.currentCell.getPrice();

                //if the cell is not owned by any player.
                if (GameLogic.currentCell.getOwner() == null) {
                    if (player.getLapNumber() < 2) {
                        break;
                    }
                    //check if player has enough money
                    if (Bank.canPay(player.getBalance(), price)) {
                            boolean isPlayerBuyProperty = GameboardController.showChoiceDialog("Do you want to buy this property?");
                            if (isPlayerBuyProperty){
                                Bank.buyProperty(player, GameLogic.currentCell);
                                GameLogic.setOutputText(player.getName() + " bought " + GameLogic.currentCell.getName() + " for RM"+ GameLogic.currentCell.getPrice() + "!");
                            }
                    }
                } 

                //if it is owned by a player which is not the current player.
                else if (GameLogic.currentCell.getOwner() != player) {
                    Bank.payRent(player, GameLogic.currentCell);
                }

                break;
            }
        }
    }
}
