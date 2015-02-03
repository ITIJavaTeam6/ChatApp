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
import java.io.File;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;
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
    public final static int PORT_NUMBER = 5000;
    final String serverIP;

    public ClientModel(ClientController controller, String serverIp) {
        this.controller = controller;
        this.serverIP = serverIp;
        try {
            client = new RMIClientImpl(this);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean connectToServer() {
        try {
            registry = LocateRegistry.getRegistry(serverIP, PORT_NUMBER);
            server = (RMIServerInterface) registry.lookup("chat");
            signInObj = (SignInInt) registry.lookup("signIn");

            System.out.println("connected to server");
            return true;
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("cannot connect to server bool");
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

            if (id != -1 && id != -3) {
                try {
                    server.register(client, id);
                    userid = id;
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                id = -3;
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

    public boolean signUp(User u) {
        boolean exist = false;
        try {
            if (connectToServer()) {
                exist = server.signUp(u);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    boolean displayReceiveFilePermission(String fileNameString, Group group) {
        return controller.displayReceiveFilePermission(fileNameString, group);
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

    public void sendAdd(String mail, int userId) {
        try {
            server.sendAdd(mail, userId);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAddRequest(String mail) {
        try {
            Contact contact = new Contact();
            contact.setUserId(server.checkUserExist(mail));
            contact.setContactId(userid);
            System.out.println("model insertAddRequest " + contact.getUserId());
            System.out.println("model insertAddRequest " + contact.getContactId());
            contact.setPending(true);
            server.insertAdd(contact);

        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void acceptRequest(String email) {
        try {
            int id = server.checkUserExist(email);
            Contact contact = new Contact();
            Contact sender = new Contact();

            contact.setUserId(id);
            contact.setContactId(userid);
            contact.setPending(false);

            sender.setContactId(id);
            sender.setUserId(userid);
            sender.setPending(false);

            server.insertAdd(contact);
            server.insertAdd(sender);

            server.createGroup(userid, id);
            System.out.println("added");

        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    public void refuseRequest(String email, int userId) {

        try {
            Contact contact = new Contact();
            int contactId = server.checkUserExist(email);
            contact.setUserId(contactId);
            contact.setContactId(userId);
            //server.removeGroup(contactId, userid);
            server.removeAdd(contact);
            System.out.println("userId " + contactId);
            System.out.println("contactId " + userId);
            System.out.println("deleted");
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendFilePermission(File f, Group group, int receiverid, int senderid) {
        try {
            server.sendFilePermission(f, group, receiverid, senderid);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void receiveFile(byte[] fileContent, Group group) {
        controller.receiveFile(fileContent, group);
    }

    void sendFile(File f, Group group, boolean accepted, RMIClientInterface receiver) {
        controller.sendFile(f, group, accepted, receiver);
    }

    void refreshGroups(Vector<Group> groups) {
        controller.refreshGroups(groups);
    }

    public String retrievePassword(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String[] getFriendRequest(int userId) {
        try {
            return server.friendRequest(userId);
        } catch (RemoteException ex) {
            return null;
        }
    }

    public void receiveMessage(String s) {
        controller.reciveMessage(s);
    }
}
