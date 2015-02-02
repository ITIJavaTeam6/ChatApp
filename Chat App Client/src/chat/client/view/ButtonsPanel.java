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
       // this.setLayout(new FlowLayout(FlowLayout.LEFT));
        con=controller;
       JButton jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/client/view/chat.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 345, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, Short.MAX_VALUE)
        );
        this.add(jButton1);
//        JButton addButton = new JButton(new ImageIcon("res/add.png"));
//        addButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent me) {
//                NewContact nc = new NewContact(null, true,con);
//                nc.setVisible(true);
//            }
//        });
//        this.add(addButton);
//         JButton startGroup = new JButton(new ImageIcon("res/add.png"));
//        addButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent me) {
//                
//            }
//        });
//        this.add(startGroup);

    }
}
