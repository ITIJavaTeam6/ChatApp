/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.gui.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Lupate
 */
public class GUIUtils {

    public static Image logo = new ImageIcon("/logo.png").getImage();
    public static HashMap<Integer, ImageIcon> images = new HashMap<Integer, ImageIcon>();

    {

        images.put(0, new ImageIcon("offlineImg.png"));
        images.put(1, new ImageIcon("onlineImg.png"));
        images.put(2, new ImageIcon("busyImg.png"));
        images.put(3, new ImageIcon("awayImg.png"));
        images.put(4, new ImageIcon("offlineImg.png"));
        images.put(5, new ImageIcon("privacyImg.png"));
    }

    public static void setCentreScreen(JFrame frame) {
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - frame.getSize().width / 2, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - frame.getSize().height / 2);
    }

    public static void setCentreParent(JFrame frame, JFrame parent) {
        frame.setLocation(parent.getLocation().x + parent.getSize().width / 2 - frame.getWidth() / 2, parent.getLocation().y + parent.getSize().height / 2 - frame.getHeight() / 2 + 40);
    }

    public static void setDownParent(JFrame frame, JFrame parent) {
        frame.setLocation(parent.getLocation().x, parent.getLocation().y + parent.getSize().height);
    }

    public static void sendDownRightCoroner(JFrame frame) {
        frame.setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getSize().width, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getSize().height - 30);
    }
}
