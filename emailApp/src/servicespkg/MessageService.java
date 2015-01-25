package servicespkg;

import java.sql.*;
import exceptions.*;
import beanspkg.*;
import java.util.ArrayList;

public class MessageService {

    public Message[] selectAll() throws SQLException {
        Connection connection = null;
        Message[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<Message> list = new ArrayList<Message>();
            Message item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM message");
            while (rs.next()) {
                item = new Message();
                item.setTime(rs.getTimestamp(1));
                item.setSource(rs.getLong(2));
                item.setGroupId(rs.getLong(3));
                item.setText(rs.getString(4));
                list.add(item);
            }

            arr = new Message[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

    public Message selectOne(long source, Timestamp time) throws SQLException {
        Connection connection = null;
        Message item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM message WHERE source = " + source + " and time = " + time);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new Message();
                    item.setTime(rs.getTimestamp(1));
                    item.setSource(rs.getLong(2));
                    item.setGroupId(rs.getLong(3));
                    item.setText(rs.getString(4));
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

    public int insert(Message item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO message VALUES('" + item.getTime()
                    + "', " + item.getSource()
                    + ", " + item.getGroupId()
                    + ", '" + item.getText() + ")";
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

    public int update(Message item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE message SET time = '" + item.getTime()
                    + "', groupId = " + item.getGroupId()
                    + ", text = '" + item.getText() + " WHERE " + "source = " + item.getSource();
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

    public int delete(long source, Timestamp time) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM message WHERE source = " + source + " and time = " + time);
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
