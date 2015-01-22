package chatApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class UserPanel extends JPanel {
	public UserPanel() {
		this.setPreferredSize(new Dimension(200, 50));
		this.setLayout(new BorderLayout());
		
		JLabel img = new JLabel(new ImageIcon("res/male.png"));
		this.add(img, BorderLayout.WEST);
		
		
		JLabel name = new JLabel("user name");

		JLabel status = new JLabel("user name");
		status.setForeground(new Color(200, 200, 200));
		
		JPanel nameAndStatusPanel = new JPanel(new BorderLayout());
		nameAndStatusPanel.add(name, BorderLayout.NORTH);
		nameAndStatusPanel.add(status, BorderLayout.SOUTH);
		
		this.add(nameAndStatusPanel, BorderLayout.CENTER);
		
		String[] state = {"Available", "Busy", "Offline"};
		JComboBox<String> stateComboBox = new JComboBox<String>(state);
		this.add(stateComboBox,BorderLayout.EAST);
	}
}
