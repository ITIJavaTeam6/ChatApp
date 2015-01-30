/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import chat.database.beans.Contact;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sharno
 */
public interface RMIServerInterface extends Remote {

    public void register(RMIClientInterface client, Integer userid) throws RemoteException;

    public void unregister(RMIClientInterface client, Integer userid) throws RemoteException;

    public void signUp(User u) throws RemoteException;

    public void addContact(String Email) throws RemoteException;

    public void removeContact(int contactId) throws RemoteException;

    public void loadContact() throws RemoteException;

    public void sendMessage(Message message, Group group) throws RemoteException;

    public void sendAdd(String email, RMIClientInterface c) throws RemoteException;

    public void callForSendFilePermission(String fileName, Group group, int receiverid) throws RemoteException;

    public void changeState(int value, int userID) throws RemoteException;

    public int checkUserExist(String email) throws RemoteException;   //return userID
    
    public int getStatus(int id) throws RemoteException;
    
    public void insertAdd(Contact con)throws RemoteException;
    
    public void removeAdd(chat.database.beans.Contact cont) throws RemoteException;
}
