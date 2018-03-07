package com.bdjobs.training.firebasetest;

/**
 * Created by FIROZ HASAN on 3/4/2018.
 */

public class Users {
    public Users(){}

    public Users(String useme, String pasrd) {
        this.up = useme;
        this.pp = pasrd;

    }

    private String up;
    private String pp;



    public String getUsername() {
        return up;
    }

    public void setUsername(String useme) {
        this.up = useme;
    }

    public String getPassword() {
        return pp;
    }

    public void setPassword(String pasrd) {
        this.pp = pasrd;
    }
}
