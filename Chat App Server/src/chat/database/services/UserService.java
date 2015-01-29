package chat.database.services;

import chat.database.exceptions.MoreThanOneItemException;
import chat.database.beans.User;
import java.sql.*;
import java.util.ArrayList;

public class UserService {

    public User[] selectAll() throws SQLException {
        Connection connection = null;
        User[] arr = null;
        try {
            connection = new DbService().getConnection();
            ArrayList<User> list = new ArrayList<User>();
            User item;
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                item = new User();
                item.setIduser(rs.getLong(1));
                item.setFname(rs.getString(2));
                item.setLname(rs.getString(3));
                item.setPassword(rs.getString(4));
                item.setBdate(rs.getTimestamp(5));
                item.setStatus(rs.getLong(6));
                item.setGender(rs.getLong(7));
                item.setCdate(rs.getTimestamp(8));
                item.setEmail(rs.getString(9));
                list.add(item);
            }

            arr = new User[list.size()];
            list.toArray(arr);
        } finally {
            if (connection != null) {
                connection.close();
            }
            return arr;
        }

    }

    public User selectOne(long iduser) throws SQLException {
        Connection connection = null;
        User item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user WHERE iduser = " + iduser);
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new User();
                    item.setIduser(rs.getLong(1));
                    item.setFname(rs.getString(2));
                    item.setLname(rs.getString(3));
                    item.setPassword(rs.getString(4));
                    item.setBdate(rs.getTimestamp(5));
                    item.setStatus(rs.getLong(6));
                    item.setGender(rs.getLong(7));
                    item.setCdate(rs.getTimestamp(8));
                    item.setEmail(rs.getString(9));
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
     public User selectOne(String email) throws SQLException {
        Connection connection = null;
        User item = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user WHERE iduser = '"+email+"'" );
            int count = 0;
            while (rs.next()) {
                count++;
                if (count == 1) {
                    item = new User();
                    item.setIduser(rs.getLong(1));
                    item.setFname(rs.getString(2));
                    item.setLname(rs.getString(3));
                    item.setPassword(rs.getString(4));
                    item.setBdate(rs.getTimestamp(5));
                    item.setStatus(rs.getLong(6));
                    item.setGender(rs.getLong(7));
                    item.setCdate(rs.getTimestamp(8));
                    item.setEmail(rs.getString(9));
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
    public int insert(User item) throws SQLException {
        Connection connection = null;
        try {
            System.out.println("here in insert method");
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String insertQuery = "INSERT INTO user VALUES(" + item.getIduser()
                    + ", '" + item.getFname()
                    + "', '" + item.getLname()
                    + "', '" + item.getPassword()
                    + "', '" + item.getBdate()
                    + "', " + item.getStatus()
                    + ", " + item.getGender()
                    + ", '" + item.getCdate()
                    + "', '" + item.getEmail() + "')";
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

    public int update(User item) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            String updateQuery = "UPDATE user SET fname = '" + item.getFname()
                    + "', lname = '" + item.getLname()
                    + "', password = '" + item.getPassword()
                    + "', bdate = '" + item.getBdate()
                    + "', status = " + item.getStatus()
                    + ", gender = " + item.getGender()
                    + ", cdate = '" + item.getCdate()
                    + "', email = '" + item.getEmail() + " WHERE " + "iduser = " + item.getIduser();
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

    public int delete(long iduser) throws SQLException {
        Connection connection = null;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            int rowsAffected = stmnt.executeUpdate("DELETE FROM user WHERE iduser = " + iduser);
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
