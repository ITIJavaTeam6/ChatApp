/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.SignIn;
import chat.data.model.Group;
import chat.data.model.Message;

/**
 *
 * @author sharno
 */
public class ClientController {
    
    public static void main(String[] args) {
        ClientController clientController = new ClientController();
    }
    
//    SignInTempView signInView;
    SignIn signInView;
    ClientModel modelObj;
    ChatController chatController;
    
    public ClientController(){
        signInView = new SignIn(this);
        signInView.setVisible(true);
        modelObj = new ClientModel(this);
    }
    
    public void signIn(String email,String pass){
        int id = modelObj.signIn(email, pass);
        if (id == ClientModel.SERVER_DOWN) {
            signInView.serverDown();
        } else if (id == ClientModel.USER_NOT_FOUND) {
            signInView.failedSignIn();
        } else {
            chatController = new ChatController(this);
            signInView.dispose();
        }
    }
    
    public void changeState(int state){
        modelObj.changeState(state, 3);
    }

    public void displayMessage(Message message, Group group) {
        chatController.displayMessage(message, group);
    }

    public void sendMessage(Message message, Group group) {
        modelObj.sendMessage(message, group);
    }
    
    public void unregister() {
        modelObj.unregister();
    }

    public void serverAnnounce(String message) {
        chatController.serverAnnounce(message);
    }

    public void serverStopping() {
        chatController.serverStopping(); 
        signInView = new SignIn(this);
        signInView.setVisible(true);
        modelObj = new ClientModel(this);
    }
}
