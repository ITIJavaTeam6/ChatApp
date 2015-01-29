/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.controller;

import chat.server.interfaces.RMIServerInterface;
//import chat.server.model.ChangeStateImp;
import chat.server.model.RMIServerImpl;
import chat.server.model.SignInImp;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author sharno
 */
public class ServerController {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SignInImp signIn=new SignInImp();
           // ChangeStateImp state=new ChangeStateImp();
            RMIServerInterface server = new RMIServerImpl();
            Registry registry = LocateRegistry.createRegistry(5002);
            registry.rebind("chat", server);
            registry.rebind("signIn", signIn);
           // registry.rebind("changState", state);
            System.out.println("Waiting for connections");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
