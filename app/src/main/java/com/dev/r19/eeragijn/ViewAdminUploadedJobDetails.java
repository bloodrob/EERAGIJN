package com.dev.r19.eeragijn;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class ViewAdminUploadedJobDetails extends AppCompatActivity {

    private TextView jobTitle, subjectOfJob, detailsOfjob;
    private Button downloadJobPdf;
    // FireBase to use in search
    private FirebaseDatabase database;
    private DatabaseReference ref;
    // storage instance and ref tdownload the file
    private FirebaseStorage storF;
    private StorageReference refToStorF;
    // String to get the url of the the file
    private String myUrlToFile;
    // string to get the name of the file
    private String MyFileName;
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
                    myUrlToFile = jobMod.MyFileUrl.toString().trim();
                    MyFileName = jobMod.Jobname.toString().trim();
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
        // initilization of firebase storagne and get ref
        // work to download the pdf file
        storF = FirebaseStorage.getInstance();
        refToStorF = storF.getReference("Uploaded Job Details/"+MyFileName +".pdf");
        // handling the i/o file exception
        try {
            //file object to create temp file with the filename
            File locFile = File.createTempFile(MyFileName, ".pdf");
            refToStorF.getFile(locFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                }
            });
            refToStorF.getFile(locFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ViewAdminUploadedJobDetails.this, "Somethimg wrong, Please contact to the database administrator", Toast.LENGTH_LONG).show();
                    return;
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        } // end of download
        //Work for view the pdf document
        //using download url get the file and open it with any pdf reader thrgough intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(myUrlToFile), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //this intent used for the create chooser of open the file in the default application
        Intent myIntent1 = Intent.createChooser(intent, "Open File");
        // handling the I/o exception
        try {
            startActivity(myIntent1);
        }catch(Exception e) {
            e.printStackTrace();
        } // end of view
    }
}
