package chat.database.services;

import chat.database.exceptions.MoreThanOneItemException;
import chat.database.beans.ChatGroup;
import java.sql.*;
import java.util.ArrayList;

public class GroupService {

    public ChatGroup[] selectAll() throws SQLException {
        Connection connection = null;
        ChatGroup[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<ChatGroup> list = new ArrayList<ChatGroup>();
            ChatGroup item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM ChatGroup");
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
            ResultSet rs = stmnt.executeQuery("SELECT * FROM ChatGroup WHERE idgroup = " + idgroup + " and userId = " + userId);
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

    public int insert(ChatGroup item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO ChatGroup VALUES(" + item.getIdgroup()
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

}
