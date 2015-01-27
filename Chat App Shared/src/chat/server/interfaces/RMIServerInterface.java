/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sharno
 */
public interface RMIServerInterface extends Remote{
    public void register (RMIClientInterface client) throws RemoteException;
    public void unregister (RMIClientInterface client) throws RemoteException;

    public void sendMessage(Message message, Group group);
}
