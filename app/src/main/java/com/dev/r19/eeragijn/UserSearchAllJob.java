package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserSearchAllJob extends AppCompatActivity {

    //firebase database variable;
    private FirebaseDatabase dataB;
    private DatabaseReference refDataB;
    //firebase storage
    private FirebaseStorage strMyFile;
    private StorageReference refTostrMyFile;
    private FirebaseApp myApp;
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
     //   FirebaseApp.initializeApp(this);
       // myApp = FirebaseApp.getInstance("[DEFAULT]");
        dataB = FirebaseDatabase.getInstance();
        refDataB = dataB.getReference("UploadedJobDetails");
        //initialize and set up progressdialog
        pd11 = new ProgressDialog(this);
        pd11.setMessage("Searching...., Please wait.");
        pd11.setCanceledOnTouchOutside(false);
        //initializing arraylist
        addListOfAllJob = new ArrayList<>();
        searchAllJob();
    }
    private void searchAllJob() {
        //start progress dialog
       // pd11.show();
        refDataB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserSeachCustomJobModel JobMod = dataSnapshot.getValue(UserSeachCustomJobModel.class);
                // dissmissing the progress dialog
             //   pd11.dismiss();
                addListOfAllJob.add(JobMod.JobName+"\n"+"\nLast Date : "+JobMod.LastDate+"\n\n\n");
                getAddListOfAllJob = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addListOfAllJob);
                listOfAllSearchJob.setAdapter(getAddListOfAllJob);
                //action after choosing a list item
                listOfAllSearchJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Intent the data to another java file
                        Intent intent = new Intent(UserSearchAllJob.this, UserSearchCustomJobDetails.class);
                        //take the selected string
                        String myString = parent.getItemAtPosition(position).toString().trim();
                        //spli the string to get the required substring and strore in a string variable
                        String[] splitString = myString.split("\n");
                        UserSearchCustomJobDetails.selectedNane = splitString[0];
                        Toast.makeText(UserSearchAllJob.this, "You select :"+UserSearchCustomJobDetails.selectedNane, Toast.LENGTH_LONG).show();
                        startActivity(intent);
                    }
                });
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
