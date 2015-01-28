package chat.database.beans;

import java.sql.*;

public class User {

    public User() {
    }

    public User(long iduser, String fname, String lname, String password, Timestamp bdate, long status, long gender, Timestamp cdate, String email) {
        this.iduser = iduser;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.bdate = bdate;
        this.status = status;
        this.gender = gender;
        this.cdate = cdate;
        this.email = email;
    }
    
    public User(String fname, String lname, String password, Timestamp bdate, long status, long gender, Timestamp cdate, String email) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.bdate = bdate;
        this.status = status;
        this.gender = gender;
        this.cdate = cdate;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "iduser=" + iduser + ", fname=" + fname + ", lname=" + lname + ", password=" + password + ", bdate=" + bdate + ", status=" + status + ", gender=" + gender + ", cdate=" + cdate + ", email=" + email + '}';
    }

    private long iduser;

    public long getIduser() {
        return iduser;
    }

    public void setIduser(long iduser) {
        this.iduser = iduser;
    }

    private String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    private String lname;

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Timestamp bdate;

    public Timestamp getBdate() {
        return bdate;
    }

    public void setBdate(Timestamp bdate) {
        this.bdate = bdate;
    }

    private long status;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    private long gender;

    public long getGender() {
        return gender;
    }

    public void setGender(long gender) {
        this.gender = gender;
    }

    private Timestamp cdate;

    public Timestamp getCdate() {
        return cdate;
    }

    public void setCdate(Timestamp cdate) {
        this.cdate = cdate;
    }

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
