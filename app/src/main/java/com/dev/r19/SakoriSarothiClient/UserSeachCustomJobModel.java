package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 1/10/2020.
 */

public class UserSeachCustomJobModel {
    private String JobName;
    private String LastDate;

    public UserSeachCustomJobModel() {

    }
    public UserSeachCustomJobModel(String JobName, String LastDate) {
        this.JobName = JobName;
        this.LastDate = LastDate;
    }
    public String getJobName() {
        return JobName;
    }
    public void setJobName(String JobName) {
        this.JobName = JobName;
    }
    public String getLastDate() {
        return LastDate;
    }

    public void setLastDate(String LastDate) {
        this.LastDate = LastDate;
    }
}
