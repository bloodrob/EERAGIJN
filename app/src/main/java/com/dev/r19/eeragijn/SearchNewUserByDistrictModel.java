package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/7/2019.
 */

public class SearchNewUserByDistrictModel {
    public String District, Name, Email;

    public SearchNewUserByDistrictModel(){

    }
    public SearchNewUserByDistrictModel(String District, String Name, String Email) {
        this.District = District;
        this.Name = Name;
        this.Email = Email;
    }
}
