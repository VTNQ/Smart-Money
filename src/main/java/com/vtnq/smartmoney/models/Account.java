package com.vtnq.smartmoney.models;

public class Account {
    private int ID;
    public String Username;
    private String Password;
    public String FullName;
    private String HashCode;
    private String OTP;
    private int AccountType;

    public Account() {
    }

    public Account(int ID, String username, String password, String fullName, String hashCode, String OTP, int accountType) {
        this.ID = ID;
        Username = username;
        Password = password;
        FullName = fullName;
        HashCode = hashCode;
        this.OTP = OTP;
        AccountType = accountType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getHashCode() {
        return HashCode;
    }

    public void setHashCode(String hashCode) {
        HashCode = hashCode;
    }

    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    public int getAccountType() {
        return AccountType;
    }

    public void setAccountType(int accountType) {
        AccountType = accountType;
    }
}
