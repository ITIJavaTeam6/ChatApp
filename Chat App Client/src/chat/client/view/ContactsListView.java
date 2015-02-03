package chat.client.view;

import chat.client.controller.ChatController;
import chat.client.controller.ClientController;
import chat.client.gui.util.GUIUtils;
import chat.data.model.Contact;
import chat.data.model.Group;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

@SuppressWarnings("serial")
public class ContactsListView extends JFrame {

    private JPanel contentPane;
    ChatController chatController;
    ClientController con;
    ContactsPanel contactsView;

    private final javax.swing.JMenuBar jMenuBar1;
    private final javax.swing.JMenu mnuMessanger;
    private final javax.swing.JMenuItem mnuExit;
    private final javax.swing.JMenuItem mnuSignOut;
    private final javax.swing.JMenu mnuContacts;
    private final javax.swing.JMenu mnuThemes;
//    private final javax.swing.JMenu mnuHelp;
    private final javax.swing.ButtonGroup buttonGroup1;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem10;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem11;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem12;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private final javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem9;

    /**
     * Create the frame.
     */
    public ContactsListView(ChatController chatController, ClientController clientcontroller) {
        this.chatController = chatController;
        con = clientcontroller;
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setResizable(false);
        setBounds(screenWidth - 400, 90, 250, 600);  //change here 300
        setMinimumSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(200, 700));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        contactsView = new ContactsPanel(chatController);
        contentPane.add(contactsView, BorderLayout.CENTER);

        UserPanel userPanel = new UserPanel(clientcontroller, this);
        contentPane.add(userPanel, BorderLayout.NORTH);

        ButtonsPanel buttonsPanel = new ButtonsPanel(con, this);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatController.unregister();
            }
        });

        jMenuBar1 = new javax.swing.JMenuBar();
        mnuMessanger = new javax.swing.JMenu();
        mnuExit = new javax.swing.JMenuItem();
        mnuSignOut = new javax.swing.JMenuItem();
        mnuContacts = new javax.swing.JMenu();
        mnuThemes = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem10 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem9 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem12 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem11 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();

        buttonGroup1.add(jRadioButtonMenuItem1);
        buttonGroup1.add(jRadioButtonMenuItem2);
        buttonGroup1.add(jRadioButtonMenuItem3);
        buttonGroup1.add(jRadioButtonMenuItem4);
        buttonGroup1.add(jRadioButtonMenuItem5);
        buttonGroup1.add(jRadioButtonMenuItem6);
        buttonGroup1.add(jRadioButtonMenuItem7);
        buttonGroup1.add(jRadioButtonMenuItem8);
        buttonGroup1.add(jRadioButtonMenuItem9);
        buttonGroup1.add(jRadioButtonMenuItem10);
        buttonGroup1.add(jRadioButtonMenuItem11);
        buttonGroup1.add(jRadioButtonMenuItem12);

        mnuMessanger.setText("Massenger");

        mnuExit.setText("Exit Account");
        mnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        mnuMessanger.add(mnuExit);

        mnuSignOut.setText("Sign out");
        mnuSignOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSignOutActionPerformed(evt);
            }
        });
        mnuMessanger.add(mnuSignOut);

        jMenuBar1.add(mnuMessanger);

        mnuThemes.setText("Themes");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Live");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("Easy");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem3);

        jRadioButtonMenuItem5.setSelected(true);
        jRadioButtonMenuItem5.setText("Natural");
        jRadioButtonMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem5ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem5);

        jRadioButtonMenuItem8.setSelected(true);
        jRadioButtonMenuItem8.setText("Pure");
        jRadioButtonMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem8ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem8);

        jRadioButtonMenuItem10.setSelected(true);
        jRadioButtonMenuItem10.setText("Classic");
        jRadioButtonMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem10ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem10);

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText("Milk And Honey");
        jRadioButtonMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem7ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem7);

        jRadioButtonMenuItem9.setSelected(true);
        jRadioButtonMenuItem9.setText("Adventure");
        jRadioButtonMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem9ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem9);

        jRadioButtonMenuItem12.setSelected(true);
        jRadioButtonMenuItem12.setText("Media");
        jRadioButtonMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem12ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem12);

        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText("Music");
        jRadioButtonMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem6ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem6);

        jRadioButtonMenuItem11.setSelected(true);
        jRadioButtonMenuItem11.setText("Streems");
        jRadioButtonMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem11ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem11);

        jRadioButtonMenuItem4.setSelected(true);
        jRadioButtonMenuItem4.setText("Default");
        jRadioButtonMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem4ActionPerformed(evt);
            }
        });
        mnuThemes.add(jRadioButtonMenuItem4);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Dark");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        contactsView.add(jRadioButtonMenuItem2);

        jMenuBar1.add(mnuThemes);

//       contentPane.add(jMenuBar1);
        this.setJMenuBar(jMenuBar1);

        pack();
    }

    public static void main(String[] argv) {
        new ContactsListView(null, null).setVisible(true);

    }

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(1);
    }

    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(2);
    }

    private void jRadioButtonMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(3);
    }

    private void jRadioButtonMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(4);
    }

    private void jRadioButtonMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(6);
    }

    private void jRadioButtonMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(5);
    }

    private void jRadioButtonMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(7);
    }

    private void jRadioButtonMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(8);
    }

    private void jRadioButtonMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(9);
    }

    private void jRadioButtonMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(10);
    }

    private void jRadioButtonMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(11);
    }

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(12);
    }

    public void refreshGroups(Vector<Group> groups) {
        contactsView.refreshGroups(groups);
    }

    private void mnuExitActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.unregister();
        boolean exit = true;
        setIsExit(exit);
//        chatController.logout();
        System.exit(0);
    }

    private void mnuSignOutActionPerformed(java.awt.event.ActionEvent evt) {
        // unregister user from the server online users Map
        chatController.unregister();
        //false >> is Exit
        boolean exit = false;
        setIsExit(exit);
        // dispose this frame 
        chatController.logout();
    }

    private void setIsExit(boolean exit) {
        try {
            //        if (edit == null || !edit.isVisible()) {
//            edit = new EditInformation(controller);
//            GUIUtils.setCentreParent(edit, this);
//            edit.setVisible(true);
//        }
//        edit.requestFocus();
            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbulid.parse(new File("ClientConfig.xml"));
            Element root = doc.getDocumentElement();
            NodeList ifExit = root.getElementsByTagName("isExit");
            Element e = (Element) ifExit.item(0);
            if (exit) {
                e.setTextContent("true");
            } else {
                e.setTextContent("false");
            }

            StreamResult sr = new StreamResult(new File("ClientConfig.xml"));
            Source src = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(src, sr);

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ContactsListView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ContactsListView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactsListView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ContactsListView.class.getName()).log(Level.SEVERE, null, ex);
        }

    void createGroupChat(Vector<Contact> groupContacts) {
        con.createGroupChat(groupContacts);
    }

    public void refresh() {
        contactsView.refresh();
    }
}
