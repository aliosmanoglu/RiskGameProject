/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import game.Player;
import game.Territory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import static server.Server.clientList;

/**
 *
 * @author Ali Haydar
 */
public class SClient extends Thread {

    OutputStream output;
    InputStream input;
    Socket socket;
    Server server;
    int clientId;
    Player player;
    BufferedReader reader;

    public SClient(Server server, Socket acceptedSocket) throws IOException {
        this.socket = acceptedSocket;
        this.server = server;
        this.input = acceptedSocket.getInputStream();
        this.output = acceptedSocket.getOutputStream();
        this.reader = new BufferedReader(new InputStreamReader(this.input, StandardCharsets.UTF_8));
    }

    public void SendPrivate(JSONObject json, int id) throws IOException {
        for (SClient sClient : this.server.clientList) {
            if (sClient.clientId == id) {
                sClient.Send(json.toString());
            }
        }
    }

    public void Send(String msg) throws IOException {
        msg += "\n";
        this.output.write(msg.getBytes(StandardCharsets.UTF_8));
    }

    public void Listen() {
        this.start();
    }

    public void MessageTypeSetOwnerMessage(int ownerID, int territoryID) throws IOException {
        JSONObject json = new JSONObject();

        json.put("ownerID", ownerID);
        json.put("territoryID", territoryID);

        json.put("type", "SETOWNER");

        this.server.broadcast(json.toString());
    }

    public void MessageTypeAttackMessage(JSONArray json) throws IOException {
        this.server.broadcast(json.toString());
    }

    public void MessageTypeSendGameInfos() throws IOException {

        JSONObject json = this.server.game.toJSON();

        json.put("type", "GAMEINFOS");

        System.out.println(json);

        this.server.broadcast(json.toString());

    }

    public void MessageTypeSendTerritories() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "TERRITORYINFOS");
        json.put("territories", this.server.game.toTerritoryJSONArray());

        this.server.broadcast(json.toString()); // sadece bu client'a gönder
    }

    public void ProcessAssignSoldierMessage(JSONObject json) throws IOException {
        int soldier = json.getInt("soldier");
        int territoryID = json.getInt("territoryID");
        int playerID = json.getInt("playerID");

        Player p = null;
        Territory t = null;

        for (Player pl : this.server.game.getPlayers()) {
            if (pl.getPlayerID() == json.getInt("playerID")) {
                p = pl;
                break;
            }
        }

        for (Territory territory : this.server.game.getTerritories()) {
            if (territory.getId() == territoryID) {
                t = territory;
            }
        }

        this.server.game.assignSoldier(t, soldier, p);

        this.server.broadcast(json.toString());

    }

    public void ProccesSendSoldierMessage(JSONObject json) throws IOException {
        Player p = null;
        Territory t = null;
        Territory sourceTerritory = null;
        int soldierCount = json.getInt("soldierCount");
        int source = json.getInt("source");

        for (Player pl : this.server.game.getPlayers()) {
            if (pl.getPlayerID() == json.getInt("playerID")) {
                p = pl;
                break;
            }
        }
        for (Territory territory : this.server.game.getTerritories()) {
            if (territory.getId() == source) {
                sourceTerritory = territory;
                System.out.println("BULUNAN T : + " + territory + " GAME T : + " + sourceTerritory);
                break;
            }
        }

        for (Territory territory : this.server.game.getTerritories()) {
            if (territory.getId() == json.getInt("target")) {
                t = territory;
            }
        }

        JSONObject receive = this.server.game.sendSoldierPlayer(t, sourceTerritory, soldierCount, p);

        System.out.println("DONUS JSON : " + json);

        /*receive.put("type", "NORMALMESSAGE");
        receive.put("data", p.getPlayerID() + " ID oyuncusu " + t.getName() + " bölgesine " + soldierCount + " asker gönderdi");
         */
        System.out.println("CEVAP JSON :" + receive);
        receive.put("type", "NORMALMESSAGE");

        this.server.broadcast(json.toString());
        this.server.broadcast(receive.toString());
    }

    public void ProccesNormalMessage(JSONObject json) throws IOException {

        String data = json.getString("data");

        this.server.broadcast(data);

    }

    public void ProccesJoinMessage(JSONObject json) throws IOException {
        this.player = new Player(json.getInt("clientID"));

        this.server.game.addPlayer(this.player);
        this.player.setColor(this.server.game.generateRandomColor());

        this.MessageTypeSendGameInfos();

    }

    public void ProcessIsItReadyMessage(JSONObject json) throws IOException {
        System.out.println(json);

        for (Player pl : this.server.game.getPlayers()) {
            if (pl.getPlayerID() == json.getInt("id")) {
                pl.setIsItReady(json.getBoolean("data"));
                break;
            }
        }

        this.server.broadcast(json.toString());
    }

    public void ProcessAttackMessage(JSONObject json) throws IOException {
        int attackerID = json.getInt("attackerID");
        int defenderID = json.getInt("defenderID");

        Territory attacker = null;
        Territory defender = null;

        for (Territory territory : this.server.game.getTerritories()) {
            if (attackerID == territory.getId()) {
                attacker = territory;
            }
        }

        for (Territory territory : this.server.game.getTerritories()) {
            if (defenderID == territory.getId()) {
                defender = territory;
            }
        }

        Player p = attacker.getOwner();

        JSONArray result = this.server.game.battleJSON(attacker, defender);

        boolean winner = false;

        for (int i = 0; i < result.length(); i++) {
            JSONObject obj = result.getJSONObject(i);
            if (obj.has("winner")) {
                winner = obj.getBoolean("winner");
                break;
            }
        }

        if (winner) {
            this.server.game.setOwnerTerritory(p, defender);
            this.MessageTypeSetOwnerMessage(p.getPlayerID(), defenderID);
        }

        this.MessageTypeAttackMessage(result);

    }

    public void ProcessSetOwnerMessage(JSONObject json) {
        int territoryID = json.getInt("territoryID");
        int ownerID = json.getInt("ownerID");

        Territory t = null;
        Player p = null;

        for (Territory territory : this.server.game.getTerritories()) {
            if (territoryID == territory.getId()) {
                t = territory;
            }
        }

        for (Player player : this.server.game.getPlayers()) {
            if (ownerID == player.getPlayerID()) {
                p = player;
            }
        }

        this.server.game.setOwnerTerritory(p, t);
    }

    public void ProcessTakeSoldierFromTerritoryMessage(JSONObject json) throws IOException {
        int territoryID = json.getInt("territoryID");
        int ownerID = json.getInt("ownerID");
        int soldierCount = json.getInt("soldierCount");

        Territory t = null;
        Player p = null;

        for (Territory territory : this.server.game.getTerritories()) {
            if (territoryID == territory.getId()) {
                t = territory;
            }
        }

        for (Player player : this.server.game.getPlayers()) {
            if (ownerID == player.getPlayerID()) {
                p = player;
            }
        }

        JSONObject result = this.server.game.takeSoldierFromTerritory(t, soldierCount, p);

        this.server.broadcast(result.getString("data"));

    }

    public void ProcessIsItStartedMessage(JSONObject json) throws IOException {
        JSONObject receive = new JSONObject();

        int clientID = json.getInt("clientID");
        receive.put("type", "ISITSTARTED");
        if (this.server.game.getIsItStarted()) {
            receive.put("bool", true);
        } else {
            receive.put("bool", false);
        }

        this.server.broadcast(receive.toString());

    }

    public void MessageTypeSendIsItStarted() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "ISITSTARTED");
        json.put("bool", true);

        //System.out.println("JSON KONTROL ::::"+ json);
        this.server.game.start();
        this.server.game.shuffleTerritories();

        MessageTypeSendTerritories();
        MessageTypeSendSoldierCount();

        this.Send(json.toString());
    }

    public void MessageTypeSendSoldierCount() throws IOException {
        JSONArray jsonArray = new JSONArray();

        for (Player player : this.server.game.getPlayers()) {
            JSONObject obj = new JSONObject();
            obj.put("playerID", player.getPlayerID());
            obj.put("soldierCount", player.getSoldierCount());
            jsonArray.put(obj);
        }

        JSONObject message = new JSONObject();
        message.put("type", "SOLDIERCOUNT");
        message.put("data", jsonArray);

        this.server.broadcast(message.toString());
    }

    public void ProcessReadyMessage(JSONObject json) throws IOException {
        int id = json.getInt("id");

        for (Player pl : this.server.game.getPlayers()) {
            if (pl.getPlayerID() == id) {
                pl.setIsItReady(true);
                this.server.game.isGameStarted();
                if (this.server.game.getIsItStarted()) {
                    MessageTypeSendIsItStarted();

                }
                break;
            }
        }
    }

    public void ProcessSoldierCountMessage(JSONObject json) {
        JSONArray data = json.getJSONArray("data");

        for (int i = 0; i < data.length(); i++) {
            JSONObject playerObj = data.getJSONObject(i);
            int playerID = playerObj.getInt("playerID");
            int soldierCount = playerObj.getInt("soldierCount");

            Player p = null;
            // Örneğin Game sınıfından player b00up güncelle:
            for (Player pl : this.server.game.getPlayers()) {
                if (pl.getPlayerID() == playerID) {
                    p = pl;
                    break;
                }
            }
            if (p != null) {
                p.setSoldierCount(soldierCount);
            }
        }

    }

    public void ProcessNextTurnMessage(JSONObject json) throws IOException {

        this.server.game.nextTurn();

        this.Send(json.toString());

    }

    /*public void ProcessStartGameMessage(JSONObject json) throws IOException{
        
        this.server.broadcast(json.toString());
    }*/
    public void handleMessageType(String message) throws IOException {

        JSONObject json = new JSONObject(message);

        switch (json.getString("type")) {
            case "SENDSOLDIER":
                ProccesSendSoldierMessage(json);
                break;
            case "NORMALMESSAGE":
                ProccesNormalMessage(json);
                break;
            case "JOIN":
                ProccesJoinMessage(json);
                break;
            case "ISITREADY":
                ProcessIsItReadyMessage(json);
                break;
            case "ATTACK":
                ProcessAttackMessage(json);
                break;
            case "SETOWNER":
                ProcessSetOwnerMessage(json);
                break;
            case "TAKESOLDIERFROMTERRITORY":
                ProcessTakeSoldierFromTerritoryMessage(json);
                break;
            case "ISITSTARTED":
                ProcessIsItStartedMessage(json);
                break;
            case "READY":
                ProcessReadyMessage(json);
                break;
            case "NEXTTURN":
                ProcessNextTurnMessage(json);
                break;
            case "ASSIGNSOLDIER":
                ProcessAssignSoldierMessage(json);
                break;
            /*case "STARTGAME":
                ProcessStartGameMessage(json);
                break;*/
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void run() {

        String msg;
        try {

            if (socket.isConnected()) {

                msg = reader.readLine();

                System.out.println("MESAJ KONTROL : " + msg);

                if (msg != null) {
                    handleMessageType(msg);
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (!socket.isClosed()) {

            try {

                msg = reader.readLine();

                System.out.println("MESAJ KONTROL : " + msg);

                if (msg != null) {
                    handleMessageType(msg);
                }

                //System.out.println("CLIENT ID : "+ this.clientId + " GELEN MESAJ : " + new String(buffer));
                //processDirectedMessage(buffer);
                /*System.out.println(this.socket.getInetAddress()
                        + ":" + this.socket.getPort()
                        + "->" + rsMsg);
                this.Send(" received");*/
            } catch (IOException ex) {
                Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /*public void processDirectedMessage(byte[] rawBytes) {
        try {
            String msg = new String(rawBytes, StandardCharsets.UTF_8).stripLeading();

            if (msg.startsWith("#")) {
                int splitIndex = msg.indexOf('|');
                if (splitIndex > 1) {
                    String targetID = msg.substring(1, splitIndex);
                    String messageBody = msg.substring(splitIndex + 1);

                    String newMsg = this.clientId + ": " + messageBody;

                    this.SendPrivate(newMsg, this, Integer.parseInt(targetID));
                } else {
                    this.Send("Geçersiz mesaj formatı. #hedefID|mesaj");
                }
            } else {
                this.Send("Hedef belirtilmemiş. #hedefID|mesaj");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
