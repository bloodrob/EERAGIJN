package com.dev.r19.eeragijn;

import java.util.Date;

/**
 * Created by R19 on 6/17/2019.
 */

public class UserChattingModel {
    public String NewChatid;
    public String ChatText, ChatUser;
    public long ChatTime;
    public UserChattingModel() {

    }

    public UserChattingModel(String ChatText, String ChatUser, long ChatTime) {
        this.ChatText = ChatText;
        this.ChatUser = ChatUser;
        this.ChatTime = ChatTime;
    }
}

