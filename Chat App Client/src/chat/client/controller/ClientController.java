/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.gui.util.GUIUtils;
import chat.client.interfaces.RMIClientInterface;
import chat.client.model.ClientModel;
import chat.client.view.SignInFinal;
import chat.data.model.Group;
import chat.data.model.Message;
import chat.database.beans.User;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
    String serverIP;

    public ClientController() {
        //to read the server ip configuration from ServerConfig.xml
        try {
            SAXParserFactory.newInstance().newSAXParser().parse(new File("ServerConfig.xml"), new DefaultHandler() {

                boolean startReading;

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (startReading) {
                        serverIP = new String(ch, start, length);
                        startReading = false;
                    }
                }

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("serverIP")) {
                        startReading = true;
                    } else {
                        startReading = false;
                    }
                }
            });

            signInView = new SignInFinal(this);
            GUIUtils.setCentreScreen(signInView);
            signInView.setVisible(true);
            modelObj = new ClientModel(this, serverIP);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signIn(String email, String pass) {
        int id = modelObj.signIn(email, pass);
        if (id == ClientModel.SERVER_DOWN) {
            signInView.showErrorMessage("Server is down, please come back again after several minutes ..!", "Server Maintaince");
        } else if (id == ClientModel.USER_NOT_FOUND) {
            signInView.showErrorMessage("Invalid ID or password", "User Not Found");
        } else {
            chatController = new ChatController(this);
            modelObj.changeState(3, id);
            userId = id;
            String[] x = modelObj.getFriendRequest(id);
            for (int i = 0; i < x.length; i++) {
                this.receiveAdd(x[i]);
            }
            signInView.dispose();
        }
    }

    public void changeState(int state) {
        modelObj.changeState(state, 5);
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
        modelObj = new ClientModel(this, serverIP);
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
            modelObj.sendAdd(mail);
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
}
