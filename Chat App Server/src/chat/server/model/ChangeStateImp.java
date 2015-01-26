/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.server.interfaces.changeStateInt;
import chat.database.beans.User;
import chat.database.services.DbService;
import chat.database.services.UserService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ZamZam
 */
public class ChangeStateImp extends UnicastRemoteObject implements changeStateInt {

    public ChangeStateImp() throws RemoteException {
        super();
    }

    @Override
    public void changeState(int value, int userID) {
        try {
            DbService db=new DbService();
            UserService user=new UserService();
            User x=user.selectOne(userID);
            x.setStatus(value);
        } catch (SQLException ex) {
            Logger.getLogger(ChangeStateImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
