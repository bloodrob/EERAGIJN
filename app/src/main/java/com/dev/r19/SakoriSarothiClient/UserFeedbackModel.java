package com.dev.r19.SakoriSarothiClient;

import android.widget.EditText;

public class UserFeedbackModel {
    private String FeedbackCategory;
    private String MessageFeedback;
    private String FeedbackPerson;

    public UserFeedbackModel(){
    }
    public UserFeedbackModel(String FeedbackCategory, String MessageFeedback, String FeedbackPerson) {
        this.FeedbackCategory = FeedbackCategory;
        this.MessageFeedback  = MessageFeedback;
        this.FeedbackPerson = FeedbackPerson;
    }
    public String getFeedbackCategory() {
        return FeedbackCategory;
    }
    public void setFeedbackCategory(String FeedbackCategory) {
        this.FeedbackCategory = FeedbackCategory;
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
