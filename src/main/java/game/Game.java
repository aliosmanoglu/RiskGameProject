/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package game;

import java.awt.List;
import static java.lang.Math.floor;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ali Haydar
 */
public class Game {

    ArrayList<Player> players;
    ArrayList<Territory> territories;
    int index;
    boolean isItStarted;
    ArrayList<String> messages = new ArrayList<>();

    public Game() {
        players = new ArrayList<>();
        territories = new ArrayList<>();
        index = 0;
        isItStarted = false;

        /*
        A > T1
        B > T2
        C > T3
        D 4
        E 5 
        F 6 
        G 7
        H 8
        I 9
        J 10
        K 11
        L 12
        M 13
        N 14
        O 15
        P 16
        R 17
        S 18
        T 19
        U 20
        Y 21
        X 22
        W 23
         */
        Territory t1 = new Territory("A bölgesi", 1);
        Territory t2 = new Territory("B bölgesi", 2);
        Territory t3 = new Territory("D bölgesi", 3);
        Territory t4 = new Territory("E bölgesi", 4);
        Territory t5 = new Territory("F bölgesi", 5);
        Territory t6 = new Territory("G bölgesi", 6);
        Territory t7 = new Territory("H bölgesi", 7);
        Territory t8 = new Territory("I bölgesi", 8);
        Territory t9 = new Territory("J bölgesi", 9);
        Territory t10 = new Territory("K bölgesi", 10);
        Territory t11 = new Territory("L bölgesi", 11);
        Territory t12 = new Territory("M bölgesi", 12);
        Territory t13 = new Territory("N bölgesi", 13);
        Territory t14 = new Territory("O bölgesi", 14);
        Territory t15 = new Territory("P bölgesi", 15);
        Territory t16 = new Territory("R bölgesi", 16);
        Territory t17 = new Territory("S bölgesi", 17);
        Territory t18 = new Territory("U bölgesi", 18);
        Territory t19 = new Territory("Y bölgesi", 19);
        Territory t20 = new Territory("X bölgesi", 20);
        Territory t21 = new Territory("W bölgesi", 21);
        Territory t22 = new Territory("Z bölgesi", 22);
        Territory t23 = new Territory("T bölgesi", 23);

        /*t1.addNeighbor(t2);
        t1.addNeighbor(t4);

        t2.addNeighbor(t4);
        t2.addNeighbor(t15);
        t2.addNeighbor(t16);

        t4.addNeighbor(t1);
        t4.addNeighbor(t2);
        t4.addNeighbor(t15);
        t4.addNeighbor(t14);
        t4.addNeighbor(t6);
        t4.addNeighbor(t5);

        t5.addNeighbor(t4);
        t5.addNeighbor(t6);
        t5.addNeighbor(t7);
        t5.addNeighbor(t8);

        t6.addNeighbor(t5);
        t6.addNeighbor(t4);
        t6.addNeighbor(t14);
        t6.addNeighbor(t13);
        t6.addNeighbor(t7);
        t6.addNeighbor(t8);
        t6.addNeighbor(t12);

        t7.addNeighbor(t5);
        t7.addNeighbor(t8);
        t7.addNeighbor(t9);

        t8.addNeighbor(t5);
        t8.addNeighbor(t6);
        t8.addNeighbor(t11);
        t8.addNeighbor(t12);
        t8.addNeighbor(t9);

        t9.addNeighbor(t7);
        t9.addNeighbor(t8);
        t9.addNeighbor(t11);
        t9.addNeighbor(t10);

        t10.addNeighbor(t9);
        t10.addNeighbor(t11);

        t11.addNeighbor(t8);
        t11.addNeighbor(t9);
        t11.addNeighbor(t10);
        t11.addNeighbor(t12);
        t11.addNeighbor(t19);

        t12.addNeighbor(t9);
        t12.addNeighbor(t9);
        t12.addNeighbor(t9);
        t12.addNeighbor(t9);
        t12.addNeighbor(t9);*/
        t1.addNeighbor(t2);
        t1.addNeighbor(t4);
        t1.addNeighbor(t5);

        t2.addNeighbor(t1);
        t2.addNeighbor(t4);
        t2.addNeighbor(t15);
        t2.addNeighbor(t16);

        t3.addNeighbor(t1);
        t3.addNeighbor(t2);
        t3.addNeighbor(t5);
        t3.addNeighbor(t6);
        t3.addNeighbor(t15);
        t3.addNeighbor(t14);

        t4.addNeighbor(t4);
        t4.addNeighbor(t6);
        t4.addNeighbor(t7);
        t4.addNeighbor(t8);

        t5.addNeighbor(t4);
        t5.addNeighbor(t5);
        t5.addNeighbor(t7);
        t5.addNeighbor(t8);
        t5.addNeighbor(t12);
        t5.addNeighbor(t13);
        t5.addNeighbor(t14);

        t6.addNeighbor(t9);

        this.territories.add(t1);
        this.territories.add(t2);
        this.territories.add(t3);
        this.territories.add(t4);
        this.territories.add(t5);
        this.territories.add(t6);
        this.territories.add(t7);
        this.territories.add(t8);
        this.territories.add(t9);
        this.territories.add(t10);
        this.territories.add(t11);
        this.territories.add(t12);
        this.territories.add(t13);
        this.territories.add(t14);
        this.territories.add(t15);
        this.territories.add(t16);
        this.territories.add(t17);
        this.territories.add(t18);
        this.territories.add(t19);
        this.territories.add(t20);
        this.territories.add(t21);
        this.territories.add(t22);
        this.territories.add(t23);

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public boolean getIsItStarted() {
        return isItStarted;
    }

    public void setIsItStarted(boolean isItStarted) {
        this.isItStarted = isItStarted;
    }

    public void isGameStarted() {
        boolean bool = true;

        for (Player player : this.getPlayers()) {
            if (!player.getIsItReady()) {
                bool = false;
                break;
            }
        }

        this.isItStarted = (bool && this.getPlayers().size() >= 2);
    }

    // Rastgele renk üretme metodu
    public String generateRandomColor() {
        // Renk listesi tanımla
        String[] colors = {
            "red", "blue", "green", "yellow", "orange", "pink"
        };

        // Rastgele bir renk seç
        int randomIndex;

        String selectedColor;
        boolean isUnique;

        do {
            randomIndex = (int) (Math.random() * colors.length);
            selectedColor = colors[randomIndex];
            isUnique = true;

            for (Player player : this.players) {
                if (selectedColor.equals(player.getColor())) {
                    isUnique = false;
                    break; // renk zaten var, tekrar dene
                }
            }
        } while (!isUnique);

        return colors[randomIndex];
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();

        JSONArray playersArray = new JSONArray();
        for (Player p : players) {
            JSONObject pJson = new JSONObject();
            pJson.put("id", p.getPlayerID());
            pJson.put("color", p.getColor());
            playersArray.put(pJson);
        }
        System.out.println("JSON GAME INFOS : " + playersArray);
        json.put("players", playersArray);

        System.out.println("GAME KONTROL : " + json);

        return json;

    }

    public JSONArray toTerritoryJSONArray() {
        JSONArray arr = new JSONArray();
        for (Territory t : territories) {
            arr.put(t.toJSON());
        }
        return arr;
    }

    //PLAYER FUNCTIONS
    public JSONObject sendSoldierPlayer(Territory t, Territory source, int soldier, Player p) {
        if (p.territories.contains(t)) {
            if (p.soldierCount < soldier) {
                return new JSONObject().put("bool", false)
                        .put("data", "Asker sayisi yetersiz");
            } else {
                //System.out.println("GAME KONTROL ONCE T : " + t.getSoldierCount()  + " SOURCE :  " + source.getSoldierCount());
                t.setSoldierCount(soldier + t.getSoldierCount());
                source.setSoldierCount(source.getSoldierCount() - soldier);
                //System.out.println("GAME KONTROL T : " + t.getSoldierCount()  + " SOURCE :  " + source.getSoldierCount());
                
                return new JSONObject().put("bool", true)
                        .put("data", "Asker gonderimi basarili");
            }
        } else {
            return new JSONObject().put("bool", false)
                    .put("data", "Bolge oyuncuya ait degil.");
        }
    }

    public JSONObject takeSoldierFromTerritory(Territory t, int soldier, Player p) {

        if (p.territories.contains(t)) {
            if (soldier > p.soldierCount) {
                return new JSONObject().put("bool", false)
                        .put("data", "Asker sayisi yetersiz");
            } else {
                t.soldierCount -= soldier;
                p.soldierCount += soldier;
                return new JSONObject().put("bool", true)
                        .put("data", "İslem basarili");
            }
        } else {
            return new JSONObject().put("bool", false)
                    .put("data", "Bolge oyuncuya ait degil");
        }
    }

    //TERRITORY FUNCKTIONS
    public void setOwnerTerritory(Player p, Territory t) {
        t.owner = p;
        t.setColor(p.getColor());

        for (Player player : this.players) {
            if (player.territories.contains(t)) {
                player.territories.remove(t);
                break;
            }
        }
        p.territories.add(t);
    }

    public int calculateDiceCount(boolean isAttacker, int soldierCount) {
        if (isAttacker) {
            if (soldierCount >= 4) {
                return 3;
            } else if (soldierCount == 3) {
                return 2;
            } else if (soldierCount == 2) {
                return 1;
            } else {
                return 0;
            }
        } else {
            if (soldierCount >= 2) {
                return 2;
            } else if (soldierCount == 1) {
                return 1;
            } else {
                return 0;
            }

        }
    }

    public void addTerritory(Territory t) {
        this.territories.add(t);
    }

    public void shuffleTerritories() {
        Collections.shuffle(this.territories);
        int playerIndex = 0;

        // Oyuncu sayısına göre başlangıç asker sayısı belirle
        int startingSoldierCount;
        switch (players.size()) {
            case 2:
                startingSoldierCount = 40;
                break;
            case 3:
                startingSoldierCount = 35;
                break;
            case 4:
                startingSoldierCount = 30;
                break;
            case 5:
                startingSoldierCount = 25;
                break;
            case 6:
                startingSoldierCount = 20;
                break;
            default:
                startingSoldierCount = 30;
        }

        // Her oyuncuya başlangıç askerlerini ata ve territory listesini sıfırla
        for (Player p : players) {
            p.setSoldierCount(startingSoldierCount);
            p.getTerritories().clear();
        }

        // Bölgeleri sırayla dağıt ve her birine 1 asker yerleştir
        for (Territory t : this.territories) {
            Player p = players.get(playerIndex);

            setOwnerTerritory(p, t);               // Sahipliğini ver
            //p.getTerritories().add(t);             // Oyuncuya ekle
            t.setSoldierCount(1);                  // Bölgeye 1 asker koy
            p.setSoldierCount(p.getSoldierCount() - 1); // Oyuncunun askerinden 1 düş

            playerIndex = (playerIndex + 1) % players.size();
        }

        // Kontrol: oyuncuların asker ve bölge durumu
        for (Player p : players) {
            for (Territory territory : p.getTerritories()) {
                System.out.println("PLAYER " + p.getPlayerID()
                        + " - Bölgeler: " + territory.name
                        + ", Elindeki asker: " + p.getSoldierCount());
            }
        }
    }

    public void assignSoldierCount() {
        for (Player player : players) {
            player.soldierCount = (int) Math.max(3, Math.floor(player.territories.size() / 3)) < 3 ? (int) Math.max(3, Math.floor(player.territories.size() / 3)) : 3;

            System.out.println("PLAYER KONTROL ID : " + player.getPlayerID() + " ASKER : " + player.getSoldierCount());
        }
    }

    public JSONObject assignSoldier(Territory t, int soldier, Player p) {
        JSONObject json = new JSONObject();

        json.put("type", "ASSIGNSOLDIER");
        json.put("soldier", soldier);

        if (soldier <= p.getSoldierCount() && p.getTerritories().contains(t)) {
            p.setSoldierCount(p.getSoldierCount() - soldier);
            t.setSoldierCount(t.getSoldierCount() + soldier);
            json.put("data", p.getPlayerID() + " ID'li oyuncu " + soldier + " tane asker gonderdi.");
        } else {
            json.put("data", "ASKER GONDERIMI BASARİSİZ");
        }

        return json;

    }

    public void assignInitialArmies(ArrayList<Player> players, ArrayList<Territory> territories) {
        int playerCount = players.size();
        int totalArmies;

        // Oyuncu sayısına göre başlangıç askeri belirle
        switch (playerCount) {
            case 2:
                totalArmies = 40;
                break;
            case 3:
                totalArmies = 35;
                break;
            case 4:
                totalArmies = 30;
                break;
            case 5:
                totalArmies = 25;
                break;
            case 6:
                totalArmies = 20;
                break;
            default:
                totalArmies = 30;
                break;
        }

        for (Player player : players) {
            ArrayList<Territory> owned = new ArrayList<>();

            // Bu oyuncuya ait bölgeleri topla
            for (Territory t : territories) {
                if (t.getOwner().equals(player)) {
                    owned.add(t);
                }
            }

            // Her bölgeye 1 asker ver
            for (Territory t : owned) {
                t.soldierCount = 1;
            }

        }
    }

    public JSONArray soldierCountToJson() {
        JSONArray arr = new JSONArray();

        for (Player player : this.players) {
            arr.put(new JSONObject().put("id", player.getPlayerID()).put("soldierCount", player.getSoldierCount()));
        }

        return arr;
    }

    public JSONArray territoriesToJson() {
        JSONArray arr = new JSONArray();

        for (Territory territory : this.territories) {
            arr.put(new JSONObject().put("id", territory.getId()).put("soldierCount", territory.getOwner().getPlayerID()));
        }

        return arr;
    }

    public void start() {
        players.get(0).isTurn = true;

        System.out.println("SIRA KONTROL : " + this.players.get(index).getPlayerID());

        if (index != players.size() - 1) {
            index++;
        } else {
            index = 0;
        }

    }

    public void nextTurn() {
        players.get(index - 1).isTurn = false;
        players.get(index).isTurn = true;

        if (index != players.size() - 1) {
            index++;
        } else {
            index = 0;
        }
    }

    public void addPlayer(Player p) {
        this.players.add(p);
        p.setColor(this.generateRandomColor());
    }

    /* public boolean battle(Territory attacker, Territory defender) {

        int attackerDiceCount = calculateDiceCount(true, attacker.soldierCount);
        int defenderDiceCount = calculateDiceCount(false, defender.soldierCount);

        int[] attackerDices = new int[attackerDiceCount];
        int[] defenderDices = new int[defenderDiceCount];

        for (int i = 0; i < attackerDices.length; i++) {
            attackerDices[i] = (int) (Math.random() * 6) + 1;
        }
        for (int i = 0; i < defenderDices.length; i++) {
            defenderDices[i] = (int) (Math.random() * 6) + 1;
        }

        Arrays.sort(attackerDices);

        for (int i = 0; i < attackerDices.length / 2; i++) {
            int temp = attackerDices[i];
            attackerDices[i] = attackerDices[attackerDices.length - 1 - i];
            attackerDices[attackerDices.length - 1 - i] = temp;
        }

        Arrays.sort(defenderDices);

        for (int i = 0; i < defenderDices.length / 2; i++) {
            int temp = defenderDices[i];
            defenderDices[i] = defenderDices[defenderDices.length - 1 - i];
            defenderDices[defenderDices.length - 1 - i] = temp;
        }

        int compareCount = attackerDiceCount <= defenderDiceCount ? attackerDiceCount : defenderDiceCount;

        for (int i = 0; i < compareCount; i++) {
            if (attackerDices[i] > defenderDices[i]) {
                defender.soldierCount--;
                defender.owner.soldierCount--;
            } else {
                attacker.soldierCount--;
                defender.owner.soldierCount--;
            }
        }

        if (defender.soldierCount <= 0) {
            defender.owner.territories.remove(defender);
            setOwnerTerritory(attacker.owner, defender);
            return true;
        } else if (attacker.soldierCount == 0) {
            return false;
        }

        return false;
    }*/
    public JSONArray battleJSON(Territory attacker, Territory defender) {
        JSONArray result = new JSONArray();

        int attackerDiceCount = calculateDiceCount(true, attacker.soldierCount);
        int defenderDiceCount = calculateDiceCount(false, defender.soldierCount);

        int[] attackerDices = new int[attackerDiceCount];
        int[] defenderDices = new int[defenderDiceCount];

        for (int i = 0; i < attackerDices.length; i++) {
            attackerDices[i] = (int) (Math.random() * 6) + 1;
        }
        for (int i = 0; i < defenderDices.length; i++) {
            defenderDices[i] = (int) (Math.random() * 6) + 1;
        }

        Arrays.sort(attackerDices);
        reverse(attackerDices);

        Arrays.sort(defenderDices);
        reverse(defenderDices);

        // Zarları ekle
        JSONObject attackerObj = new JSONObject();
        attackerObj.put("attackerDices", attackerDices);
        result.put(attackerObj);

        JSONObject defenderObj = new JSONObject();
        defenderObj.put("defenderDices", defenderDices);
        result.put(defenderObj);

        int compareCount = Math.min(attackerDiceCount, defenderDiceCount);

        for (int i = 0; i < compareCount; i++) {
            if (attackerDices[i] > defenderDices[i]) {
                defender.soldierCount--;
                defender.owner.soldierCount--;
            } else {
                attacker.soldierCount--;
                defender.owner.soldierCount--;
            }
        }

        boolean attackerWon = false;

        if (defender.soldierCount <= 0) {
            defender.owner.territories.remove(defender);
            setOwnerTerritory(attacker.owner, defender);
            attackerWon = true;
        }

        // Sonuç objesi
        JSONObject resultObj = new JSONObject();
        resultObj.put("winner", attackerWon);
        result.put(resultObj);

        return result;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

}
