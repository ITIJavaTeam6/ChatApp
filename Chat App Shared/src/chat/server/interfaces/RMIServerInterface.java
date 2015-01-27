/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Contact;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sharno
 */
public interface RMIServerInterface extends Remote{
    public void register (RMIClientInterface client) throws RemoteException;
    public void unregister (RMIClientInterface client) throws RemoteException;
     public void signUp(Contact c) throws RemoteException;
    public void addContact(String Email)throws RemoteException;
    public void removeContact(int contactId)throws RemoteException;
    public void loadContact()throws RemoteException;
}
