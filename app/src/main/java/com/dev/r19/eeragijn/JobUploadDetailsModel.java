package com.dev.r19.eeragijn;

/**
 * Created by R19 on 5/15/2019.
 */

public class JobUploadDetailsModel {
    public String Jobname, JobSubject, JobDetails;
    public JobUploadDetailsModel() {

    }
    public JobUploadDetailsModel(String JobName, String JobSubject, String JobDetails) {
        this.Jobname = JobName;
        this.JobSubject = JobSubject;
        this.JobDetails = JobDetails;
    }
}
