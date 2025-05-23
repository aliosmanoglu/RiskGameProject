/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import game.Game;
import game.Player;
import game.Territory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ali Haydar
 */
public class Server extends Thread {

    ServerSocket socket;
    public static ArrayList<SClient> clientList = new ArrayList<>();
    public static Game game = new Game();

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        
        
    }

    public void Listen() {
        this.start();
    }

    public void broadcast(String msg) throws IOException{
        for (SClient sClient : clientList) {
            sClient.Send(msg);
        }
    }
    
    
    @Override
    public void run() {
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if (Server.game.getIsItStarted()) {
                        Server.game.setIsItStarted(true);
                        Thread.currentThread().interrupt();
                        break;
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

        try {
            while (!socket.isClosed()) {
                Socket client = socket.accept();//blocking
                SClient sclient = new SClient(this, client);

                clientList.add(sclient);
                sclient.Listen();
                

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) throws IOException {

        Server s = new Server(5000);
        
     
        s.Listen();
        


    }

}
