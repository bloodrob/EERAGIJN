package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 3/6/2019.
 */

public class UserProfileUpdateModel {
    private String ActiveId;
    private String LatestQualification;
    private String Email;
    private String Mobile;
    private String Name;

    public UserProfileUpdateModel() {

    }
    public UserProfileUpdateModel(String ActiveId, String Name, String Email, String LatestQualification, String Mobile) {
        this.ActiveId = ActiveId;
        this.Name = Name;
        this.Email = Email;
        this.LatestQualification = LatestQualification;
        this.Mobile = Mobile;
    }

    public String getActiveId() {
        return ActiveId;
    }
    public void setActiveId(String ActiveId) {
        this.ActiveId = ActiveId;
    }

    public String getLatestQualification() {
        return LatestQualification;
    }
    public void setLatestQualification(String LatestQualification) {
        this.LatestQualification = LatestQualification;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email){
        this.Email = Email;
    }

    public String getMobile() {
        return Mobile;
    }
    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getName() {
        return Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }
}
