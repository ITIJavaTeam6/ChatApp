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
import chat.client.view.StatusNotifierFrame;
import chat.data.model.Contact;
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
        readServerConfig();
        modelObj = new ClientModel(this, serverIP);
        signInView = new SignInFinal(this);
        signInView.getFrameMemory();

        if (signInView != null) {
            signInView.setVisible(true);
            GUIUtils.setCentreScreen(signInView);
        }

    }

    private void readServerConfig() {
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
        } else if (id == -3) {
            this.reciveMessage("You already signed in !");
        } else {
            chatController = new ChatController(this);
            modelObj.changeState(0, id);
            userId = id;
            signInView.dispose();
            signInView = null;
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
        signInView.getFrameMemory();
        signInView.setVisible(true);
        modelObj = new ClientModel(this, serverIP);
    }

    public boolean signUp(User u) {
        boolean exist = modelObj.signUp(u);
        return exist;
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
        if (state == 0) {
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

    public void refreshGroups(Vector<Group> groups, Contact contactWhoChangedStatus) {
        chatController.refreshGroups(groups);
        if (contactWhoChangedStatus.getId() != ClientController.userId) {
            if (contactWhoChangedStatus.getStatus() == Contact.OFFLINE) {
                new Thread(new StatusNotifierFrame(contactWhoChangedStatus.getFname() + " " + contactWhoChangedStatus.getLname() + " signed out")).start();
            } else if (contactWhoChangedStatus.getStatus() == Contact.ONLINE) {
                new Thread(new StatusNotifierFrame(contactWhoChangedStatus.getFname() + " " + contactWhoChangedStatus.getLname() + " signed in ")).start();
            }
        }
    }

    public String[] getFriendRequest(int userId) {
        return modelObj.getFriendRequest(userId);
    }

    public void createGroupChat(Vector<Contact> groupContacts) {
        modelObj.createGroupChat(groupContacts);
    }

    public void reciveMessage(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message);
            }
        });
    }

    public void startGroupChat(Group newGroup) {
//        ChatController.groupsList.add(newGroup);
        chatController.openChatWindow(newGroup);
    }
}
