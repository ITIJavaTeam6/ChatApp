/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.interfaces.RMIClientInterface;
import chat.client.model.RMIClientImpl;
import chat.client.view.ChatWindow;
import chat.client.view.ContactsListView;
import chat.data.model.Group;
import chat.data.model.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author sharno
 */
public class ChatController {
    ClientController clientController;
    ContactsListView contactsListView;
    File receivedFile;
    Map<Integer, ChatWindow> chatWindows = new HashMap<>();
    
    public ChatController (ClientController clientController) {
        this.clientController = clientController;
        contactsListView = new ContactsListView(this,clientController);
        contactsListView.setVisible(true);
    }

    public void displayMessage(Message message, Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        if (chatWindows.containsKey(groupid)) {
            chatWindows.get(groupid).displayMessage(message);
        } else {
            System.out.println("opening new chat window");
            openChatWindow (group);
            displayMessage(message, group);
        }
    }
    
    public void displayMessage(String msg, Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        if (chatWindows.containsKey(groupid)) {
            chatWindows.get(groupid).displayMessage(msg);
        } else {
            System.out.println("opening new chat window");
            openChatWindow (group);
            displayMessage(msg, group);
        }
    }
    
    public void openChatWindow (Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        if (! chatWindows.containsKey(groupid)) {
            ChatWindow chatWindow = new ChatWindow(group, this);
            chatWindows.put(groupid, chatWindow);
            chatWindow.setVisible(true);
        } else {
            chatWindows.get(groupid).requestFocus();
        }
    }
    
    public void closeChatWindow (Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        chatWindows.get(groupid).dispose();
        chatWindows.remove(groupid);
    }

    public void sendMessage(Message message, Group group) {
        clientController.sendMessage(message, group);
    }
    
    public void unregister () {
        clientController.unregister();
    }

    void serverStopping() {
        for (Map.Entry<Integer, ChatWindow> entry : chatWindows.entrySet()) {
            entry.getValue().dispose();
        }
        chatWindows.clear();
        contactsListView.dispose();
    }

    void serverAnnounce(String message) {
        JOptionPane.showMessageDialog(contactsListView, message);
    }

    boolean displayReceiveFilePermission(String fileNameString, Group group) {
        openChatWindow(group);
        ChatWindow chatWindow = chatWindows.get(group.getId());
        int choice = JOptionPane.showConfirmDialog(chatWindow, "Would you like to receive " + fileNameString + " ?");
        if (choice == JOptionPane.YES_OPTION) {
            JFileChooser fileChooser = new JFileChooser(fileNameString);
            System.out.println("showing file chooser");
            int fileChoice = fileChooser.showSaveDialog(chatWindow);
            System.out.println("got choice");
            if (fileChoice == JFileChooser.APPROVE_OPTION) {
                System.out.println("got file place");
                receivedFile = fileChooser.getSelectedFile();
                return true;
            }
        }
        return false;
    }

    public void sendFilePermission(File f, Group group, int receiverid, int senderid) {
        clientController.sendFilePermission(f, group, receiverid, senderid);
    }
    
    public void sendFile (File f, Group group, boolean accepted, RMIClientInterface receiver) {
        if (! accepted) {
            displayMessage("Sending file was refused", group);
        } else {
            new Thread() {

                @Override
                public void run() {
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = new FileInputStream(f);
                        int length = fileInputStream.available();
                        byte[] b = new byte[length];
                        fileInputStream.read(b);
                        receiver.receiveFile(b, group);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fileInputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();

        }
    }
    
    public void receiveFile (byte [] fileContent, Group g) {
        new Thread(){

            @Override
            public void run() {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
                    fileOutputStream.write(fileContent);
                    fileOutputStream.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(RMIClientImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }.start();
    }
}
