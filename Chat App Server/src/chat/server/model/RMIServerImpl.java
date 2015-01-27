/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.Contact;
import chat.database.beans.User;
import chat.database.services.DbService;
import chat.database.services.UserService;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.server.interfaces.RMIServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerInterface {

    Vector<RMIClientInterface> clients = new Vector<>();

    public RMIServerImpl() throws RemoteException {

    }

    @Override
    public void register(RMIClientInterface client) throws RemoteException {
        clients.add(client);
        System.out.println("unregistered client");
    }

    @Override
    public void unregister(RMIClientInterface client) throws RemoteException {
        clients.remove(client);
        System.out.println("registered client");
    }

    @Override
    public void signUp(Contact c) throws RemoteException {
        try {
            DbService dbService = new DbService();

            UserService service = new UserService();

            service.insert();
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void addContact(String Email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeContact(int contactId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadContact() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    public void sendMessage(Message message, Group group) {
        Vector<Contact> groupContacts = group.getContacts();
        for (Contact contact : groupContacts) {
            for (RMIClientInterface client : clients) {
                if (contact.getId() == client.getId()) {
                    client.receiveMessage(message, group);
                }
            }
        }
    }
}
