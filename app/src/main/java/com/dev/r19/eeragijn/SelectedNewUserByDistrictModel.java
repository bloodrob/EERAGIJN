package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/8/2019.
 */

public class SelectedNewUserByDistrictModel {
    public String Name, Email, Gender, Address, City, District, Mobile;

    public SelectedNewUserByDistrictModel(){

    }
    public SelectedNewUserByDistrictModel(String Name, String Email, String Gender, String Address, String City, String District, String Mobile) {
        this.Name = Name;
        this.Email = Email;
        this.Gender = Gender;
        this.Address =Address;
        this.City = City;
        this.District = District;
        this.Mobile = Mobile;
    }
}
