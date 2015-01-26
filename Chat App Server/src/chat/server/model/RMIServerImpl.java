/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.client.interfaces.RMIClientInterface;
import chat.server.interfaces.RMIServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author sharno
 */
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerInterface{
    Vector<RMIClientInterface> clients = new Vector<>();

    public RMIServerImpl() throws RemoteException {
        
    }

    @Override
    public void register(RMIClientInterface client) throws RemoteException {
        clients.add(client);
        System.out.println("unregistered client");
    }

    @Override
    public void unregister(RMIClientInterface client) throws RemoteException {
        clients.remove(client);
        System.out.println("registered client");
    }
}
