/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.SignInTempView;
import chat.client.view.SignUp;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sharno
 */
public class ClientContoller {
    SignInTempView signInView;
    ClientModel modelObj;
    
    public ClientContoller(SignUp sign){
        
    }
    public ClientContoller(SignInTempView t){
        signInView = t;
        try {
            modelObj=new ClientModel();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientContoller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void signIn(String email,String pass){
         System.out.println(modelObj.signIn(email, pass));
    }
    public void changeState(int state){
        modelObj.changeState(state, 3);
    }
//    public static void main(String[] args) {
//       
//    }

    public void sendMessage(Message message, Group group) {
        modelObj.sendMessage(message, group);
    }
    public void signUp(User u){
        modelObj.signUp(u);
    }
}
