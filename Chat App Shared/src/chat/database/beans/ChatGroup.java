package chat.database.beans;

import java.sql.*;

public class ChatGroup {

    private long idgroup;

    public long getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(long idgroup) {
        this.idgroup = idgroup;
    }

    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
