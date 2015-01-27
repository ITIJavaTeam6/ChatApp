/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.SignInTempView;
import chat.data.model.Group;
import chat.data.model.Message;

/**
 *
 * @author sharno
 */
public class ClientController {
    SignInTempView signInView;
    ClientModel modelObj;
    ChatController chatController;
    
    public ClientController(SignInTempView t){
        signInView = t;
        modelObj = new ClientModel(this);
        chatController = new ChatController(this);
    }
    public void signIn(String email,String pass){
         System.out.println(modelObj.signIn(email, pass));
         // TODO: successful sign in should start a ChatController instance to launch views
    }
    public void changeState(int state){
        modelObj.changeState(state, 3);
    }
//    public static void main(String[] args) {
//       
//    }

    public void displayMessage(Message message, Group group) {
        chatController.displayMessage(message, group);
    }

    public void sendMessage(Message message, Group group) {
        modelObj.sendMessage(message, group);
    }
    
}
