
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author sharno
 */
public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInterface {
    ClientModel model;
    
    public RMIClientImpl (ClientModel model) throws RemoteException {
        this.model = model;
    }

    @Override
    public void receiveMessage(Message message, Group group) throws RemoteException {
        model.displayMessage(message, group);
    }
    
}