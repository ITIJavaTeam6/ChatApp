package chat.client.view;

import chat.client.controller.ChatController;
import chat.client.controller.ClientController;
import chat.data.model.Group;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ContactsListView extends JFrame {

    private JPanel contentPane;
    ChatController chatController;
    ClientController con;
    ContactsPanel contactsView;

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
    public ContactsListView(ChatController chatController, ClientController clientcontroller) {
        this.chatController = chatController;
        con = clientcontroller;
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

        contactsView = new ContactsPanel(chatController);
        contentPane.add(contactsView, BorderLayout.CENTER);

        UserPanel userPanel = new UserPanel();
        contentPane.add(userPanel, BorderLayout.NORTH);

        ButtonsPanel buttonsPanel = new ButtonsPanel(con);
        contentPane.add(buttonsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chatController.unregister();
            }
        });

    }
    public void refreshGroups(Vector<Group> groups) {
        contactsView.refreshGroups(groups);
    }
   
    
}