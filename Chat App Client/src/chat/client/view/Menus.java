/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.view;

/**
 *
 * @author Lupate
 */
public class Menus extends javax.swing.JFrame {

    /**
     * Creates new form Menus
     */
    public Menus() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        themesMenuItem = new javax.swing.JMenu();
        easyTheme = new javax.swing.JCheckBoxMenuItem();
        liveTheme = new javax.swing.JCheckBoxMenuItem();
        naturalTheme = new javax.swing.JCheckBoxMenuItem();
        naturalTheme1 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme2 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme3 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme4 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme5 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme6 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme7 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme8 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme9 = new javax.swing.JCheckBoxMenuItem();
        naturalTheme10 = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jEditorPane1);

        jButton1.setText("jButton1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        themesMenuItem.setText("Themes");

        easyTheme.setSelected(true);
        easyTheme.setText("Live");
        easyTheme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyThemeActionPerformed(evt);
            }
        });
        themesMenuItem.add(easyTheme);

        liveTheme.setSelected(true);
        liveTheme.setText("Easy");
        themesMenuItem.add(liveTheme);

        naturalTheme.setSelected(true);
        naturalTheme.setText("Natural");
        themesMenuItem.add(naturalTheme);

        naturalTheme1.setSelected(true);
        naturalTheme1.setText("Pure");
        themesMenuItem.add(naturalTheme1);

        naturalTheme2.setSelected(true);
        naturalTheme2.setText("Classic");
        themesMenuItem.add(naturalTheme2);

        naturalTheme3.setSelected(true);
        naturalTheme3.setText("Milk and Honey");
        themesMenuItem.add(naturalTheme3);

        naturalTheme4.setSelected(true);
        naturalTheme4.setText("Advanture");
        themesMenuItem.add(naturalTheme4);

        naturalTheme5.setSelected(true);
        naturalTheme5.setText("Media");
        themesMenuItem.add(naturalTheme5);

        naturalTheme6.setSelected(true);
        naturalTheme6.setText("Music");
        themesMenuItem.add(naturalTheme6);

        naturalTheme7.setSelected(true);
        naturalTheme7.setText("Streams");
        themesMenuItem.add(naturalTheme7);

        naturalTheme8.setSelected(true);
        naturalTheme8.setText("Default");
        themesMenuItem.add(naturalTheme8);

        naturalTheme9.setSelected(true);
        naturalTheme9.setText("Dark");
        themesMenuItem.add(naturalTheme9);

        naturalTheme10.setSelected(true);
        naturalTheme10.setText("Nimbuss");
        themesMenuItem.add(naturalTheme10);

        jMenuBar1.add(themesMenuItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void easyThemeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyThemeActionPerformed
        
    }//GEN-LAST:event_easyThemeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem easyTheme;
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JCheckBoxMenuItem liveTheme;
    private javax.swing.JCheckBoxMenuItem naturalTheme;
    private javax.swing.JCheckBoxMenuItem naturalTheme1;
    private javax.swing.JCheckBoxMenuItem naturalTheme10;
    private javax.swing.JCheckBoxMenuItem naturalTheme2;
    private javax.swing.JCheckBoxMenuItem naturalTheme3;
    private javax.swing.JCheckBoxMenuItem naturalTheme4;
    private javax.swing.JCheckBoxMenuItem naturalTheme5;
    private javax.swing.JCheckBoxMenuItem naturalTheme6;
    private javax.swing.JCheckBoxMenuItem naturalTheme7;
    private javax.swing.JCheckBoxMenuItem naturalTheme8;
    private javax.swing.JCheckBoxMenuItem naturalTheme9;
    private javax.swing.JMenu themesMenuItem;
    // End of variables declaration//GEN-END:variables
}
