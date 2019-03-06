package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/6/2019.
 */

public class UserPersionalInfoInsertModel {
    String activeId;
    String Name, Email, Father_name, Gender, DOB, Address, City, District, State, Mobile;

    public UserPersionalInfoInsertModel() {

    }
    public UserPersionalInfoInsertModel( String Name, String Email, String Father_name, String Gender, String DOB, String Address, String City, String District, String State, String Mobile) {
        this.Name = Name;
        this.Email = Email;
        this.Father_name = Father_name;
        this.Gender = Gender;
        this.DOB = DOB;
        this.Address = Address;
        this.City = City;
        this.District = District;
        this.State = State;
        this.Mobile = Mobile;
    }
}
