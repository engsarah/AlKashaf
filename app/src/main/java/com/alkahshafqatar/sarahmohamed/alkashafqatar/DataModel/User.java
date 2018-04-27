package com.alkahshafqatar.sarahmohamed.alkashafqatar.DataModel;

import javax.inject.Inject;

public class User {

    String uid;
    String pwd;


    public User(String uid, String pwd) {
        this.uid = uid;
        this.pwd = pwd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
