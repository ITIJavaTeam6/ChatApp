/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ZamZam
 */
public interface changeStateInt extends Remote {

    public void changeState(int value,int userID) throws RemoteException;
}
