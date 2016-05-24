package com.byronginvest.modulecollections.data.bean;

import java.io.Serializable;

/**
 * Created by Gosha on 2016-05-24.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6702529497217076560L;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;
}
