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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */ 

public class ClientModel implements Serializable{

    RMIClientInterface client;
    RMIServerInterface server;
    SignInInt signInObj;
    ClientController controller;

    public ClientModel(ClientController controller) {
        try {
            client = new RMIClientImpl(this);

            Registry registry = LocateRegistry.getRegistry(5002);
            server = (RMIServerInterface) registry.lookup("chat");
            signInObj = (SignInInt) registry.lookup("signIn");
           // chstateOb = (changeStateInt) registry.lookup("changState");
            
            this.controller = controller;

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
            } catch (RemoteException ex) {
                Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id;
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
    public void signUp(User u){
        try {
            server.signUp(u);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
