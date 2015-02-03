/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.data.model;

import java.awt.Color;
import java.awt.Font;
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

    //Add by Lupate
    Font messageFont;
    Color textClor;
    //

    public Message() {
        // TODO: make constructor with default values
    }

    public Message(Contact sender, Group group, String text) {
        this.sender = sender;
        this.group = group;
        // TODO: get date from database
        this.text = text;
    }

    //Added By Lupate
    // to add the text format to the messagr itself from the sender to receiver 
    public Message(Contact sender, Group group, Date sendDate, String text, Font messageFont, Color textClor) {
        this.sender = sender;
        this.group = group;
        this.sendDate = sendDate;
        this.text = text;
        this.messageFont = messageFont;
        this.textClor = textClor;
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

    // by Lupate
    public Font getMessageFont() {
        return messageFont;
    }

    // by Lupate
    public void setMessageFont(Font messageFont) {
        this.messageFont = messageFont;
    }

    // by Lupate
    public Color getTextClor() {
        return textClor;
    }
    
    // by Lupate
    public void setTextClor(Color textClor) {
        this.textClor = textClor;
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
