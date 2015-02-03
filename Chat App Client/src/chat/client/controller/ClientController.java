/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.interfaces.RMIClientInterface;
import chat.client.model.ClientModel;
import chat.client.view.SignInFinal;
import chat.data.model.Contact;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import java.io.File;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author sharno
 */
public class ClientController implements Serializable {

    public static void main(String[] args) {
        ClientController clientController = new ClientController();
    }

    SignInFinal signInView;
    //  SignIn signInView;
    ClientModel modelObj;
    ChatController chatController;
    public static int userId;

    public ClientController() {
        signInView = new SignInFinal(this);
        signInView.setVisible(true);
        modelObj = new ClientModel(this);
    }

    public void signIn(String email, String pass) {
        int id = modelObj.signIn(email, pass);
        if (id == ClientModel.SERVER_DOWN) {
            signInView.showErrorMessage("Server is down, please come back again after several minutes ..!", "Server Maintaince");
        } else if (id == ClientModel.USER_NOT_FOUND) {
            signInView.showErrorMessage("Invalid ID or password", "User Not Found");
        } else {
            chatController = new ChatController(this);
            modelObj.changeState(0, id);
            userId = id;
            String[] x = modelObj.getFriendRequest(id);
            if (x != null) {
                for (int i = 0; i < x.length; i++) {
                    this.receiveAdd(x[i]);
                }
            }
            signInView.dispose();
        }
    }

    public void changeState(int state) {
        modelObj.changeState(state, userId);
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
        signInView = new SignInFinal(this);
        signInView.setVisible(true);
        modelObj = new ClientModel(this);
    }

    public void signUp(User u) {
        modelObj.signUp(u);
    }

    public boolean displayReceiveFilePermission(String fileNameString, Group group) {
        return chatController.displayReceiveFilePermission(fileNameString, group);
    }

    public void sendingFileNotAccepted(Group group) {
        chatController.displayMessage("Sending file was refused", group);
    }

    public void receiveAdd(String email) {

        String s = "this user wants to add you " + email;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int choise = -1;
                choise = JOptionPane.showConfirmDialog(null, s);
                if (choise == 0) {
                    modelObj.acceptRequest(email);
                }
                if (choise == 1) {
                    modelObj.refuseRequest(email, userId);
                }
            }
        });
    }

    public int checkUserExist(String mail) {
        return modelObj.checkUserExist(mail);
    }

    public void sendAdd(String mail) {
        int state = modelObj.checkUserExist(mail);
        if (state == 3) {
            modelObj.sendAdd(mail, userId);
            System.out.println("online");
        } else {
            modelObj.insertAddRequest(mail);
            System.out.println("offline");
        }
    }

    public String getPassword(String text) {
        String returnVal = modelObj.retrievePassword(text);
        return returnVal;
    }

    void sendFilePermission(File f, Group group, int receiverid, int senderid) {
        modelObj.sendFilePermission(f, group, receiverid, senderid);
    }

    public void sendFile(File f, Group group, boolean accepted, RMIClientInterface receiver) {
        chatController.sendFile(f, group, accepted, receiver);
    }

    public void receiveFile(byte[] fileContent, Group group) {
        chatController.receiveFile(fileContent, group);
    }

    public void refreshGroups(Vector<Group> groups) {
        chatController.refreshGroups(groups);
    }

    public String[] getFriendRequest(int userId) {
        return modelObj.getFriendRequest(userId);
    }

    public void createGroupChat(Vector<Contact> groupContacts) {
        modelObj.createGroupChat(groupContacts);
    }
}
