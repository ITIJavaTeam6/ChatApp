/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.data.model;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author sharno
 */
public class Group implements Serializable {
    private Vector<Contact> contacts;
    private int id;

    public Vector<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Vector<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addContact (Contact contact) {
        contacts.add(contact);
    }
    
    public void removeContact (Contact contact) {
        contacts.remove(contact);
    }
}
