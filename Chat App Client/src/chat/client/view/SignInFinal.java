/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.view;

import chat.client.controller.ClientController;
import chat.client.gui.util.GUIUtils;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Lupate
 */
public class SignInFinal extends javax.swing.JFrame {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private boolean isReadyToSignIn = true;
    ClientController controller;

    /**
     * Creates new form SignInFinal
     */
    public SignInFinal(ClientController aThis) {
        this.controller = aThis;
        initComponents();
        this.setIconImage(GUIUtils.logo);
        getRootPane().setDefaultButton(jButton1);
        jPanel1.getRootPane().setDefaultButton(jButton1);
        getFrameMemory();
        jLabel1.setIcon(new ImageIcon(GUIUtils.logoString));
    }

    private SignInFinal() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chatto");
        setMinimumSize(new java.awt.Dimension(320, 710));
        setResizable(false);

        jPanel1.setMinimumSize(new java.awt.Dimension(250, 710));
        jPanel1.setPreferredSize(new java.awt.Dimension(125, 240));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setMinimumSize(new java.awt.Dimension(312, 150));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Chatto ID:");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password:");

        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
        });

        jCheckBox1.setText("Remember my ID ..");

        jCheckBox2.setText("Remember my password");

        jButton1.setText("Sign In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 255));
        jLabel4.setText("Get a new Chatto ID ..");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 255));
        jLabel5.setText("Forget your password? ");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1)
                                    .addComponent(jLabel3)
                                    .addComponent(jPasswordField1)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addGap(29, 29, 29)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        SignUp signUp = new SignUp(controller);
        signUp.setVisible(true);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jTextField1.getText().matches(EMAIL_PATTERN)) {
            showErrorMessage("Please enter a valid ID", "Invalid data");
            jTextField1.setText("");
            jTextField1.requestFocus();
            isReadyToSignIn = false;
        }
        if (jPasswordField1.getText().isEmpty()) {
            showErrorMessage("Please enter a password", "Empty password");
            jPasswordField1.setText("");
            jPasswordField1.requestFocus();
            isReadyToSignIn = false;
        }

        if (isReadyToSignIn) {
            System.out.println("User data is valid .. ready to sign in");
            setFrameMemory();
            controller.signIn(jTextField1.getText(), jPasswordField1.getText());

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        jTextField1.selectAll();
    }//GEN-LAST:event_jTextField1FocusGained

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        jPasswordField1.selectAll();
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered
        Cursor normalCursor = new Cursor(Cursor.HAND_CURSOR);
        setCursor(normalCursor);
    }//GEN-LAST:event_jLabel4MouseEntered

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        setCursor(normalCursor);
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered
        Cursor normalCursor = new Cursor(Cursor.HAND_CURSOR);
        setCursor(normalCursor);
    }//GEN-LAST:event_jLabel5MouseEntered

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited
        Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
        setCursor(normalCursor);
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        CallMyPassword callMyPassword = new CallMyPassword(controller);
        callMyPassword.setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    /**
     * @param args the command line arguments
     */

    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         
     try {
     for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
     if ("Nimbus".equals(info.getName())) {
     javax.swing.UIManager.setLookAndFeel(info.getClassName());
     break;
     }
     }
     } catch (ClassNotFoundException ex) {
     java.util.logging.Logger.getLogger(SignInFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (InstantiationException ex) {
     java.util.logging.Logger.getLogger(SignInFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (IllegalAccessException ex) {
     java.util.logging.Logger.getLogger(SignInFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     } catch (javax.swing.UnsupportedLookAndFeelException ex) {
     java.util.logging.Logger.getLogger(SignInFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
     }
     */
        //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    public void showErrorMessage(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public void setFrameMemory() {
        try {
            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbulid.parse(new File("ClientConfig.xml"));
            Element root = doc.getDocumentElement();
            if (jCheckBox1.isSelected()) {
                NodeList userid = root.getElementsByTagName("userID");
                Element e = (Element) userid.item(0);
                e.setTextContent(jTextField1.getText());

                NodeList remember = root.getElementsByTagName("rememberMe");
                Element e1 = (Element) remember.item(0);
                e1.setTextContent(jCheckBox1.isSelected() + "");

            } else {
                NodeList userid = root.getElementsByTagName("userID");
                Element e = (Element) userid.item(0);
                e.setTextContent("");

                NodeList remember = root.getElementsByTagName("rememberMe");
                Element e1 = (Element) remember.item(0);
                e1.setTextContent(jCheckBox1.isSelected() + "");
            }

            if (jCheckBox2.isSelected()) {
                NodeList pass = root.getElementsByTagName("password");
                Element e = (Element) pass.item(0);
                e.setTextContent(new String(jPasswordField1.getPassword()));

                NodeList rememberpass = root.getElementsByTagName("rememberPassword");
                Element e1 = (Element) rememberpass.item(0);
                e1.setTextContent(jCheckBox2.isSelected() + "");
            } else {
                NodeList pass = root.getElementsByTagName("password");
                Element e = (Element) pass.item(0);
                e.setTextContent("");

                NodeList rememberpass = root.getElementsByTagName("rememberPassword");
                Element e1 = (Element) rememberpass.item(0);
                e1.setTextContent(jCheckBox2.isSelected() + "");

            }

            StreamResult sr = new StreamResult(new File("ClientConfig.xml"));
            Source src = new DOMSource(doc);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(src, sr);

        } catch (ParserConfigurationException ex) {
            System.out.println("7a7aaaaaaaaaaaaaaaaaaaa");
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrr");
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            generateClientConfigXML();
        } catch (TransformerException ex) {
            System.out.println("7)00000000000000000000");
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getFrameMemory() {
        try {
            DocumentBuilder docbulid = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = docbulid.parse(new File("ClientConfig.xml"));
            Element root = doc.getDocumentElement();

            NodeList remember = root.getElementsByTagName("rememberMe");
            NodeList rememberpass = root.getElementsByTagName("rememberPassword");

            Element e1 = (Element) remember.item(0);
            Element e2 = (Element) rememberpass.item(0);

            if (e1 == null || e2 == null) {
                generateClientConfigXML();

            }

            if (Boolean.parseBoolean(e1.getTextContent())) {
                NodeList userid = root.getElementsByTagName("userID");
                Element e = (Element) userid.item(0);
                jTextField1.setText(e.getTextContent());
                jCheckBox1.setSelected(true);
            }

            if (Boolean.parseBoolean(e2.getTextContent())) {
                NodeList pass = root.getElementsByTagName("password");
                Element e = (Element) pass.item(0);
                jPasswordField1.setText(e.getTextContent());
                jCheckBox2.setSelected(true);
            }

        } catch (ParserConfigurationException ex) {
            System.out.println("mmmmmmmmmmmmmmmmmmmmm");
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxx");
            generateClientConfigXML();
        } catch (IOException ex) {
            generateClientConfigXML();
        }

        /*
         try {
         String data = " <?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><!--\n"
         + "    Document   : ClientConfig.xml\n"
         + "    Created on : ?\n"
         + "    Author     : Lupate\n"
         + "    Description:\n"
         + "        Purpose of the document follows.\n"
         + "--><root>\n"
         + "    <theme>9</theme>\n"
         + "    <userID>example@server.com</userID>\n"
         + "    <password>55555</password>\n"
         + "    <rememberMe>true</rememberMe>\n"
         + "    <rememberPassword>true</rememberPassword>\n"
         + "</root>";

         File file = new File("ClientConfig.xml");

         //if file doesnt exists, then create it
         if (!file.exists()) {
         file.createNewFile();
         }

         //true = append file
         FileWriter fileWritter = new FileWriter(file.getName(), true);
         BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
         bufferWritter.write(data);
         bufferWritter.close();

         System.out.println("Done appending xml ..");

         } catch (IOException e) {
         e.printStackTrace();
         }

         */
    }

    private void generateClientConfigXML() {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            //root 
            Document document = builder.newDocument();
            Element rootElement = document.createElement("root");
            document.appendChild(rootElement);

            //theme
            Element staffElement = document.createElement("theme");
            staffElement.appendChild(document.createTextNode("9"));
            rootElement.appendChild(staffElement);

            //userID
            Element fNameElement = document.createElement("userID");
            fNameElement.appendChild(document.createTextNode("Muhammad"));
            rootElement.appendChild(fNameElement);

            //pass
            Element lNameElement = document.createElement("password");
            lNameElement.appendChild(document.createTextNode("Edmerdash"));
            rootElement.appendChild(lNameElement);

            //rememberME
            Element nickname = document.createElement("rememberMe");
            nickname.appendChild(document.createTextNode("true"));
            rootElement.appendChild(nickname);

            //rememberPassword
            Element salary = document.createElement("rememberPassword");
            salary.appendChild(document.createTextNode("true"));
            rootElement.appendChild(salary);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "YES");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("ClientConfig.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("xml file saved..!");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(SignInFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
