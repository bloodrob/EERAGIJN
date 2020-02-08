package com.dev.r19.SakoriSarothiClient;

public class UnSignUserMainActivityModel {
    private String JobName;
    private String LastDate;

    public UnSignUserMainActivityModel(){

    }

    public UnSignUserMainActivityModel(String JobName, String LastDate) {
        this.JobName = JobName;
        this.LastDate = LastDate;
    }
    public String getJobName (){
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
