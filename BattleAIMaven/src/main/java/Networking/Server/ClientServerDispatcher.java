/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking.Server;

import Client.ConnectionHandler;
import Constants.MasterServerConstants;
import Networking.Requests.HostMatch;
import Networking.Requests.RegisterActivity;
import Networking.Requests.Request;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class ClientServerDispatcher extends ServerDispatcher {
    
    private volatile Match activeMatch;
    private static ClientServerDispatcher instance;
    
    private ClientServerDispatcher() {
    }
    
    public static ClientServerDispatcher getInstance() {
        if (instance == null)
            instance = new ClientServerDispatcher();
        
        return instance;
    }
    
    /**
     * Starts a timer which first sends a HostMatch request to the master server,
     * in order to register the match. After that it sends RegisterActivity 
     * requests each MasterServerConstants.Packet_Delay milliseconds which are used
     * by the master server to confirm that this match is still active and therefor
     * continue listing it.
     */
    private void startMasterServerNotifier() {
        Timer masterServerNotifier = new Timer();
                
        TimerTask notification = new TimerTask() {
            private boolean matchRegistered = false;
            
            @Override
            public void run() {
                if (!isRunning) {
                    this.cancel();
                    System.out.println("Cancelling");
                    return;
                }
                
                try {
                    if (!matchRegistered) {
                        // Register match with the master server 
                        ConnectionHandler.getInstance().sendToMasterServer(new HostMatch(activeMatch));
                        matchRegistered = true;
                    }
                    // Confirm activity
                    ConnectionHandler.getInstance().sendToMasterServer(new RegisterActivity());
                } catch (IOException ex) {
                    this.cancel();
                    Logger.getLogger(ClientServerDispatcher.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };

        masterServerNotifier.scheduleAtFixedRate(notification, 0, MasterServerConstants.PACKET_DELAY);
    }
    
    /**
     * 
     * @param port The port on which this server will start.
     * @param match The match object associated with this server.
     * @return true if successful, false otherwise.
     */
    public boolean start(Match match) {
        this.activeMatch = match;
        this.port = match.getPort();
        boolean result = this.start(port);
        startMasterServerNotifier();
        return result;
    }
    
    /**
     * Starts the loop which handles incoming connections.
     * @param serverSocket The server socket to which the clients will connect to.
     */
    @Override
    protected void listenForConnections(ServerSocket serverSocket) {
        while (isRunning) {
            try {
                Socket clientSocket = serverSocket.accept();
                PlayerConnection playerConnection = new PlayerConnection(clientSocket);
                THREAD_POOL.execute(playerConnection);
                activeConnections.add(playerConnection);
            } catch (IOException ex) {
                Logger.getLogger(ServerDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void run() {
        startConnectionCleaner();
        listenForConnections(serverSocket);
    }
    
    /** Send a request to all clients connected to this match. 
     * @param request The request which will be broadcasted to all connected players.
     */
    public synchronized void broadcast(Request request) {
        for (Connection i: activeConnections)
            try {
                i.getOutputStream().reset();
                i.getOutputStream().writeObject(request);
                i.getOutputStream().flush();
            } catch (IOException ex) {
                Logger.getLogger(ClientServerDispatcher.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * @return The active match associated with this server.
     */
    public Match getActiveMatch() {
        return activeMatch;
    }
}
