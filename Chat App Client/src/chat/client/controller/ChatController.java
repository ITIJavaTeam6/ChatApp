/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.view.ChatWindow;
import chat.client.view.ContactsListView;
import chat.data.model.Contact;
import chat.data.model.Group;
import chat.data.model.Message;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sharno
 */
public class ChatController {

    ClientController clientController;
    ContactsListView contactsListView;
    Map<Integer, ChatWindow> chatWindows = new HashMap<>();

    public ChatController(ClientController clientController) {
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
            openChatWindow(group);
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

    public void openChatWindow(Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        if (!chatWindows.containsKey(groupid)) {
            ChatWindow chatWindow = new ChatWindow(group, this);
            chatWindows.put(groupid, chatWindow);
            chatWindow.setVisible(true);
        } else {
            chatWindows.get(groupid).requestFocus();
        }
    }

    public void closeChatWindow(Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        chatWindows.get(groupid).dispose();
        chatWindows.remove(groupid);
    }

    public void sendMessage(Message message, Group group) {
        clientController.sendMessage(message, group);
    }

    public void unregister() {
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
        int choice = JOptionPane.showConfirmDialog(chatWindows.get(group.getId()), "Would you like to receive " + fileNameString + " ?");
        if (choice == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }
}
