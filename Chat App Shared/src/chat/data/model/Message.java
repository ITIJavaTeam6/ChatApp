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

    private Contact sender;
    private Group group;
    private Date sendDate;
    private String text;
    
    public Message() {
        // TODO: make constructor with default values
    }
    
    public Message(Contact sender, Group group, String text) {
        this.sender = sender;
        this.group = group;
        // TODO: get date from database
        this.text = text;
    }
    
    public Contact getSender() {
        return sender;
    }

    public void setSender(Contact sender) {
        this.sender = sender;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
