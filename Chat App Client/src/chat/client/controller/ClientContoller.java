/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client.controller;

import chat.client.model.ClientModel;
import chat.client.view.test;

/**
 *
 * @author sharno
 */
public class ClientContoller {
    test obj;
    ClientModel modelObj;
    public ClientContoller(test t){
        obj=t;
        modelObj=new ClientModel();
    }
    public void signIn(String email,String pass){
         System.out.println(modelObj.signIn(email, pass));
    }
    public void changeState(int state){
        modelObj.changeState(state, 3);
    }
    public static void main(String[] args) {
       
    }
    
}
