/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.data.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author AmoOOOnA
 */

public class Contact implements Serializable{
    public static final int ONLINE = 0;
    public static final int OFFLINE = 1;
    public static final int BUSY = 2;
    public static final int AWAY = 3;
    
    public static final int MALE = 0;
    public static final int FEMALE = 1;

    private String email;
    private int id;
    private String fname;
    private String lname;
    private String pass;//TODO
    private Date birthdate;
    private int status;
    private int gender;

    public Contact() {
    }

    public Contact(String email, int id, String fname, String lname, String pass, Date birthdate, int status, int gender) {
        this.email = email;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.birthdate = birthdate;
        this.status = status;
        this.gender = gender;
    }
    
    public Contact(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contact) {
            return this.id == ((Contact)obj).id;
        } else {
            return false;
        }
    }
}
