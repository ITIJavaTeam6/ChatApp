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
enum statusenum {

    online, offline, busy, awy
};

enum genderenum {

    female, male
};

public class Contact implements Serializable{

    private String email;
    private int id;
    private String fname;
    private String lname;
    private String pass;//TODO
    private Date birthdate;
    private statusenum status;
    private genderenum gender;

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

    public statusenum getStatus() {
        return status;
    }

    public void setStatus(statusenum status) {
        this.status = status;
    }

    public genderenum getGender() {
        return gender;
    }

    public void setGender(genderenum gender) {
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
