package chat.database.services;

import chat.database.beans.Contact;
import java.sql.*;
import exceptions.*;
import java.util.ArrayList;

public class ContactService {

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
            String insertQuery = "INSERT INTO contact VALUES(" + item.getContactId()
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
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM contact WHERE contactId = " + contactId + " and userId = " + userId);
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

}
