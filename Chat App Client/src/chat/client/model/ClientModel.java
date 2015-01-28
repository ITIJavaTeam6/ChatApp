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
import chat.server.interfaces.RMIServerInterface;
import chat.server.interfaces.SignInInt;
import chat.server.interfaces.changeStateInt;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */ 

public class ClientModel {

    int userid = -1;
    
    RMIClientInterface client;
    RMIServerInterface server;
    SignInInt signInObj;
    changeStateInt chstateOb;
    ClientController controller;

    public ClientModel(ClientController controller) {
        try {
            client = new RMIClientImpl(this);
            Registry registry = LocateRegistry.getRegistry(5000);
            server = (RMIServerInterface) registry.lookup("chat");
            signInObj = (SignInInt) registry.lookup("signIn");
            chstateOb = (changeStateInt) registry.lookup("changState");
            this.controller = controller;
            
            System.out.println("connected to server");

        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int signIn(String email, String password) {
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
    }

    public void changeState(int state, int userID) {
        try {
            chstateOb.changeState(state, userID);
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
}
