package com.dev.r19.eeragijn;

/**
 * Created by R19 on 4/3/2019.
 */

public class AdminPushDataModel {
    public String activeId,Ex_name,Ex_email,Ex_fatherName,Ex_caste,Ex_gender,Ex_dob,Ex_address, Ex_district,Ex_city,Ex_state,Ex_mobile;

    public AdminPushDataModel(){
    }

    public AdminPushDataModel(String ex_name, String ex_email, String ex_fatherName, String ex_caste, String ex_gender, String ex_dob, String ex_address, String ex_district, String ex_city, String ex_state, String ex_mobile) {
        Ex_name = ex_name;
        Ex_email = ex_email;
        Ex_fatherName = ex_fatherName;
        Ex_caste = ex_caste;
        Ex_gender = ex_gender;
        Ex_dob = ex_dob;
        Ex_address = ex_address;
        Ex_district = ex_district;
        Ex_city = ex_city;
        Ex_state = ex_state;
        Ex_mobile = ex_mobile;
    }
}
