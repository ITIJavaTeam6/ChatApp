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
    Vector<Contact> contacts;
    int id;
}