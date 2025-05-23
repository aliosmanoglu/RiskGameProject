/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import game.Game;
import game.Player;
import org.json.JSONObject;
import game.Territory;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import Frame.GameFrame;

/**
 *
 * @author Ali Haydar
 */
public class Client extends Thread {

    int id;
    OutputStream output;
    InputStream input;
    Socket socket;
    Game game = new Game();
    GameFrame frame;

    public Client() {
        this.id = (int) (Math.random() * 1000) + 1;

    }

    public void Connect(int port) throws IOException {

        this.socket = new Socket("localhost", 5000);
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();

        this.MessageTypeSendJoinMessage();
    }

    public Game getGame() {
        return game;
    }

    public GameFrame getFrame() {
        return frame;
    }

    public void setFrame(GameFrame frame) {
        this.frame = frame;
    }

    public int getClientId() {
        return id;
    }

    public void Send(String msg) throws IOException {
        msg += "\n";

        //System.out.println("CLIENT MESAJ KONTROL : " + msg);
        this.output.write(msg.getBytes(StandardCharsets.UTF_8));
    }

    public void Listen() {
        this.start();
    }

    public void MessageTypeSendAttackMessage(Territory source,Territory dest){
        JSONObject json = new JSONObject();
        
        json.put("type","ATTACK");
        json.put("source")
    }
    
    public void MessageTypeSendAssignSoldier(Territory t, Player p, int soldier) throws IOException{
        JSONObject json = new JSONObject();
        
        json.put("type","ASSIGNSOLDIER");
        json.put("territoryID",t.getId());
        json.put("playerID",p.getPlayerID());
        
        this.Send(json.toString());
        
        
    }
    
    public void MessageTypeSendNextTurnMessage() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "NEXTTURN");

        this.Send(json.toString());
    }

    public void MessageTypeSendIsItStarted() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "ISITSTARTED");
        json.put("clientID", this.id);

        this.Send(json.toString());
    }

    public void MessageTypeSendSoldierTerritory(Territory t,Territory source, int soldierCount) throws IOException {
        JSONObject json = new JSONObject();

     
        
        json.put("type", "SENDSOLDIER");
        json.put("playerID", this.id);
        json.put("soldierCount", soldierCount);
        json.put("target", t.getId());
        json.put("source",source.getId());

        
        System.out.println("JSON BURDA : " + json);
        String msg = json.toString();

        this.Send(" " + msg);
    }

    public void MessageTypeSendJoinMessage() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "JOIN");
        json.put("clientID", this.id);

        String msg = json.toString();

        this.Send(msg);
    }

    public void MessageTypeSendNormalMessage(String msg) throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "NORMALMESSAGE");
        json.put("data", msg);

        String val = json.toString();

        this.Send(val);
    }

    public void MessageTypeSendReadyMessage() throws IOException {
        JSONObject json = new JSONObject();

        json.put("type", "READY");
        json.put("id", this.id);
        json.put("data", true);

        for (Player player : this.game.getPlayers()) {
            if (player.getPlayerID() == this.id) {
                player.setIsItReady(true);
                System.out.println("OYUNCU HAZIR");
            }
        }

        this.Send(json.toString());
    }

    public void ProcessNormalMessage(JSONObject json) throws IOException {
        System.out.println("NROMAL KONTORL : " + json);
        String data = json.getString("data");

        this.game.getMessages().add(data);
        if (this.frame != null) {
            this.frame.updateView();
        }

    }

    public void ProcessSendSoldierMessage(JSONObject json) throws InterruptedException {
        int playerID = json.getInt("playerID");
        int soldierCount = json.getInt("soldierCount");
        int target = json.getInt("target");
        int source = json.getInt("source");

        Territory t = null;
        for (Territory territory : game.getTerritories()) {
            if (territory.getId() == target) {
                t = territory;
                System.out.println("GAME REFERANS KONTROL T : " + t + " SOURCE" + territory);
                break;
            }
            
        }
        
        Territory sourceTerritory = null;
        for (Territory territory : game.getTerritories()) {
            if (territory.getId() == target) {
                sourceTerritory = territory;
            }
        }
        Player p = null;
        for (Player player : game.getPlayers()) {
            if (player.getPlayerID() == playerID) {
                p = player;
            }
        }

        this.game.sendSoldierPlayer(t,sourceTerritory, soldierCount, p);
        //this.game.getMessages().add(playerID + " ID'li oyuncu " + t.getName() + "'ne " + soldierCount + " asker yolladi.");

        
        if (this.frame != null) {
            this.frame.updateView();
        }

    }

    public void ProcessJoinMessage(JSONObject msg) {
        int id = msg.getInt("clientID");

        this.game.getMessages().add(id + " ID'li oyuncu oyuna giris yapti");

        //this.game.addPlayer(new Player(id));
        //this.game.getPlayers().get((this.game.getPlayers().size() - 1)).setColor(msg.getString("color"));
        if (this.frame != null) {
            this.frame.updateView();
        }
        //System.out.println("PLAYER CLIENT KONTROL : " + this.game.getPlayers());
    }

    public void ProcessGameInfosMessage(JSONObject json) {
        this.game.getPlayers().clear();

        JSONArray players = json.getJSONArray("players");

        //System.out.println("PLAYER CLIENT KONTROL" + players);
        for (int i = 0; i < players.length(); i++) {
            JSONObject p = players.getJSONObject(i);
            Player player = new Player(p.getInt("id"));
            this.game.addPlayer(player);
            player.setColor(p.getString("color"));

        }

        if (this.frame != null) {
            this.frame.updateView();
        }

        //System.out.println("PLAYER KONTROL : " + this.game.getPlayers());
    }
    
    public void ProcessAssignSoldierMessage(JSONObject json) throws IOException {
        int soldier = json.getInt("soldier");
        int territoryID = json.getInt("territoryID");
        int playerID = json.getInt("playerID");
        
        Player p = null;
        Territory t = null;
        
        for (Player pl : this.game.getPlayers()) {
            if (pl.getPlayerID() == json.getInt("playerID")) {
                p = pl;
                break;
            }
        }
        
        for (Territory territory : this.game.getTerritories()) {
            if (territory.getId() == territoryID) {
                t = territory;
            }
        }
        
        JSONObject receive = this.game.assignSoldier(t, soldier, p);
        
        this.game.getMessages().add(receive.getString("data"));
        
        if(this.frame != null) {
            frame.updateView();
        }
    }

    public void ProcessIsItReadyMessage(JSONObject json) {
        for (Player pl : this.game.getPlayers()) {
            if (pl.getPlayerID() == json.getInt("id")) {
                pl.setIsItReady(json.getBoolean("data"));
                System.out.println(json.getInt("id") + " ID li oyuncu " + pl.getIsItReady());
                break;
            }
        }

        if (this.frame != null) {
            this.frame.updateView();
        }
    }

    public void ProcessAttackMessage(JSONArray result) {
        JSONArray attackerDices = null;
        JSONArray defenderDices = null;
        boolean winner = false;

        for (int i = 0; i < result.length(); i++) {
            JSONObject obj = result.getJSONObject(i);
            if (obj.has("attackerDices")) {
                attackerDices = obj.getJSONArray("attackerDices");
            } else if (obj.has("defenderDices")) {
                defenderDices = obj.getJSONArray("defenderDices");
            } else if (obj.has("winner")) {
                winner = obj.getBoolean("winner");
            }
        }

        this.game.getMessages().add("Saldıran zarları : " + attackerDices);
        this.game.getMessages().add("Savunan zarları : " + defenderDices);

        if (this.frame != null) {
            this.frame.updateView();
        }

    }

    public void ProcessSetOwnerMessage(JSONObject json) {
        int territoryID = json.getInt("territoryID");
        int ownerID = json.getInt("ownerID");

        Territory t = null;
        Player p = null;

        System.out.println("ID KONTROL : " + territoryID);

        for (Territory territory : this.game.getTerritories()) {
            System.out.println("FOR KONTROL : " + territory.getId());
            if (territoryID == territory.getId()) {
                t = territory;
                break;
            }
        }

        for (Player player : this.game.getPlayers()) {
            if (ownerID == player.getPlayerID()) {
                p = player;
                break;
            }
        }

        this.game.setOwnerTerritory(p, t);

        this.game.getMessages().add(t.getName() + " adli bolgenin sahibi degisti. Yeni sahibi, " + t.getOwner().getPlayerID() + " ID'li oyuncu.");

        if (this.frame != null) {
            this.frame.updateView();
        }
    }

    public void ProcessTakeSoldierFromTerritoryMessage(JSONObject json) {
        int territoryID = json.getInt("territoryID");
        int ownerID = json.getInt("ownerID");
        int soldierCount = json.getInt("soldierCount");

        Territory t = null;
        Player p = null;

        for (Territory territory : this.game.getTerritories()) {
            if (territoryID == territory.getId()) {
                t = territory;
            }
        }

        for (Player player : this.game.getPlayers()) {
            if (ownerID == player.getPlayerID()) {
                p = player;
            }
        }

        this.game.takeSoldierFromTerritory(t, soldierCount, p);
        if (this.frame != null) {
            this.frame.updateView();
        }

    }

    public void ProcessIsItStartedMessage(JSONObject json) {

        if (json.getBoolean("bool")) {
            this.game.setIsItStarted(true);
            this.game.start();
        } else {
            this.game.setIsItStarted(false);
        }
    }

    public void ProcessNextTurnMessage(JSONObject json) {
        this.game.nextTurn();

        if (this.frame != null) {
            this.frame.updateView();
        }
    }

    /*public void ProcessStartGameMessage(JSONObject json) {
        this.game.start();

        if (this.frame != null) {
            this.frame.updateView();
        }
    }*/
    public void ProcessTerritoryMessage(JSONObject json) {
        JSONArray data = json.getJSONArray("territories");

        System.out.println("GİRİS YAPTI");

        for (int i = 0; i < data.length(); i++) {
            JSONObject item = data.getJSONObject(i);
            int territoryID = item.getInt("id");
            int ownerID = item.getInt("owner");
            int soldierCount = item.getInt("soldierCount");

            //System.out.println("Territory ID: " + territoryID + ", Owner ID: " + ownerID);
            // Eşleşen Territory ve Player nesnelerini bul
            Territory t = null;
            Player p = null;

            for (Territory territory : this.game.getTerritories()) {
                if (territory.getId() == territoryID) {
                    t = territory;
                    break;
                }
            }
            t.setSoldierCount(soldierCount);

            for (Player player : this.game.getPlayers()) {
                if (player.getPlayerID() == ownerID) {
                    p = player;
                    break;
                }
            }

            if (t != null && p != null) {
                this.game.setOwnerTerritory(p, t);
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
            for (Player pl : this.game.getPlayers()) {
                if(pl.getPlayerID() == playerID){
                    p = pl;
                    break;
                }
            }
            if (p != null) {
                p.setSoldierCount(soldierCount);
            }
        }
        if(this.frame != null) {
            this.frame.updateView();
        }
        
        
    }
    

    /* 
    public void ProcessMoveSoldierToTerritory(JSONObject json){
        int territoryID = json.getInt("territoryID");
        int playerID = json.getInt("playerID");
        int soldierCount = json.getInt("soldierCount");
        
        Territory t;
        Player p;
        
        for (Territory territory : this.game.getTerritories()) {
            if(territoryID == territory.getId()) t = territory;
        }
        
        for (Player player : this.game.getPlayers()) {
            if(playerID == player.getPlayerID()) p = player;
        }
        
        this.game.sendSoldierPlayer(t, soldierCount, p);
        
        
    }*/

 /* public void handleMessageType(String message) throws IOException {
        JSONObject json = new JSONObject(message);
        
        

        switch (json.getString("type")) {
            case "SENDSOLDIER":
                ProcessSendSoldierMessage(json);
                break;
            case "NORMALMESSAGE":
                ProcessNormalMessage(json);
                break;
            case "JOIN":
                ProcessJoinMessage(json);
                break;
            case "GAMEINFOS":
                ProcessGameInfosMessage(json);
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
            default:
                throw new AssertionError();
        }
    }*/
    public void handleMessageType(String message) throws IOException, InterruptedException {
        // Mesaj JSONObject mi yoksa JSONArray mi?
        message = message.trim(); // baştaki boşlukları kaldır

        if (message.startsWith("{")) {
            JSONObject json = new JSONObject(message);
            String type = json.getString("type");

            switch (type) {
                case "SENDSOLDIER":
                    ProcessSendSoldierMessage(json);
                    break;
                case "NORMALMESSAGE":
                    ProcessNormalMessage(json);
                    break;
                case "JOIN":
                    ProcessJoinMessage(json);
                    break;
                case "GAMEINFOS":
                    ProcessGameInfosMessage(json);
                    break;
                case "ISITREADY":
                    ProcessIsItReadyMessage(json);
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
                case "NEXTTURN":
                    ProcessNextTurnMessage(json);
                    break;
                case "TERRITORYINFOS":
                    ProcessTerritoryMessage(json);
                    break;
                case "SOLDIERCOUNT" : 
                    ProcessSoldierCountMessage(json);
                    break;
                case "ASSIGNSOLDIER":
                    ProcessAssignSoldierMessage(json);
                    break;
                default:
                    throw new AssertionError("Bilinmeyen mesaj tipi: " + type);
            }

        } else if (message.startsWith("[")) {
            JSONArray array = new JSONArray(message);

            ProcessAttackMessage(array);

        } else {
            throw new IOException("Geçersiz JSON formatı: " + message);
        }
    }

    public void sendThread() {
        new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    /*Scanner s = new Scanner(System.in);
                    System.out.println("Message : ");
                    String msg = s.next();
                    if (msg.equals("ready")) {
                        this.MessageTypeSendReadyMessage();
                    }*/

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Komut girin: ");
                    String input = scanner.nextLine().trim().toLowerCase();

                    if (input.equals("exit")) {
                        System.out.println("Çıkılıyor...");
                        break;
                    }

                    if (input.equals("ready")) {
                        this.MessageTypeSendReadyMessage();
                    } else if (input.equals("attack")) {
                        System.out.print("Saldıran bölge adı: ");
                        String from = scanner.nextLine().trim();

                        System.out.print("Hedef bölge adı: ");
                        String to = scanner.nextLine().trim();

                        System.out.print("Kaç askerle saldırıyorsun? ");
                        int count = Integer.parseInt(scanner.nextLine().trim());

                        JSONObject attackMessage = new JSONObject();
                        attackMessage.put("type", "ATTACK");
                        attackMessage.put("from", from);
                        attackMessage.put("to", to);
                        attackMessage.put("count", count);

                        try {
                            this.handleMessageType(attackMessage.toString());
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("Komut geçersiz ya da oyun başlamadı.");
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        ).start();
    }

    public void run() {
        //DataInputStream dataInput = new DataInputStream(this.input);

        while (!socket.isClosed()) {
            try {

                /* int bsize = dataInput.read();
                byte buffer[] = new byte[bsize];
                dataInput.readFully(buffer);*/
                BufferedReader reader = new BufferedReader(new InputStreamReader(this.input, StandardCharsets.UTF_8));

                String msg = reader.readLine();

                System.out.println("CLIENT DINLEME MESAJ KONTROL : " + msg);
                if (msg != null) {
                    try {
                        handleMessageType(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace(); // veya log yaz
                    }
                }

            } catch (IOException e) {
                Logger.getLogger(SClient.class
                        .getName()).log(Level.SEVERE, null, e);

            }
        }
    }

    public static void main(String[] args) throws IOException {
        Client c = new Client();
        c.Connect(5000);
        c.sendThread();
        c.Listen();
    }

}
