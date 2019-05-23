package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserSearchAllJob extends AppCompatActivity {

    //firebase variable;
    private FirebaseDatabase dataB;
    private DatabaseReference refDataB;
    //button
    private ListView listOfAllSearchJob;
    //list to add the data
    private List<String> addListOfAllJob;
    //arrayAdaptor to get the list item data
    private ArrayAdapter<String> getAddListOfAllJob;
    // Progress Dialog to show the progress
    private ProgressDialog pd11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_all_job);
        //button initialization
        listOfAllSearchJob = (ListView) findViewById(R.id.list_off_all_search_job);
        //firebase database get instance and ref to the instance
        dataB = FirebaseDatabase.getInstance();
        refDataB = dataB.getReference("UploadedJobDetails");
        //initialize and set up progressdialog
        pd11 = new ProgressDialog(this);
        pd11.setTitle("Searching...., Please wait.");
        pd11.setCanceledOnTouchOutside(false);
        //initializing arraylist
        addListOfAllJob = new ArrayList<>();
        searchAllJob();
    }
    private void searchAllJob() {
        //start progress dialog
        pd11.show();
        refDataB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                JobUploadDetailsModel JobMod = dataSnapshot.getValue(JobUploadDetailsModel.class);
                // dissmissing the progress dialog
                pd11.dismiss();
                addListOfAllJob.add("Job Title  : "+JobMod.Jobname +"\n \n Job Subject  : "+JobMod.JobSubject +"\n\n Job Details  : "+JobMod.JobDetails +"\n\n\n\n");
                getAddListOfAllJob = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addListOfAllJob);
                listOfAllSearchJob.setAdapter(getAddListOfAllJob);
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
                Toast.makeText(UserSearchAllJob.this, "Database Error, Please contact to the database administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
