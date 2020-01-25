package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 3/6/2019.
 */

public class UserPersionalInfoInsertModel {
    public String activeId;
    public String Name, Email, Father_name,Cast, Gender, DOB, Address, City, District, State, Mobile;

    public UserPersionalInfoInsertModel() {

    }
    public UserPersionalInfoInsertModel( String Name, String Email, String Father_name,String Cast, String Gender, String DOB, String Address, String City, String District, String State, String Mobile) {
        this.Name = Name;
        this.Email = Email;
        this.Father_name = Father_name;
        this.Cast = Cast;
        this.Gender = Gender;
        this.DOB = DOB;
        this.Address = Address;
        this.City = City;
        this.District = District;
        this.State = State;
        this.Mobile = Mobile;
    }
}
