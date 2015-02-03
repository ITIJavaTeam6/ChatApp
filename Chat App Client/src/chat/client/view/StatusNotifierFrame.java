/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusNotifierFrame.java
 * Created on 02-02-2015 06:44:19 AM
 */
package chat.client.view;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Lupate
 *
 */

/*
new Thread calles in change status for contact 
 just call it such as: 

 /////////////////////////changeContactStatus(){
    if (contact.getStatus() == "Offline" || contact.getStatus() == "SignOut") {
        new Thread(new StatusNotifierFrame(contact.getName() + " signed out")).start();
    } else {
        new Thread(new StatusNotifierFrame(contact.getName() + " is " + contact.getStatus().getName())).start();
    }
 }

 */

public class StatusNotifierFrame extends javax.swing.JDialog implements Runnable {

    /**
     * Creates new form StatusNotifierFrame
     */
    public StatusNotifierFrame(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        //SwingUtilities3.
        SwingUtilities.updateComponentTreeUI(jLabel1);
        TimerTask tt = new TimerTask() {

            @Override
            public void run() {
                StatusNotifierFrame.this.dispose();
                this.cancel();
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 3000, 6000);
    }

    public StatusNotifierFrame(String string) {
        this(null, false);

        jLabel1.setText(string);

//        this.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 240, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 90, 240, 75);
        //jLabel1.setIcon(new ImageIcon(new ImageIcon("notifier.png").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST)));
        this.setVisible(true);

    }

    @Override
    public void run() {
        this.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 240, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 90, 240, 75);
        for (int i = 0; i < 120; i += 5) {

            this.setBounds((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 240, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - i, 240, 75);
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(StatusNotifierFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StatusNotifierFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StatusNotifierFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StatusNotifierFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StatusNotifierFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread(new StatusNotifierFrame("Hello")).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jLabel1.setFont(new java.awt.Font("Andalus", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
