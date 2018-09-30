package com.example.sunil.safetrack2;
/**
 * Created by Sunil on 12/11/2017.
 */

public class Users {
    int _id ;
    String _username, _password, _email, _telephone1, _telephone2, _telephone3;
//empy constructor
    public  Users() {
    }
    //constructor
    public Users (int id, String username, String password, String email, String telephone1,
                  String telephone2, String telephone3) {
        this._id = id ;
        this._username = username;
        this._password = password;
        this._email = email;
        this._telephone1 = telephone1 ;
        this._telephone2 = telephone2;
        this._telephone3 = telephone3;
    }
    //getters and setters
    public int getID() {
        return this._id ;
    }
    public void setID (int id) {
        this._id = id ;
    }
    public String getUserName () {
        return this._username ;
    }
    public void setUserName(String username) {
        this._username = username;
    }
    public String getPassWord () {
        return this._password ;
    }
    public void setPassWord (String password) {
        this._password = password ;
    }
    public String getEmail (){
        return this._email ;
    }
    public void setEmail(String email) {
        this._email = email ;
    }
    public String getTelePhone1 () {
        return this._telephone1 ;
    }
    public void setTelePhone1 (String telephone1) {
        this._telephone1 = telephone1 ;
    }
    public String getTelePhone2 () {
        return this._telephone2 ;
    }
    public void setTelePhone2 (String telephone2) {
        this._telephone2 = telephone2 ;
    }
    public String getTelePhone3 () {
        return this._telephone3 ;
    }
    public void setTelePhone3 (String telephone3) {
        this._telephone3 = telephone3 ;
    }
}


