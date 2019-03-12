package com.dev.r19.eeragijn;

/**
 * Created by R19 on 3/12/2019.
 */

public class SelectedNewUserEduInfoByDistrictModel {
    public String HSLC_board, HSLC_pass_year, HSLC_percantage, activeId;

    public SelectedNewUserEduInfoByDistrictModel(){

    }
    public SelectedNewUserEduInfoByDistrictModel(String HSLC_board, String HSLC_pass_year, String HSLC_percantage, String activeId) {
        this.HSLC_board = HSLC_board;
        this.HSLC_pass_year = HSLC_pass_year;
        this.HSLC_percantage = HSLC_percantage;
        this.activeId = activeId;
    }
}
