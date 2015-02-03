package chat.client.view;

import chat.client.controller.ChatController;
import chat.client.controller.ClientController;
import chat.data.model.Contact;
import chat.data.model.Group;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class ContactsPanel extends JScrollPane {

    JList<Group> list;
    int highlightIndex = -1;
    ChatController chatController;

    public ContactsPanel(ChatController chatController) {
        this.chatController = chatController;
        DefaultListModel<Group> listModel = new DefaultListModel<>();

        Contact c1 = new Contact();
        Contact c2 = new Contact();
        c1.setId(1);
        c2.setId(2);
        Group g1 = new Group();
        g1.addContact(c1);
        g1.addContact(c2);

        g1.setId(1);

        listModel.addElement(g1);

        list = new JList<>(listModel);

        // setting highlight index when mouse hovers on a menu item
        list.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                Point p = new Point(me.getX(), me.getY());
                highlightIndex = list.locationToIndex(p);
                list.repaint();
            }
        });

        list.addMouseListener(new MouseAdapter() {
            // double click event
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    chatController.openChatWindow(list.getSelectedValue());
                    list.setSelectedIndex(-1);
                }
            }

            // mouse exiting menu removes highlight
            @Override
            public void mouseExited(MouseEvent me) {
                highlightIndex = -1;
                list.repaint();
            }
        });

        list.setCellRenderer(new MyRenderer());

        setViewportView(list);
    }

    void refreshGroups(Vector<Group> groups) {
        for (Group group : groups) {
            System.err.println(group.getId());
        }
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                list.setListData(groups);
            }
        });
    }

    void refresh() {
        
    }

    class MyRenderer implements ListCellRenderer<Group> {

        @Override
        public Component getListCellRendererComponent(
                JList<? extends Group> list, Group value, int index,
                boolean isSelected, boolean cellHasFocus) {

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            
            String conversationName = new String();
            for (Contact contact : value.getContacts()) {
                if (contact.getId() != ClientController.userId) {
                    conversationName += contact.getFname() + " " + contact .getLname();
                    // add a coma if it's a group chat and not the last name
                    if (value.getContacts().size() > 2 && value.getContacts().indexOf(contact) != value.getContacts().size()-1) {
                        conversationName += ", ";
                    }
                }
            }
            JLabel name = new JLabel(conversationName);
            name.setForeground(Color.BLACK);
            name.setFont(getFont().deriveFont(Font.BOLD, 14));

            JLabel img = new JLabel(new ImageIcon("src/res/male.png"));

            JLabel state = null;
            if (value.getContacts().size() < 3) {
                for (Contact contact : value.getContacts()) {
                    if (contact.getId() != ClientController.userId) {
                        switch (contact.getStatus()) {
                            case Contact.ONLINE:
                                state = new JLabel(new ImageIcon("src/res/online.png"));
                                break;
                            case Contact.OFFLINE:
                                state = new JLabel(new ImageIcon("src/res/offline.png"));
                                break;
                            case Contact.BUSY:
                                state = new JLabel(new ImageIcon("src/res/busy.png"));
                                break;
                            case Contact.AWAY:
                                state = new JLabel(new ImageIcon("src/res/away.png"));
                                break;
                            default:
                                System.out.println("default");
                                state = new JLabel(new ImageIcon("src/res/offline.png"));
                        }
                    }
                }
                panel.add(state, BorderLayout.EAST);
            }
            
            
//			GroupLayout layout = new GroupLayout(panel);
//			panel.setLayout(layout);
//			
//			layout.setAutoCreateGaps(true);
//			layout.setAutoCreateContainerGaps(true);
//			layout.setHorizontalGroup(
//					layout.createSequentialGroup()
//					.addComponent(img)
//					//					      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
//					//					    		  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//							.addComponent(name)
//							.addComponent(status))
//					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//					.addComponent(state)
//			);
//			layout.setVerticalGroup(
//					layout.createSequentialGroup()
//					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//							.addComponent(img)
//							.addComponent(name)
//							.addComponent(state))
//					.addComponent(status)
//					.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//			);

            panel.setPreferredSize(new Dimension(100, 50));

            panel.add(img, BorderLayout.WEST);

            panel.add(name, BorderLayout.CENTER);
//						panel.add(new JLabel("hello there", JLabel.CENTER), BorderLayout.SOUTH);


            if (highlightIndex == index) {
                panel.setBackground(new Color(200, 200, 200));
            }
            if (isSelected) {
                panel.setBackground(new Color(150, 150, 255));
            }

            return panel;
        }

    }
}
