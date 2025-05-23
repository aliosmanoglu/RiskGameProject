/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Ali Haydar
 */
public class Territory {

    Player owner;
    int soldierCount;
    String name;
    ArrayList<Territory> neighbors;
    Game g;
    private static int nextId = 1; // sadece artacak sayaç
    private final int id;          // her Territory'nin sabit ID'si

    String color;

    public Territory(String name,int id) {
        this.name = name;
        neighbors = new ArrayList<>();
        this.g = g;
        this.id = id;
    }

    public void setSoldierCount(int soldierCount) {
        this.soldierCount = soldierCount;
    }
    
    
    

    public ArrayList<Territory> getNeighbors() {
        return neighbors;
    }

    public int getSoldierCount() {
        return soldierCount;
    }
    
    

    public void addNeighbor(Territory t) {
        neighbors.add(t);
    }

    public boolean isNeighbor(Territory t) {
        return this.neighbors.contains(t);
    }

    public Player getOwner() {
        return owner;
    }

    public void setColor(String color){
        this.color = color;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("name", this.name);
        json.put("owner", this.owner != null ? this.owner.getPlayerID() : -1);
        json.put("soldierCount", this.soldierCount);
        return json;
    }

}
