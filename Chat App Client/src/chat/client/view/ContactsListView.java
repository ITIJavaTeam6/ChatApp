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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class ContactsListView extends JFrame {

    private JPanel contentPane;
    ChatController chatController;

    /**
     * Create the frame.
     */
    public ContactsListView(ChatController chatController) {
        this.chatController = chatController;

        int screenWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setResizable(false);
        setBounds(screenWidth-400, 90, 300, 600);
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
}
