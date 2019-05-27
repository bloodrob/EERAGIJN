package com.dev.r19.eeragijn;

/**
 * Created by R19 on 5/27/2019.
 */

public class AdminLoginModel {
    public String UniqueId, UserName, PassWord;
    public AdminLoginModel() {
    }
    public AdminLoginModel(String UniqueId, String UserName, String PassWord) {
        this.UniqueId = UniqueId;
        this.UserName = UserName;
        this.PassWord = PassWord;
    }
}
