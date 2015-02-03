/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.model;

import chat.database.beans.User;
import chat.database.services.DbService;
import chat.database.services.UserService;
import chat.server.interfaces.SignInInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZamZam
 */
public class SignInImp extends UnicastRemoteObject implements SignInInt {

    public SignInImp() throws RemoteException {
        super();
    }

    @Override
    public int signIn(String email, String password) {

        int id = -1;
        try {
            DbService db = new DbService();
            UserService user = new UserService();
            User x = user.selectOne(email);
            if (x != null&&x.getStatus()==1) {
                if (x.getPassword().equals(password)) {
                    id = (int) x.getIduser();
                }
            }
            if(x!=null&&x.getStatus()!=1){
                id=-3;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SignInImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

}
