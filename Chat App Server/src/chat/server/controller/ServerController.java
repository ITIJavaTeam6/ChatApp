/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.controller;

import chat.client.interfaces.RMIClientInterface;
import chat.server.interfaces.RMIServerInterface;
import chat.server.model.ChangeStateImp;
import chat.server.model.RMIServerImpl;
import chat.server.model.SignInImp;
import chat.server.view.ServerFXMLController;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

/**
 *
 * @author sharno
 */
public class ServerController {

    /**
     * @param args the command line arguments
     */
    
    Registry registry;
    
    SignInImp signIn;
    ChangeStateImp state;
    RMIServerInterface server;
    
    ServerFXMLController fxmlController;
    
    public static void main(String[] args) {
//        new ServerController();
        Application.launch(ServerFXMLController.class, args);
    }

    public ServerController(ServerFXMLController fxmlController) {
        this.fxmlController = fxmlController;
        try {
            signIn=new SignInImp();
            state = new ChangeStateImp();
            server = new RMIServerImpl();
            registry = LocateRegistry.createRegistry(5000);
            registry.rebind("chat", server);
            registry.rebind("signIn", signIn);
            registry.rebind("changState", state);
            System.out.println("Waiting for connections");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stopService() {
        for (Map.Entry<Integer, RMIClientInterface> entry : RMIServerImpl.clients.entrySet()) {
            try {
                entry.getValue().serverStopping();
            } catch (RemoteException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RMIServerImpl.clients.clear();
        
        try {
            registry.unbind("chat");
            registry.unbind("signIn");
            registry.unbind("changState");
            
            UnicastRemoteObject.unexportObject(signIn, true);
            UnicastRemoteObject.unexportObject(state, true);
            UnicastRemoteObject.unexportObject(server, true);
            
            signIn = null;
            state = null;
            server = null;
            System.out.println("Stopped services");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startService() {
        try {
            signIn=new SignInImp();
            state = new ChangeStateImp();
            server = new RMIServerImpl();
            registry.rebind("chat", server);
            registry.rebind("signIn", signIn);
            registry.rebind("changState", state);
            System.out.println("Waiting for connections");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
