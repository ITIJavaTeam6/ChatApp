/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.interfaces;

import chat.data.model.Group;
import chat.data.model.Message;
import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author sharno
 */
public interface RMIClientInterface extends Remote {

    public void receiveMessage(Message message, Group group) throws RemoteException;
    public void receiveAdd(String email) throws RemoteException;
    public boolean receiveFilePermission (String fileNameString, Group group) throws RemoteException;
    public void sendFile (File f, Group group, boolean accepted, RMIClientInterface receiver) throws RemoteException;
    public void receiveFile (byte[] fileContent, Group group) throws RemoteException;
    
    public void serverStopping() throws RemoteException;
    public void serverAnnounce(String message) throws RemoteException;

    public String getPassword(String email) throws RemoteException;
    
    
    public void refreshGroups (Vector<Group> groups) throws RemoteException;
}
