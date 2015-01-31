
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */
public class RMIClientImpl extends UnicastRemoteObject implements RMIClientInterface, Serializable {

    ClientModel model;

    public RMIClientImpl(ClientModel model) throws RemoteException {
        this.model = model;
    }

    @Override
    public void receiveMessage(Message message, Group group) throws RemoteException {
        model.displayMessage(message, group);
    }

    @Override
    public boolean receiveFilePermission(String fileNameString, Group group) throws RemoteException {
        return model.displayReceiveFilePermission(fileNameString, group);
    }

    @Override
    public void sendFile(File f, Group group, boolean accepted, RMIClientInterface receiver) throws RemoteException {
        model.sendFile(f, group, accepted, receiver);
    }

    @Override
    public void receiveFile(byte[] fileContent, Group group) throws RemoteException {
        model.receiveFile(fileContent, group);
    }

    @Override
    public void serverStopping() throws RemoteException {
        model.serverStopping();
    }

    @Override
    public void serverAnnounce(String message) throws RemoteException {
        model.serverAnnounce(message);
    }

    @Override
    public void receiveAdd(String email) throws RemoteException {
        System.out.println(email);
        model.receiveAdd(email);
    }

    @Override
    public String getPassword(String email) throws RemoteException {
        String pass = model.retrievePassword(email);
        if (pass != null) {
            return pass;
        } else {
            return null;
        }
    }

    @Override
    public void refreshGroups(Vector<Group> groups) throws RemoteException {
        model.refreshGroups(groups);
    }
}
