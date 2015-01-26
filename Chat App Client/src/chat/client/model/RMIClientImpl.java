/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.interfaces.RMIClientInterface;
import chat.server.interfaces.RMIServerInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author sharno
 */
public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInterface {
    
    RMIServerInterface server;
    
    public RMIClientImpl () throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(5000);
            server = (RMIServerInterface) registry.lookup("chat");
            server.register(this);
            // TODO: unregister on unexpected quitting
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
    
}
