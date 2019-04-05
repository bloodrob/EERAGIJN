package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/7/2019.
 */

public class SearchNewUserByDistrictModel {
    public String activeId, District, Name, Email, Father_name, Gender, Caste,Address, City, State, Mobile, DOB;

    public SearchNewUserByDistrictModel(){

    }
    public SearchNewUserByDistrictModel(String District, String Name, String Email, String Father_name, String Gender, String Caste,String Address, String City, String State, String Mobile, String DOB) {
        this.District = District;
        this.Name = Name;
        this.Email = Email;
        this.Father_name = Father_name;
        this.Gender = Gender;
        this.Caste = Caste;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Mobile = Mobile;
        this.DOB = DOB;
    }
}
