/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.client.interfaces.RMIClientInterface;
import chat.data.model.*;
import chat.data.model.conversion.Converter;
import chat.database.beans.ChatGroup;
import chat.database.beans.User;
import chat.database.services.ContactService;
import chat.database.services.DbService;
import chat.database.services.GroupService;
import chat.database.services.UserService;
import chat.server.interfaces.RMIServerInterface;
import java.io.File;
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
    public void changeState(int status, int userID) throws RemoteException {
        UserService user = new UserService();
        try {
            System.out.println("here in server");
            ContactService service = new ContactService();
            User x = user.selectOne(userID);
            if (x != null) {
                x.setStatus(status);
                user.update(x);
                System.out.println("state changed :) to" + status);
                int[] v = service.getContacts(userID);

                updateUserContactList(userID, Converter.fromUserToContact(x));
                for (int i = 0; i < v.length; i++) {

                    updateUserContactList(v[i], Converter.fromUserToContact(x));
                }

            } else {
                System.out.println("error");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(ChangeStateImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUserContactList(int userId, Contact contactWhoChangedStatus) {
        if (clients.containsKey(userId)) {
            try {
                GroupService user = new GroupService();
                //        try {
                //            System.out.println("here in server");
                //            DbService db = new DbService();
                //            UserService users = new UserService();
                //            ContactService service = new ContactService();
                //
                //            int[] v = service.getContacts(userId);
                //            for (int i = 0; i < v.length; i++) {
                //                nested_contacts = service.getContacts(v[i]);
                //              
                //            }
                //
                //        } catch (SQLException ex) {
                //            ex.printStackTrace();
                //        }
                Vector<Group> groups = user.getGroupsDataModelOfUser(userId);
                try {
                    clients.get(userId).refreshGroups(groups, contactWhoChangedStatus);
                } catch (RemoteException ex) {
                    Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void sendAdd(String email, int userId) throws RemoteException {
        try {
            System.out.println("");
            UserService service = new UserService();
            User user = service.selectOne(email);
            int client = (int) user.getIduser();
            if (clients.containsKey(client)) {
                System.out.println("im map");
                RMIClientInterface clientObj = clients.get(client);
                clientObj.receiveAdd(service.selectOne(userId).getEmail());//
            }
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            System.out.println("here in server");
            DbService db = new DbService();
            ContactService contact = new ContactService();
            contact.delete(cont.getContactId(), cont.getUserId());
        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getPasswordFromServer(String email) throws RemoteException {
        try {
            DbService dbService = new DbService();
            User user = new UserService().selectOne(email);
            if (isNull(user)) {
                return "NoSuchEmail";
            } else {
                String password = user.getPassword();
                return password;
            }
        } catch (SQLException ex) {
            return "NoSuchEmail";
        }
    }

    private boolean isNull(User user) {
        return user == null;
    }

    @Override
    public int createGroup(int userId, int contactId) throws RemoteException {
        chat.database.beans.ChatGroup sender = null;
        chat.database.beans.ChatGroup receiver = null;
        try {
            DbService db = new DbService();
            GroupService groupservice = new GroupService();
            int groupId = groupservice.getGroupId();

            sender = new chat.database.beans.ChatGroup();
            sender.setUserId(userId);
            groupservice.insert(sender, groupId);

            receiver = new chat.database.beans.ChatGroup();
            receiver.setUserId(contactId);
            groupservice.insert(receiver, groupId);

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

    @Override
    public String[] friendRequest(int userId) {
        ContactService service = null;
        try {
            DbService db = new DbService();
            service = new ContactService();

        } catch (SQLException ex) {
            Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return service.getFriendRequest(userId);
    }

    public void sendFilePermission(File f, Group group, int receiverid, int senderid) {
        RMIClientInterface sender = clients.get(senderid);
        if (clients.containsKey(receiverid)) {
            RMIClientInterface receiver = clients.get(receiverid);
            try {
                String fileName = f.getName();
                boolean isAccepted = receiver.receiveFilePermission(fileName, group);
                sender.sendFile(f, group, isAccepted, receiver);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                sender.sendFile(f, group, false, null);
            } catch (RemoteException ex) {
                Logger.getLogger(RMIServerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
