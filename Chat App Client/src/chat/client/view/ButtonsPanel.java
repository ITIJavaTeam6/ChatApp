package chat.client.view;

import chat.client.controller.ClientController;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonsPanel extends JPanel {
        ClientController con;
    public ButtonsPanel(ClientController controller) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        con=controller;
        JButton addButton = new JButton(new ImageIcon("res/add.png"));
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                NewContact nc = new NewContact(null, true,con);
                nc.setVisible(true);
            }
        });
        this.add(addButton);
         JButton startGroup = new JButton(new ImageIcon("res/add.png"));
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                
            }
        });
        this.add(startGroup);

    }
}
