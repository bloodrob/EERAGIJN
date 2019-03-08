package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/8/2019.
 */

public class UserEducationQualificationInfoInsertModel {
    public String activeId;
    public String HS_board, HSLC_board, Graduation_board, Postgradutaion_university;
    public String HS_pass_year, HSLC_pass_year, Graduation_pass_year, Postgraduation_pass_year;
    public String HS_percantage, HSLC_percantage, Graduation_percantage, Postgradution_percantage;

    public UserEducationQualificationInfoInsertModel() {

    }

    public UserEducationQualificationInfoInsertModel(String HS_board, String HSLC_board, String Graduation_board, String Postgraduation_university, String HS_pass_year, String HSLC_pass_year, String Graduation_pass_year, String Postgraduation_pass_year, String HS_percantage, String HSLC_percantage, String Graduation_percantage, String Postgraduation_percantage) {
        this.HS_board = HS_board;
        this.HSLC_board = HSLC_board;
        this.Graduation_board = Graduation_board;
        this.Postgradutaion_university = Postgraduation_university;
        this.HS_pass_year = HS_pass_year;
        this.HSLC_pass_year = HSLC_pass_year;
        this.Graduation_pass_year = Graduation_pass_year;
        this.Postgraduation_pass_year = Postgraduation_pass_year;
        this.HS_percantage = HS_percantage;
        this.HSLC_percantage = HSLC_percantage;
        this.Graduation_percantage = Graduation_percantage;
        this.Postgradution_percantage = Postgraduation_percantage;
    }
}
