package chat.database.beans;

import java.io.Serializable;
import java.sql.*;

public class Contact implements Serializable{

    private long contactId;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
