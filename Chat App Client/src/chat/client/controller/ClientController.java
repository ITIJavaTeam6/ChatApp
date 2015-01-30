/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.ContactsListView;
import chat.client.view.SignIn;
import chat.data.model.Contact;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author sharno
 */
public class ClientController implements Serializable{
    
    public static void main(String[] args) {
        ClientController clientController = new ClientController();
    }
    
    SignIn signInView;
  //  SignIn signInView;
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
            modelObj.changeState(3,id);
            signInView.dispose();
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
    
    public void signUp(User u){
        modelObj.signUp(u);
    }

    public boolean displayReceiveFilePermission(String fileNameString, Group group) {
        return chatController.displayReceiveFilePermission(fileNameString, group);
    }

    public void sendingFileNotAccepted(Group group) {
        chatController.displayMessage("Sending file was refused", group);
    }
    public void receiveAdd(String email){
        
        String s="this user wants to add you "+email;
       SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            int choise=-1;
           choise=JOptionPane.showConfirmDialog(null,s); 
           if(choise==0){
               modelObj.acceptRequest();
           }
           if(choise==1){
               modelObj.refuseRequest();
           }
        }
    });
    }
    public int checkUserExist(String mail){
        return modelObj.checkUserExist(mail);
    }
    public void sendAdd(String mail){
        int state= modelObj.checkUserExist(mail);
        if(state==3){
        modelObj.sendAdd(mail);
            System.out.println("online");
        }
        else{
            modelObj.insertAddRequest(mail);
            System.out.println("offline");
        }
    }
    
}
