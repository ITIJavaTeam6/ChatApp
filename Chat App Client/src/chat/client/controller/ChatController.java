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
import chat.data.model.Contact;
import chat.data.model.Group;
import chat.data.model.Message;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
    File receivedFile;
    int choice;
    int fileChoice;
    Map<Integer, ChatWindow> chatWindows = new HashMap<>();
    public static Vector<Group> groupsList = new Vector<>();

    public ChatController(ClientController clientController) {
        this.setLookAndFeel(getLookAndFeel());
        this.clientController = clientController;
        contactsListView = new ContactsListView(this, clientController);
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
            openChatWindow(group);
            displayMessage(msg, group);
        }
    }

    public void openChatWindow(Group group) {
        Integer groupid = Integer.valueOf(group.getId());
        if (!chatWindows.containsKey(groupid)) {
            ChatWindow chatWindow = new ChatWindow(group, this);
            chatWindows.put(groupid, chatWindow);
            chatWindow.setVisible(true);
            chatWindow.requestFocus();
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
        try {
            openChatWindow(group);
            ChatWindow chatWindow = chatWindows.get(group.getId());

            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    choice = JOptionPane.showConfirmDialog(chatWindow, "Would you like to receive " + fileNameString + " ?");
                }
            });

            if (choice == JOptionPane.YES_OPTION) {
                JFileChooser fileChooser = new JFileChooser(fileNameString);
//            System.out.println("showing file chooser");
                SwingUtilities.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        fileChoice = fileChooser.showSaveDialog(chatWindow);
                    }
                });

                if (fileChoice == JFileChooser.APPROVE_OPTION) {
                    System.out.println("got file place");
                    receivedFile = fileChooser.getSelectedFile();
                    return true;
                }
            }
            return false;
        } catch (InterruptedException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

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

        try {
            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbulid.parse(new File("ClientConfig.xml"));
            Element root = doc.getDocumentElement();
            NodeList themes = root.getElementsByTagName("theme");
            Element e = (Element) themes.item(0);
            e.setTextContent(i + "");

            StreamResult sr = new StreamResult(new File("ClientConfig.xml"));
            Source src = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(src, sr);

        } catch (TransformerException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SAXException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getLookAndFeel() {
        String index = "1";
        try {
            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbulid.parse(new File("ClientConfig.xml"));
            Element root = doc.getDocumentElement();
            NodeList themes = root.getElementsByTagName("theme");
            Element e = (Element) themes.item(0);
            index = e.getTextContent();
        } catch (SAXException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(index);
    }

    public void sendFile(File f, Group group, boolean accepted, RMIClientInterface receiver) {
        if (!accepted) {
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
                        Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            fileInputStream.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();
        }
    }

    public void receiveFile(byte[] fileContent, Group g) {
        new Thread() {

            @Override
            public void run() {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(receivedFile);
                    fileOutputStream.write(fileContent);
                    fileOutputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(ChatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    public void sendFilePermission(File f, Group group, int receiverid, int senderid) {
        clientController.sendFilePermission(f, group, receiverid, senderid);
    }

    void refreshGroups(Vector<Group> groups) {
        groupsList = groups;
        contactsListView.refreshGroups(groups);
    }
}
