/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.view.ChatWindow;
import chat.client.view.ContactsListView;
import chat.data.model.Group;
import chat.data.model.Message;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sharno
 */
public class ChatController {
    ClientController clientController;
    Map<Integer, ChatWindow> chatWindows = new HashMap<>();
    
    public ChatController (ClientController clientController) {
        this.clientController = clientController;
        ContactsListView contactsListView = new ContactsListView(this);
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
    
    public void openChatWindow (Group group) {
        ChatWindow chatWindow = new ChatWindow(group);
        Integer groupid = Integer.valueOf(group.getId());
        chatWindows.put(groupid, chatWindow);
        chatWindow.setVisible(true);
    }

    void sendMessage(Message message, Group group) {
        clientController.sendMessage(message, group);
    }
}
