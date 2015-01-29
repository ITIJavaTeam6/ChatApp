
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    @Override
    public boolean receiveFilePermission(String fileNameString, Group group) throws RemoteException {
        return model.displayReceiveFilePermission(fileNameString, group);
    }

    @Override
    public void sendFile(File f, Group group, boolean accepted, RMIClientInterface receiver) throws RemoteException {
        if (! accepted) {
            model.sendingFileNotAccepted(group);
        } else {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(f);
                int length = fileInputStream.available();
                byte[] b = new byte[length];
                fileInputStream.read(b);
                receiver.receiveFile(f, b, group);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileInputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void receiveFile(File f, byte[] fileContent, Group group) throws RemoteException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(f);
            fileOutputStream.write(fileContent);
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void serverStopping() throws RemoteException {
        model.serverStopping();
    }

    @Override
    public void serverAnnounce(String message) throws RemoteException {
        model.serverAnnounce(message);
    }
}