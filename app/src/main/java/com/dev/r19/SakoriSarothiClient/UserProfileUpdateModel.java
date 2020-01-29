package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 3/6/2019.
 */

public class UserProfileUpdateModel {
    public String activeId;
    public String Preference;
    public String LatestQualification;
    public String AboutChoice;
    public String Email;
    public String Mobile;
    public String Name;

    public UserProfileUpdateModel() {

    }
    public UserProfileUpdateModel(String Name, String Email, String Preference, String LatestQualification, String AbouChoice, String Mobile) {
        this.Name = Name;
        this.Email = Email;
        this.Preference = Preference;
        this.AboutChoice = AbouChoice;
        this.LatestQualification = LatestQualification;
        this.Mobile = Mobile;
    }
}
