/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.interfaces;

import chat.data.model.Group;
import chat.data.model.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sharno
 */
public interface RMIClientInterface extends Remote {
    public int getId ();

    public void receiveMessage(Message message, Group group) throws RemoteException;
}
