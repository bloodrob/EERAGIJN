package com.dev.r19.SakoriSarothiClient;

import android.widget.EditText;

public class UserFeedbackModel {
    private String FeedbackType;
    private String MessageFeedback;
    private String FeedbackPerson;

    public UserFeedbackModel(){
    }
    public UserFeedbackModel(String FeedbackType, String MessageFeedback, String FeedbackPerson) {
        this.FeedbackType = FeedbackType;
        this.MessageFeedback  = MessageFeedback;
        this.FeedbackPerson = FeedbackPerson;
    }
    public String getFeedbackType() {
        return FeedbackType;
    }
    public void setFeedbackType(String FeedbackType) {
        this.FeedbackType = FeedbackType;
    }
    public String getMessageFeedback() {
        return MessageFeedback;
    }
    public void setMessageFeedback(String MessageFeedback){
        this.MessageFeedback = MessageFeedback;
    }
    public String getFeedbackPerson() {
        return FeedbackPerson;
    }
    public void setFeedbackPerson(String FeedbackPerson){
        this.FeedbackPerson = FeedbackPerson;
    }
}
