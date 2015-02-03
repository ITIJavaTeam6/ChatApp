package chat.client.view;

import chat.client.controller.ClientController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends JPanel {

    
    ClientController con;
    JComboBox stateComboBox ;
    ContactsListView contactsListView;
    public UserPanel(ClientController c, ContactsListView contactsListView) {
        this.contactsListView = contactsListView;
        con = c;
         JPanel   jPanel1 = new javax.swing.JPanel();
         JPanel jPanel2 = new javax.swing.JPanel();
        stateComboBox  = new javax.swing.JComboBox();
        JButton jButton1 = new javax.swing.JButton();
        JButton jButton6 = new javax.swing.JButton();
        JButton jButton5 = new javax.swing.JButton();
        JButton jButton2 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(102, 0, 102));

        stateComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Online", "Offline", "Busy" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/client/view/setting.png"))); // NOI18N

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/client/view/add.png"))); 
        jButton6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                NewContact nc = new NewContact(null, true,con);
                nc.setVisible(true);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/client/view/remove .png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton5ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chat/client/view/group_1.png"))); // NOI18N
//        jButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                new StartGroupChat(contactsListView, true).setVisible(true);
//            }
//        });

        jButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
               String[] x = con.getFriendRequest(ClientController.userId);
            if (x != null) {
                for (int i = 0; i < x.length; i++) {
                    con.receiveAdd(x[i]);
                }
            }
            }
        });
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(stateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, Short.MAX_VALUE)
        );

//        this.setPreferredSize(new Dimension(200, 50));
//        this.setLayout(new BorderLayout());
//
//        JLabel img = new JLabel(new ImageIcon("res/male.png"));
//        this.add(img, BorderLayout.WEST);
//
//        JLabel name = new JLabel("user name");
//
//        JLabel status = new JLabel("user name");
//        status.setForeground(new Color(200, 200, 200));
//
//        JPanel nameAndStatusPanel = new JPanel(new BorderLayout());
//        nameAndStatusPanel.add(name, BorderLayout.NORTH);
//        nameAndStatusPanel.add(status, BorderLayout.SOUTH);
//
//        this.add(nameAndStatusPanel, BorderLayout.CENTER);
//
        stateComboBox.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stateComboBoxActionPerformed(evt);
            }
        });
//        this.add(stateComboBox, BorderLayout.EAST);
    }

    private void stateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        String state = stateComboBox.getSelectedItem().toString();
        System.out.println("here in comboBox");
        if (state.equals("Online")) {
            con.changeState(0);
        }
        if (state.equals("Offline")) {
            con.changeState(1);
        }
        if (state.equals("Busy")) {
            con.changeState(2);
        }
    }
}
