/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import chat.client.interfaces.RMIClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sharno
 */
public interface RMIServer extends Remote{
    public void register (RMIClient client) throws RemoteException;
    public void unregister (RMIClient client) throws RemoteException;
}
