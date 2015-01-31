package chat.database.services;

import chat.database.exceptions.MoreThanOneItemException;
import chat.database.beans.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactService {

//    public int[] getNonOffLineContacts(int userId) throws SQLException {
//        Connection connection = null;
//        int[] arr;
//        ArrayList<Integer> list = new ArrayList<Integer>();
//
//        connection = new DbService().getConnection();
//
//        Statement stmnt = connection.createStatement();
//        System.out.println(userId);
//        ResultSet rs = stmnt.executeQuery("SELECT contactId FROM contact where userId= " + userId + " and status <> 1");
//        while (rs.next()) {
//            list.add(rs.getInt("contactId"));
//        }
//        arr = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            arr[i] = list.get(i);
//        }
//
//        if (connection != null) {
//            connection.close();
//        }
//
//        return arr;
//
//    }

    public int[] getContacts(int userId) throws SQLException {
        Connection connection = null;
        int[] arr;
        ArrayList<Integer> list = new ArrayList<Integer>();

        connection = new DbService().getConnection();

        Statement stmnt = connection.createStatement();
        System.out.println(userId);
        ResultSet rs = stmnt.executeQuery("SELECT contactId FROM contact where userId= " + userId + "");
        while (rs.next()) {
            list.add(rs.getInt("contactId"));
        }
        arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

//        } finally {
//            if (connection != null) {
//                connection.close();
//            }
//        }
        return arr;

    }

    public Contact[] selectAll() throws SQLException {
        Connection connection = null;
        Contact[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<Contact> list = new ArrayList<Contact>();
            Contact item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM contact");
            while (rs.next()) {
                item = new Contact();
                item.setContactId(rs.getLong(1));
                item.setUserId(rs.getLong(2));
                list.add(item);
            }

            arr = new Contact[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

    public Contact selectOne(long contactId, long userId) throws SQLException {
        Connection connection = null;
        Contact item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM contact WHERE contactId = " + contactId + " and userId = " + userId);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new Contact();
                    item.setContactId(rs.getLong(1));
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

    public int insert(Contact item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            System.out.println(item.getContactId());
            System.out.println(item.getUserId());
            String insertQuery = "INSERT INTO contact VALUES(" + item.getContactId()
                    + ", " + item.getUserId() + "," + item.getPending() + ")";
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

    public int update(Contact item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE contact SET userId = " + item.getUserId() + " WHERE " + "contactId = " + item.getContactId();
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

    public int delete(long contactId, long userId) throws SQLException {
        Connection connection = null;
        try {
            System.out.println("in database delete ");
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM contact WHERE contactId = " + userId + " and userId = " + contactId);
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

    public String[] getFriendRequest(int userId) {
        Vector<String> vector = new Vector<String>();
        String[] arr = null;
        try {
            Connection connection = null;
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT contactid FROM contact where userid=" + userId + " and binding=1");
            while (rs.next()) {
                vector.add(getEmail((int)rs.getLong(1)));
            }
            arr = new String[vector.size()];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = vector.get(i);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }
    public String getEmail(int id){
        Connection connection = null;
        String email="";
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT email from user where iduser="+id+"");
            rs.next();
            email=rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }
}
