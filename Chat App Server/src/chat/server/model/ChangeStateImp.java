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
import java.sql.SQLException;
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
            if(x!=null){
            x.setStatus(value);
            }
            else{
            System.out.println("error");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChangeStateImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
