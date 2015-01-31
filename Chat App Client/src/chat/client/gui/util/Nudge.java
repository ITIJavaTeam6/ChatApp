/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.gui.util;

import java.awt.Point;
import javax.swing.JFrame;

/**
 *
 * @author Lupate
 */
public class Nudge extends Thread {

    JFrame sender;
    AudioPlayer player;
    final String fileName = "chimes.wav";
    private static boolean locked = false;

    public Nudge(JFrame sender) {
        this.sender = sender;
        player = new AudioPlayer(fileName);
    }

    @Override
    public synchronized void run() {
        player.start();
        if (!locked) {
            locked = true;
            boolean flag = true;
            //Point p = sender.getLocation();
            //System.out.println(sender.getLocation().x + " , " + sender.getLocation().y);
            for (int i = 0; i < 10; i += 2) {
                try {
                    if (flag) {
                        sender.setLocation(sender.getLocation().x, sender.getLocation().y + i);
                        flag = false;
                    } else {
                        sender.setLocation(sender.getLocation().x + i, sender.getLocation().y);
                        flag = true;
                    }
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                //System.out.println(sender.getLocation().x + " , " + sender.getLocation().y);
            }
            for (int i = 0; i < 10; i += 2) {
                try {
                    if (flag) {
                        sender.setLocation(sender.getLocation().x - i, sender.getLocation().y);
                        flag = false;
                    } else {
                        sender.setLocation(sender.getLocation().x, sender.getLocation().y - i);
                        flag = true;
                    }
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
              //  System.out.println(sender.getLocation().x + " , " + sender.getLocation().y);
            }
            //sender.setLocation(p);
            //System.out.println(sender.getLocation().x + " , " + sender.getLocation().y);
            locked=false;
        }
    }
}
