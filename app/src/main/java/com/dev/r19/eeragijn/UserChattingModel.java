package com.dev.r19.eeragijn;

import java.util.Date;

/**
 * Created by R19 on 6/17/2019.
 */

public class UserChattingModel {
    public String chatText, chatUser;
    public long chatTime;
    public UserChattingModel(String chatUser, String chatText) {

    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getChatUser() {
        return chatUser;
    }

    public void setChatUser(String chatUser) {
        this.chatUser = chatUser;
    }

    public long getChatTime() {
        return chatTime;
    }

    public void setChatTime(long chatTime) {
        this.chatTime = chatTime;
    }

    public UserChattingModel(String chatText, String chatUser, long chatTime) {
        this.chatText = chatText;
        this.chatUser = chatUser;
        // initializing the current time
        this.chatTime = new Date().getTime();
    }
}

