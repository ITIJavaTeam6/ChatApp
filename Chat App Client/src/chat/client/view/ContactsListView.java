package chat.client.view;

import chat.client.controller.ChatController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ContactsListView extends JFrame {

    private JPanel contentPane;
    ChatController chatController;

    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem10;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem11;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem12;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem9;

    /**
     * Create the frame.
     */
    public ContactsListView(ChatController chatController) {

        // Menu ..
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem9 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem10 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem11 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem12 = new javax.swing.JRadioButtonMenuItem();

        jMenu1.setText("Themes");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("Live");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("Easy");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem3);

        jRadioButtonMenuItem5.setSelected(true);
        jRadioButtonMenuItem5.setText("Natural");
        jRadioButtonMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem5);

        jRadioButtonMenuItem8.setSelected(true);
        jRadioButtonMenuItem8.setText("Pure");
        jRadioButtonMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem8);

        jRadioButtonMenuItem10.setSelected(true);
        jRadioButtonMenuItem10.setText("Classic");
        jRadioButtonMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem10ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem10);

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText("Milk And Honey");
        jRadioButtonMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem7);

        jRadioButtonMenuItem9.setSelected(true);
        jRadioButtonMenuItem9.setText("Adventure");
        jRadioButtonMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem9);

        jRadioButtonMenuItem12.setSelected(true);
        jRadioButtonMenuItem12.setText("Media");
        jRadioButtonMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem12);

        jRadioButtonMenuItem6.setSelected(true);
        jRadioButtonMenuItem6.setText("Music");
        jRadioButtonMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem6);

        jRadioButtonMenuItem11.setSelected(true);
        jRadioButtonMenuItem11.setText("Streems");
        jRadioButtonMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem11);

        jRadioButtonMenuItem4.setSelected(true);
        jRadioButtonMenuItem4.setText("Default");
        jRadioButtonMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem4);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Dark");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jRadioButtonMenuItem2);

        jMenuBar1.add(jMenu1);

        this.chatController = chatController;

        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setResizable(false);
        setBounds(screenWidth - 400, 90, 300, 600);
        setMinimumSize(new Dimension(300, 600));
        setMaximumSize(new Dimension(400, 700));

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        ContactsPanel contactsView = new ContactsPanel(chatController);
        contentPane.add(contactsView, BorderLayout.CENTER);

        UserPanel userPanel = new UserPanel();
        contentPane.add(userPanel, BorderLayout.NORTH);

        ButtonsPanel buttonsPanel = new ButtonsPanel();
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatController.unregister();
            }
        });
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
        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(6);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(5);
        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(7);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(8);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(9);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(10);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(11);

        // TODO add your handling code here:
    }

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        chatController.setLookAndFeel(12);

        // TODO add your handling code here:
    }
}
