package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 1/12/2020.
 */

public class UserGetNameModel {
    private String Name;
    public UserGetNameModel() {

    }
    public UserGetNameModel(String Name) {
        this.Name = Name;
    }
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
}
