/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.*;
import chat.database.beans.ChatGroup;
import chat.database.beans.User;
import chat.database.services.ContactService;
import chat.database.services.DbService;
import chat.database.services.GroupService;
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

    public static Map<Integer, RMIClientInterface> clients = new HashMap<>();
    RMIClientInterface client;

    public RMIServerImpl() throws RemoteException {

    }

    @Override
    public void register(RMIClientInterface client, Integer userid) throws RemoteException {
        clients.put(userid, client);
        System.out.println("registered client");
    }

    @Override
    public void unregister(RMIClientInterface client, Integer userid) throws RemoteException {
        clients.remove(userid, client);
        System.out.println("unregistered client");
    }
//

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
    public void callForSendFilePermission(String fileName, Group group, int receiverid) throws RemoteException {
        clients.get(receiverid).receiveFilePermission(fileName, group);
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
        UserService user;
        try {
            System.out.println("here in server");
            DbService db = new DbService();
            user = new UserService();
            User x = user.selectOne(userID);
            if (x != null) {
                x.setStatus(value);
                user.update(x);
                System.out.println("state changed :)");
            } else {
                System.out.println("error");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(ChangeStateImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sendAdd(String email, RMIClientInterface x) throws RemoteException {
        client = x;
        client.receiveAdd(email);
    }

    @Override
    public int checkUserExist(String email) throws RemoteException {
        int id = -1;
        try {

            DbService db = new DbService();
            UserService user = new UserService();
            User x = user.selectOne(email);
            if (x != null) {
                id = (int) x.getIduser();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int getStatus(int id) throws RemoteException {
        int state = -1;
        try {
            DbService db = new DbService();
            UserService user = new UserService();
            User x = user.selectOne(id);
            if (x != null) {
                state = (int) x.getStatus();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }

    @Override
    public void insertAdd(chat.database.beans.Contact cont) throws RemoteException {
        try {
            System.out.println("here in server");
            DbService db = new DbService();
            ContactService contact = new ContactService();
            contact.insert(cont);
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeAdd(chat.database.beans.Contact cont) throws RemoteException {
        try {
            System.out.println("here in server removeAdd");
            DbService db = new DbService();
            ContactService contact = new ContactService();
            contact.delete(cont.getContactId(), cont.getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int createGroup(int userId, int contactId) throws RemoteException {
        chat.database.beans.ChatGroup sender = null;
        chat.database.beans.ChatGroup receiver = null;
        try {
            DbService db = new DbService();
            GroupService groupservice = new GroupService();
            sender = new chat.database.beans.ChatGroup();
            sender.setUserId(userId);
            groupservice.insert(sender);
            int groupid = (int) sender.getIdgroup();
            System.out.println(groupid);
            receiver = new chat.database.beans.ChatGroup();
            receiver.setIdgroup(groupid);
            receiver.setUserId(contactId);
            groupservice.insert(receiver);
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int) sender.getIdgroup();
    }

    @Override
    public void removeGroup(int contactId, int userID) {
        ChatGroup groupId;
        try {
            DbService db = new DbService();
            GroupService groupservice = new GroupService();
            groupId = groupservice.selectOne(contactId, userID);
            groupservice.delete(groupId.getIdgroup(), userID);
            groupservice.delete(groupId.getIdgroup(), contactId);
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
