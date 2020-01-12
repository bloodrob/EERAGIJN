package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


public class UserSearchCustomJobDetails extends AppCompatActivity {

    // button variable declare
    private EditText nameJob, categoryJob, lastDateJob, stipenSalaryJob, experienceJob, detailJob, checkAd;
    //to take the string from other page
    static String selectedNane;
    // to take the url of the selected file
    static String selectUrl;
    // progress dialogue
    private ProgressDialog pd1;

    // firebase variable for data
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseStorage firStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job_details);
        // button init
        nameJob = (EditText)findViewById(R.id.name_job);
        categoryJob = (EditText)findViewById(R.id.category_job);
        lastDateJob = (EditText)findViewById(R.id.last_date_job);
        stipenSalaryJob = (EditText)findViewById(R.id.stipen_salary_job);
        experienceJob = (EditText)findViewById(R.id.experience_job);
        detailJob = (EditText)findViewById(R.id.detail_job);
        checkAd = (EditText)findViewById(R.id.check_ad);

        // init of firebase variable
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");
        // init of Progress bar
        pd1 = new ProgressDialog(this);
        pd1.setCanceledOnTouchOutside(false);
        pd1 = ProgressDialog.show(this, "Searching...", "Please Wait");

        Toast.makeText(UserSearchCustomJobDetails.this, "name is :"+selectedNane, Toast.LENGTH_LONG).show();
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
                UserSeachCustomJobModel res1 = dataSnapshot.getValue(UserSeachCustomJobModel.class);
                if (res1.JobName.equals(selectedNane)) {
                    nameJob.setText(res1.JobName);
                    categoryJob.setText(res1.JobSubject);
                    lastDateJob.setText(res1.LastDate);
                    stipenSalaryJob.setText(res1.StiepndSalary);
                    experienceJob.setText(res1.JobExperience);
                    detailJob.setText(res1.JobDetails);
                    selectUrl = res1.MyFileUrl.toString().trim();
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

    }
}
