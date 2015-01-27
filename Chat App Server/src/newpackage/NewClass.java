/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import chat.database.beans.User;
import chat.database.services.DbService;
import chat.database.services.UserService;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmoOOOnA
 */
public class NewClass {

    public static void main(String[] argv) {
        try {
            DbService dbService = new DbService();

            UserService service = new UserService();

            User[] users = service.selectAll();

            for (int i = 0; i < users.length; i++) {
                System.out.println("User is: " + users[i].toString());
            }
            
            service.insert(new User("Mody", "we7e4", "123456", new Timestamp(2012, 3, 4, 0, 0, 0, 0), 0, 1, new Timestamp(2010, 9, 8, 0, 0, 0, 0),"senior.mshgfj@sdf.com"));
        } catch (SQLException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
