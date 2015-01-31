/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.data.model.conversion;

import chat.data.model.Contact;
import chat.database.beans.User;

/**
 *
 * @author sharno
 */
public class Converter {
    static public Contact fromUserToContact (User user) {
        Contact c = new Contact();
        
        c.setBirthdate(user.getBdate());
        c.setEmail(user.getEmail());
        c.setFname(user.getFname());
        c.setLname(user.getLname());
        c.setGender((int) user.getGender());
        c.setId((int) user.getIduser());
        c.setPass(user.getPassword());
        c.setStatus((int) user.getStatus());
        return c;
    }
}
