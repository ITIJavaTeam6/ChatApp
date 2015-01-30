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
        contactsListView = new ContactsListView(this);
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

    /**
     * @param index changing Look&Feel from Theme Menu
     *
     */
    public void setLookAndFeel(int i) {
        String s = null;
        switch (i) {
            case 1:
                s = "com.jtattoo.plaf.luna.LunaLookAndFeel";
                break;
            case 2:
                s = "com.jtattoo.plaf.smart.SmartLookAndFeel";
                break;
            case 3:
                s = "com.jtattoo.plaf.noire.NoireLookAndFeel";
                break;
            case 4:
                s = "com.jtattoo.plaf.mint.MintLookAndFeel";
                break;
            case 5:
                s = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
                break;
            case 6:
                s = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
                break;
            case 7:
                s = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
                break;
            case 8:
                s = "com.jtattoo.plaf.fast.FastLookAndFeel";
                break;
            case 9:
                s = "com.jtattoo.plaf.bernstein.BernsteinLookAndFeel";
                break;
            case 10:
                s = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
                break;
            case 11:
                s = "com.jtattoo.plaf.aero.AeroLookAndFeel";
                break;
            case 12:
                s = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
                break;
        }
        try {
            UIManager.setLookAndFeel(s);
//            if (clientMain != null) {
//                SwingUtilities.updateComponentTreeUI(clientMain);
//            }

            if (contactsListView != null) {
                SwingUtilities.updateComponentTreeUI(contactsListView);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        try {
//            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = docbulid.parse(new File("ClientConfig.xml"));
//            Element root = doc.getDocumentElement();
//            NodeList themes = root.getElementsByTagName("theme");
//            Element e = (Element) themes.item(0);
//            e.setTextContent(i + "");
//
//            StreamResult sr = new StreamResult(new File("ClientConfig.xml"));
//            Source src = new DOMSource(doc);
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer t = tf.newTransformer();
//            t.transform(src, sr);
//
//        } catch (TransformerException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//
//        } catch (SAXException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    public int getLookAndFeel() {
//        String index = "1";
//        try {
//            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
//            Document doc = docbulid.parse(new File("ClientConfig.xml"));
//            Element root = doc.getDocumentElement();
//            NodeList themes = root.getElementsByTagName("theme");
//            Element e = (Element) themes.item(0);
//            index = e.getTextContent();
//        } catch (SAXException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ParserConfigurationException ex) {
//            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return Integer.parseInt(index);
//    }
}
