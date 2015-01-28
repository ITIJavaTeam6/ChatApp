/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.SignIn;
import chat.client.view.SignInTempView;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;

/**
 *
 * @author sharno
 */
public class ClientController {
    
    public static void main(String[] args) {
        new ClientController();
    }
    
    SignInTempView signInView;
  //  SignIn signInView;
    ClientModel modelObj;
    ChatController chatController;
    
    public ClientController(){
        signInView = new SignInTempView(this);
        signInView.setVisible(true);
        modelObj = new ClientModel(this);
    }
    public void signIn(String email,String pass){
        int id = modelObj.signIn(email, pass);
        if (id != -1) {
            chatController = new ChatController(this);
            signInView.dispose();
        } else {
           // signInView.failedSignIn();
            System.out.println("error");
        }
    }
    
    public void changeState(int state){
        modelObj.changeState(state,5);
    }

    public void displayMessage(Message message, Group group) {
        chatController.displayMessage(message, group);
    }

    public void sendMessage(Message message, Group group) {
        modelObj.sendMessage(message, group);
    }
    public void signUp(User u){
        modelObj.signUp(u);
    }
}
