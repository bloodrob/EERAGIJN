package com.dev.r19.SakoriSarothiClient;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;


public class UserSearchCustomJobDetails extends AppCompatActivity {

    // button variable declare
    private EditText nameJob;
    private EditText categoryJob;
    private EditText lastDateJob;
    private EditText stipenSalaryJob;
    private EditText experienceJob;
    private EditText detailJob;
    private Button checkAd;
    //to take the string from other page
    private String selectedName;
    // to take the url of the selected file
    static String selectUrl;
    // progress dialogue
    private ProgressDialog pd1;

    // firebase variable for data
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private StorageReference refToStorF;
    private FirebaseStorage firStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job_details);
        // init activity var
        initActivityVar();
        // init of Progress bar
        pd1 = new ProgressDialog(this);
        pd1.setCanceledOnTouchOutside(false);
        pd1 = ProgressDialog.show(this, "Searching...", "Please Wait");
        // init of firebase variable
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");
        Intent intent1 = getIntent();
        if ("cusSerJob".equals(intent1.getStringExtra("id"))) {
            selectedName = intent1.getStringExtra("nameJob");
        }
        if ("allSerJob".equals(intent1.getStringExtra("id"))) {
            selectedName = intent1.getStringExtra("nameOfJob");
        }
        if ("notiSerJob".equals(intent1.getStringExtra("id"))) {
            selectedName = intent1.getStringExtra("title");
        }
        Toast.makeText(UserSearchCustomJobDetails.this, "name is :"+selectedName, Toast.LENGTH_LONG).show();
        //method of data retrive
        searchJobDetailData();
        //button func for pdf retrieve
        checkAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method for getting the pdf url
                getTheJobPdf();
            }
        });

    }
    // mathod for retrieve data
    private void searchJobDetailData() {
        pd1.show();
        //search data
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //pd dismiss
                pd1.dismiss();
                UserSearchCustomJobDetailsModel res1 = dataSnapshot.getValue(UserSearchCustomJobDetailsModel.class);
                if (selectedName.equals(res1.getJobName())) {
                    nameJob.setText(res1.getJobName());
                    categoryJob.setText(res1.getJobSubject());
                    lastDateJob.setText(res1.getLastDate());
                    stipenSalaryJob.setText(res1.getStiepndSalary());
                    experienceJob.setText(res1.getJobExperience());
                    detailJob.setText(res1.getJobDetails());
                    selectUrl = res1.getMyFileUrl().toString().trim();
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

            }
        });

    }
    // method for get the pdf file using url
    private void getTheJobPdf() {
        // initilization of firebase storagne and get ref
        // work to download the pdf file
        firStore = FirebaseStorage.getInstance();
        refToStorF = firStore.getReference().child("Uploaded Job Pdf").child(selectUrl +".pdf");
        // handling the i/o file exception
        try {
            //file object to create temp file with the filename
            File locFile = File.createTempFile("documents", "pdf");
            refToStorF.getFile(locFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // Toast.makeText(ViewAdminUploadedJobDetails.this, "Downloading....", Toast.LENGTH_LONG).show();
                }
            });
            refToStorF.getFile(locFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //  Toast.makeText(ViewAdminUploadedJobDetails.this, "Somethimg wrong, Please contact to the database administrator", Toast.LENGTH_LONG).show();
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
        intent.setDataAndType(Uri.parse(selectUrl), "application/pdf");
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
    private void initActivityVar() {
        nameJob = (EditText)findViewById(R.id.name_job);
        categoryJob = (EditText)findViewById(R.id.category_job);
        lastDateJob = (EditText)findViewById(R.id.last_date_job);
        stipenSalaryJob = (EditText)findViewById(R.id.stipen_salary_job);
        experienceJob = (EditText)findViewById(R.id.experience_job);
        detailJob = (EditText)findViewById(R.id.detail_job);
        checkAd = (Button) findViewById(R.id.check_ad);
    }
}
