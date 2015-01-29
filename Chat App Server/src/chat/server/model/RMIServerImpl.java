/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.*;
import chat.database.beans.User;
import chat.database.services.DbService;
import chat.database.services.UserService;
import chat.server.interfaces.RMIServerInterface;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */
public class RMIServerImpl extends UnicastRemoteObject implements RMIServerInterface {

    Map<Integer, RMIClientInterface> clients = new HashMap<>();

    public RMIServerImpl() throws RemoteException {

    }

    @Override
    public void register(RMIClientInterface client, Integer userid) throws RemoteException {
        clients.put(userid, client);
        System.out.println("unregistered client");
    }

    @Override
    public void unregister(RMIClientInterface client, Integer userid) throws RemoteException {
        clients.remove(userid, client);
        System.out.println("registered client");
    }

    @Override
    public void sendMessage(Message message, Group group) {
        Vector<Contact> groupContacts = group.getContacts();
        for (Contact contact : groupContacts) {
            Integer userId = Integer.valueOf(contact.getId());
            if (clients.containsKey(userId)) {
                try {
                    clients.get(userId).receiveMessage(message, group);
                } catch (RemoteException ex) {
                    Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    }

    @Override
    public void signUp(User u) throws RemoteException {
        try {
            System.out.println("here in server imp");
            DbService dbService = new DbService();
            UserService service = new UserService();
            service.insert(u);
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void changeState(int value, int userID) throws RemoteException {
       try {
           System.out.println("here in server");
            DbService db=new DbService();
            UserService user=new UserService();
            User x=user.selectOne(userID);
            if(x!=null){
            x.setStatus(value);
                System.out.println("state changed :)");
            }
            else{
            System.out.println("error");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(ChangeStateImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
