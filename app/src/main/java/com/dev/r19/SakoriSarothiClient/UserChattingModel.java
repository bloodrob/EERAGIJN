package com.dev.r19.SakoriSarothiClient;

import java.util.Map;

/**
 * Created by R19 on 6/17/2019.
 */

public class UserChattingModel {
    private String ChatText;
    private String ChatUser;
   // private String ChatTime;
    private Map<String, String> ChatTime;
    UserChattingModel() {

    }

    public UserChattingModel(String ChatText, String ChatUser, Map<String, String> ChatTime) {
        this.ChatText = ChatText;
        this.ChatUser = ChatUser;
        this.ChatTime = ChatTime;
    }
    public String getChatUser() {
        return ChatUser;
    }
    public void setChatUser(String ChatUser) {
        this.ChatUser = ChatUser;
    }
    public String getChatText() {
        return ChatText;
    }
    public void setChatText(String ChatText) {
        this.ChatText = ChatText;
    }
    public Map<String, String> getChatTime(){
        return ChatTime;
    }
    public void setChatTime(Map<String, String> ChatTime) {
        this.ChatTime = ChatTime;
    }
}

