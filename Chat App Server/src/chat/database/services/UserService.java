package chat.database.services;

import chat.database.exceptions.MoreThanOneItemException;
import chat.database.beans.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class UserService {

    public int getMales() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(gender) FROM user WHERE gender = 0");
            
            rs.next();
            count = rs.getInt(1);
            rs.close();

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }

    }

    public int getFemales() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(gender) FROM user WHERE gender = 1");
            
            rs.next();
            count = rs.getInt(1);
            rs.close();

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }

    }

    public Vector loadContact(int userId) throws SQLException {
        Connection connection = null;
        ContactService contactService = new ContactService();
        Vector vect = null;
        Statement stmt = connection.createStatement();
        String querystring = new String("select fname,lname,password,email,birthdate,gender from contacts where userId=" + userId + "");
        ResultSet rs = stmt.executeQuery(querystring);
        int i = 0;
        Vector vect_container = new Vector();
        while (rs.next()) {
            vect = new Vector();
            vect.addElement(rs.getString("fname"));
            vect.addElement(rs.getString("lname"));
            vect.addElement(rs.getString("password"));
            vect.addElement(rs.getString("email"));
            vect.addElement(rs.getString("birthdate"));
            vect.addElement(rs.getInt("gender"));
            vect_container.add(vect);
            System.out.println(vect_container.get(i));
            i++;
        }

        return vect_container;
    }

//    public Vector<User> loadContact(int userId) throws SQLException {
//        Connection connection = null;
//        ContactService contactService = new ContactService();
//        //User[] arr = null;
//        Vector<User> list = new Vector<>();
//
//        int[] contactsid = contactService.getContacts(userId);
//        for (int i = 0; i < contactsid.length; i++) {
//
//            list.add(selectOne(contactsid[i]));
//
//        }
//        return list;
//
//    }
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
//
//            item = new User();
//            item.setPassword("NoSuchEmail");
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
            ResultSet rs = stmnt.executeQuery("SELECT * FROM user WHERE email = '" + email + "'");
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
            System.out.println(item.getStatus());
            String updateQuery = "UPDATE user SET fname = '" + item.getFname()
                    + "', lname = '" + item.getLname()
                    + "', password = '" + item.getPassword()
                    + "', bdate = '" + item.getBdate()
                    + "', status = " + item.getStatus()
                    + ", gender = " + item.getGender()
                    + ", cdate = '" + item.getCdate()
                    + "', email = '" + item.getEmail() + "' WHERE " + "iduser = " + item.getIduser();
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

    public int getOnlineStatus() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(status) FROM user WHERE status = 0");

            rs.next();
            count = rs.getInt(1);
            rs.close();

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }

    }

    public int getOffLineStatus() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(status) FROM user WHERE status =1");
            
            rs.next();
            count = rs.getInt(1);
            rs.close();

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }

    }

    public int getBusyStatus() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(status) FROM user WHERE status = 2");
            
            rs.next();
            count = rs.getInt(1);
            rs.close();

            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }

    }

    public int getAwayStatus() throws SQLException {
        Connection connection = null;
        int count = 0;
        try {
            connection = new DbService().getConnection();
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT count(status) FROM user WHERE status = 3");
            
            rs.next();
            count = rs.getInt(1);
            rs.close();
            stmnt.close();
        } finally {
            if (connection != null) {
                connection.close();
            }
            return count;
        }
    }

}
