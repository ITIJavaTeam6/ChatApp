package chatApp;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ButtonsPanel extends JPanel {
	public ButtonsPanel() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton addButton = new JButton(new ImageIcon("res/add.png"));
                addButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent me) {
                            NewContact nc=new NewContact(null, true);
                            nc.setVisible(true);
			}
                });
		this.add(addButton);
		
		
	}
}
