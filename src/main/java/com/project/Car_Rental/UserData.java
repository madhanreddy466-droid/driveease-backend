package com.project.Car_Rental;

import jakarta.persistence.*;

@Entity
public class UserData {

    @Id   // Email is primary key
    private String uemail;

    private String ufname;
    private String ulname;
    private String password;
    private String phonenumber;

    // getters & setters
    public String getUemail() { return uemail; }
    public void setUemail(String uemail) { this.uemail = uemail; }

    public String getUfname() { return ufname; }
    public void setUfname(String ufname) { this.ufname = ufname; }

    public String getUlname() { return ulname; }
    public void setUlname(String ulname) { this.ulname = ulname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhonenumber() { return phonenumber; }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    // default constructor
    public UserData() {}

    // corrected constructor
    public UserData(String uemail, String ufname, String ulname, String password, String phonenumber) {
        this.uemail = uemail;
        this.ufname = ufname;
        this.ulname = ulname;
        this.password = password;
        this.phonenumber = phonenumber;
    }
}