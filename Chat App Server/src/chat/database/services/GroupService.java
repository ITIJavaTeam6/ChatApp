package chat.database.services;

import chat.database.exceptions.MoreThanOneItemException;
import chat.database.beans.Group;
import java.sql.*;
import java.util.ArrayList;

public class GroupService {

    public Group[] selectAll() throws SQLException {
        Connection connection = null;
        Group[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<Group> list = new ArrayList<Group>();
            Group item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM group");
            while (rs.next()) {
                item = new Group();
                item.setIdgroup(rs.getLong(1));
                item.setUserId(rs.getLong(2));
                list.add(item);
            }

            arr = new Group[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

    public Group selectOne(long idgroup, long userId) throws SQLException {
        Connection connection = null;
        Group item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM group WHERE idgroup = " + idgroup + " and userId = " + userId);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new Group();
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

    public int insert(Group item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO group VALUES(" + item.getIdgroup()
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

    public int update(Group item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE group SET userId = " + item.getUserId() + " WHERE " + "idgroup = " + item.getIdgroup();
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
            int rowsAffected = stmnt.executeUpdate("DELETE FROM group WHERE idgroup = " + idgroup + " and userId = " + userId);
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
