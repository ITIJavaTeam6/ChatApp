package chat.database.services;

import chat.data.model.Contact;
import chat.data.model.Group;
import chat.data.model.conversion.Converter;
import chat.database.beans.ChatGroup;
import chat.database.beans.User;
import chat.database.exceptions.MoreThanOneItemException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupService {
    
    public Vector<chat.data.model.Group> getGroupsDataModelOfUser (int userid) throws SQLException {
        Vector<chat.data.model.Group> groups = new Vector<>();
        for (Integer groupid : getGroupsIdsOfUser(userid)) {
            groups.add(getGroupDataModel(groupid));
        }
        
        return groups;
    }
    
    public Vector<Integer> getGroupsIdsOfUser(long userId) throws SQLException {
        Connection connection = null;
        Vector<Integer> groupsIds = new Vector<>();
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM chatgroup WHERE userId = " + userId);
            while (rs.next()) {
                groupsIds.add(rs.getInt(1));
            }

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return groupsIds;
        }

    }
    
    public chat.data.model.Group getGroupDataModel (int groupid) throws SQLException {
        chat.data.model.Group group = new chat.data.model.Group();
        Vector<Contact> contacts = getContactsInGroup(groupid);
        
        group.setContacts(contacts);
        group.setId(groupid);
        
        return group;
    }
    
    public Vector<Contact> getContactsInGroup(int groupid) throws SQLException {
        Connection connection = null;
        Vector<Contact> contacts = new Vector<>();
        
        Vector<Integer> contactsIds = getUserIdsInGroup(groupid);
        for (Integer id : contactsIds) {
            UserService userService = new UserService();
            User user = userService.selectOne(id);
            System.out.println("first name:" + user.getFname());
            contacts.add(Converter.fromUserToContact(user));
        }
        return contacts;
    }
    
    public Vector<Integer> getUserIdsInGroup(int groupid) throws SQLException {
        Connection connection = null;
        Vector<Integer> userIds = new Vector<>();
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM chatgroup WHERE idgroup = " + groupid);
            while (rs.next()) {
                userIds.add(rs.getInt(2));
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
            return userIds;
        }
    }

    public ChatGroup[] selectAll() throws SQLException {
        Connection connection = null;
        ChatGroup[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<ChatGroup> list = new ArrayList<ChatGroup>();
            ChatGroup item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM chatgroup");
            while (rs.next()) {
                item = new ChatGroup();
                item.setIdgroup(rs.getLong(1));
                item.setUserId(rs.getLong(2));
                list.add(item);
            }

            arr = new ChatGroup[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

    public ChatGroup selectOne(long idgroup, long userId) throws SQLException {
        Connection connection = null;
        ChatGroup item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM chatgroup WHERE idgroup = " + idgroup + " and userId = " + userId);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new ChatGroup();
                    item.setIdgroup(rs.getLong(1));
                    item.setUserId(rs.getLong(2));
                } else {
                    throw new MoreThanOneItemException();
                }

            }

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return item;
        }

    }

    public int insert(ChatGroup item,int groupId) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            Statement stm = connection.createStatement();
            String insertQuery = "INSERT INTO ChatGroup VALUES(" + groupId
                    + ", " + item.getUserId() + ")";
            insertQuery = insertQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(insertQuery);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
     public int insert(Contact contact,int groupId) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            Statement stm = connection.createStatement();
            String insertQuery = "INSERT INTO ChatGroup VALUES(" + groupId
                    + ", " + contact.getId() + ")";
            insertQuery = insertQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(insertQuery);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public int update(ChatGroup item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE ChatGroup SET userId = " + item.getUserId() + " WHERE " + "idgroup = " + item.getIdgroup();
            updateQuery = updateQuery.replace("'null'", "null");
            int rowsAffected = stmnt.executeUpdate(updateQuery);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public int delete(long idgroup, long userId) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM ChatGroup WHERE idgroup = " + idgroup + " and userId = " + userId);
            stmnt.close();
            if (rowsAffected != 0) {
                return rowsAffected;
            } else {
                return -1;
            }

        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
    public int getGroupId(){
        int id=0;
        try {
            Connection connection;
            connection = new DbService().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT max(idgroup) FROM chatgroup");
            rs.next();
            id=rs.getInt(1);
            id++;
        } catch (SQLException ex) {
            Logger.getLogger(GroupService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    
    public chat.data.model.Group  createeGroup(Vector<Contact> vector){
        Group group=new Group();
        int groupId=getGroupId();
            for (int i = 0; i < vector.size(); i++) {
            try {
                insert(vector.get(i),groupId);
                
            } catch (SQLException ex) {
                Logger.getLogger(GroupService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            group.setContacts(vector);
            group.setId(groupId);
        return group;
    }
}
