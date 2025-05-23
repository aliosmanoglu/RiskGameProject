/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author Ali Haydar
 */
public class Player {

    ArrayList<Territory> territories;
    int playerID;
    int soldierCount;
    boolean isTurn;
    boolean isItReady;
    String color;

    public Player(int playerID) {
        this.playerID = playerID;
        this.territories = new ArrayList<>();
        this.isTurn = false;
        this.isItReady = false;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }
    
    

    // Renk Getter ve Setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsTurn() {
        return isTurn;
    }

    

    public int getPlayerID() {
        return playerID;
    }

    public int getSoldierCount() {
        return soldierCount;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }

    public void setIsTurn(boolean isTurn) {
        this.isTurn = isTurn;
    }

    public void setIsItReady(boolean isItReady) {
        this.isItReady = isItReady;
    }

    public boolean getIsItReady() {
        return this.isItReady;
    }

}
