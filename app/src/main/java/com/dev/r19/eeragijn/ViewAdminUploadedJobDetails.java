package com.dev.r19.eeragijn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewAdminUploadedJobDetails extends AppCompatActivity {

    private TextView jobTitle, subjectOfJob, detailsOfjob;
    private Button downloadJobPdf;
    // FireBase to use in search
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_admin_uploaded_job_details);
        //initialization
        jobTitle = (TextView)findViewById(R.id.job_title);
        subjectOfJob = (TextView)findViewById(R.id.job_subject);
        detailsOfjob = (TextView)findViewById(R.id.job_details);
        downloadJobPdf = (Button)findViewById(R.id.download_job_pdf);
        // getting the string
        final String nameOfJob = AdminSendJobNotification.JobName.toString().trim();
        // firebase Initialization
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JobUploadDetailsModel jobMod = dataSnapshot.getValue(JobUploadDetailsModel.class);
                if (nameOfJob.equals(jobMod.Jobname)) {
                    jobTitle.setText(jobMod.Jobname);
                    subjectOfJob.setText(jobMod.JobSubject);
                    detailsOfjob.setText(jobMod.JobDetails);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewAdminUploadedJobDetails.this, "Something wrong, Contact to the database administrators", Toast.LENGTH_LONG).show();
            }
        });
        // end of firebase database
        //set onclick listener for pdf download
        downloadJobPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method to download pdf file
                downloadWork();
            }
        });
    }
    private void downloadWork() {

    }
}
