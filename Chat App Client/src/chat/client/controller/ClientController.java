/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.SignIn;

/**
 *
 * @author sharno
 */
public class ClientController {
    SignIn signInView;
    ClientModel model;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new ClientController();
    }
    
    public ClientController () {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                signInView = new SignIn();
                signInView.setVisible(true);
            }
        });
        
        model = new ClientModel();
    }
    
    
}
