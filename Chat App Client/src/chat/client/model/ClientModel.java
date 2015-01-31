/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.model;

import chat.client.controller.ClientController;
import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.Contact;
import chat.database.beans.User;
import chat.server.interfaces.RMIServerInterface;
import chat.server.interfaces.SignInInt;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */
public class ClientModel implements Serializable {

    public static final int USER_NOT_FOUND = -1;
    public static final int SERVER_DOWN = -2;

    int userid = -1;
    int contactid = -1;
    RMIClientInterface client;
    RMIServerInterface server;
    SignInInt signInObj;
    ClientController controller;
    Registry registry;

    public ClientModel(ClientController controller) {
        this.controller = controller;
        try {
            client = new RMIClientImpl(this);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean connectToServer() {
        try {
            registry = LocateRegistry.getRegistry(5000);
            server = (RMIServerInterface) registry.lookup("chat");
            signInObj = (SignInInt) registry.lookup("signIn");
            System.out.println("connected to server");
            return true;
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int signIn(String email, String password) {
        if (connectToServer()) {
            int id = -1;
            try {
                id = signInObj.signIn(email, password);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (id != -1) {
                try {
                    server.register(client, id);
                    userid = id;
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return id;
        } else {
            return SERVER_DOWN;
        }
    }

    public void changeState(int state, int userID) {
        try {
            server.changeState(state, userID);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(Message message, Group group) {
        try {
            server.sendMessage(message, group);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void displayMessage(Message message, Group group) {
        controller.displayMessage(message, group);
    }

    public void unregister() {
        try {
            server.unregister(client, userid);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void serverStopping() {
        server = null;
        signInObj = null;
        controller.serverStopping();
    }

    void serverAnnounce(String message) {
        controller.serverAnnounce(message);
    }

    public void signUp(User u) {
        try {
            if (connectToServer()) {
                server.signUp(u);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    boolean displayReceiveFilePermission(String fileNameString, Group group) {
        return controller.displayReceiveFilePermission(fileNameString, group);
    }

    public void sendingFileNotAccepted(Group group) {
        controller.sendingFileNotAccepted(group);
    }

    public void receiveAdd(String s) {
        controller.receiveAdd(s);
    }

    public int checkUserExist(String mail) {
        if (connectToServer()) {
            try {
                contactid = server.getStatus(server.checkUserExist(mail));
            } catch (RemoteException ex) {
                Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contactid;
    }

    public void sendAdd(String mail) {
        try {
            server.sendAdd(mail, client);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAddRequest(String mail) {
        try {
            Contact contact = new Contact();
            contact.setUserId(server.checkUserExist(mail));
            contact.setContactId(userid);
            server.insertAdd(contact);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acceptRequest(String email) {
        try {
            int id = server.checkUserExist(email);
            Contact contact = new Contact();
            contact.setUserId(id);
            contact.setContactId(userid);
            server.insertAdd(contact);
            server.createGroup(userid, id);
            System.out.println("added");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    public void refuseRequest(String email) {

        try {
            Contact contact = new Contact();
            int contactId = server.checkUserExist(email);
            contact.setUserId(contactId);
            contact.setContactId(userid);
            //server.removeGroup(contactId, userid);
            server.removeAdd(contact);
            System.out.println("userId " + server.checkUserExist(email));
            System.out.println("contactId " + userid);
            System.out.println("deleted");
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
