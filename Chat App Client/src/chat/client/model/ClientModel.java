/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.controller.ClientController;
import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import chat.server.interfaces.RMIServerInterface;
import chat.server.interfaces.SignInInt;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sharno
 */ 

public class ClientModel {
    public static final int USER_NOT_FOUND = -1;
    public static final int SERVER_DOWN = -2;

    int userid = -1;
    
    RMIClientInterface client;
    RMIServerInterface server;
    SignInInt signInObj;
    ClientController controller;
    Registry registry;

    public ClientModel(ClientController controller) {
        this.controller = controller;
        try {
            client = new RMIClientImpl(this);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean connectToServer() {
        try {
            registry = LocateRegistry.getRegistry(5000);
            server = (RMIServerInterface) registry.lookup("chat");
            signInObj = (SignInInt) registry.lookup("signIn");
            chstateOb = (changeStateInt) registry.lookup("changState");
            System.out.println("connected to server");
            return true;
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int signIn(String email, String password) {
        if (connectToServer()) {
            int id = -1;
            try {
                id = signInObj.signIn(email, password);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (id != -1) {
                try {
                    server.register(client, id);
                    userid = id;
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return id;
        } else {
            return SERVER_DOWN;
        }
    }

    public void changeState(int state, int userID) {
        try {
            server.changeState(state, userID);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void sendMessage(Message message, Group group) {
        try {
            server.sendMessage(message, group);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void displayMessage(Message message, Group group) {
        controller.displayMessage(message, group);
    }
    
    public void unregister () {
        try {
            server.unregister(client, userid);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void serverStopping() {
        server = null;
        signInObj = null;
        chstateOb = null;
        controller.serverStopping();
    }

    void serverAnnounce(String message) {
        controller.serverAnnounce(message);
    public void signUp(User u){
        try {
            server.signUp(u);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
