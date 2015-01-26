/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.data.model;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author AmoOOOnA
 */
public class Message implements Sendable {

    Contact sender;
    Vector<Contact> group = new Vector<Contact>();
    Date sendDate;
    int groupid;
    String text;
}
