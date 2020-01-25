package com.dev.r19.SakoriSarothiClient;

/**
 * Created by R19 on 1/10/2020.
 */

public class UserSeachCustomJobModel {
    public String JobName, JobSubject,LastDate,JobExperience,StiepndSalary, JobDetails, MyFileUrl;
    public UserSeachCustomJobModel() {

    }
    public UserSeachCustomJobModel(String JobName, String JobSubject, String LastDate, String JobExperience, String StipendSalary, String JObDetails, String MyFileUrl) {
        this.JobName = JobName;
        this.JobSubject = JobSubject;
        this.LastDate = LastDate;
        this.JobExperience = JobExperience;
        this.StiepndSalary = StipendSalary;
        this.JobDetails = JObDetails;
        this.MyFileUrl = MyFileUrl;
    }
}
