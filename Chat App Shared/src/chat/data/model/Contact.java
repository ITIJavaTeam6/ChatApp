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

    String email;
    int id;
    String fname;
    String lname;
    String pass;//TODO
    Date birthdate;
    statusenum status;
    genderenum gender;
    
}
