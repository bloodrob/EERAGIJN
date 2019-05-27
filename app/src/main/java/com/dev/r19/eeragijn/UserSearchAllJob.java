package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

public class UserSearchAllJob extends AppCompatActivity {

    //firebase database variable;
    private FirebaseDatabase dataB;
    private DatabaseReference refDataB;
    //firebase storage
    private FirebaseStorage strMyFile;
    private StorageReference refTostrMyFile;
    //button
    private ListView listOfAllSearchJob;
    //list to add the data
    private List<String> addListOfAllJob;
    //arrayAdaptor to get the list item data
    private ArrayAdapter<String> getAddListOfAllJob;
    // Progress Dialog to show the progress
    private ProgressDialog pd11;
    //static string to take the selected item list
    static String nameOfJob;
    // string to take the url
    private String takeMyUrl12, takeMyUrl;
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
        pd11.setMessage("Searching...., Please wait.");
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
                addListOfAllJob.add(JobMod.Jobname+"\n"+"\nJob Subject  : "+JobMod.JobSubject +"\n\nJob Details  : "+JobMod.JobDetails +"\n\n Click here to check the full job details advertisedment."+"\n\n\n\n");
                getAddListOfAllJob = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addListOfAllJob);
                listOfAllSearchJob.setAdapter(getAddListOfAllJob);
                //action after choosing a list item
                listOfAllSearchJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //take the selected string
                        String myString = parent.getItemAtPosition(position).toString().trim();
                        //spli the string to get the required substring and strore in a string variable
                        String[] splitString = myString.split("\n");
                        nameOfJob = splitString[0];
                        Toast.makeText(UserSearchAllJob.this, "You select :"+nameOfJob, Toast.LENGTH_LONG).show();
                        // method to get the url
                        getTheSelectFileUrl();
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
    // function to get the url
    private void getTheSelectFileUrl() {
        DatabaseReference ref11 = FirebaseDatabase.getInstance().getReference("UploadedJobDetails");
        ref11.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
             JobUploadDetailsModel findUrl2 = dataSnapshot.getValue(JobUploadDetailsModel.class);
                if (nameOfJob.equals(findUrl2.Jobname)) {
                    // url string to open pdf file in any pdf reader
                    takeMyUrl12 = findUrl2.MyFileUrl.toString().trim();
                    // split the url string and take the required url string to downoad the file
                    String[] takeMyUrl123 = takeMyUrl12.split("\\?");
                    takeMyUrl = takeMyUrl123[0];
                   // Toast.makeText(UserSearchAllJob.this, "Downloading......", Toast.LENGTH_LONG).show();
                    Toast.makeText(UserSearchAllJob.this, "Url is :"+takeMyUrl, Toast.LENGTH_LONG).show();
                    // method to retrive the pdf file
                    getMyPdfFile();
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
    // retrive the pdf file function
    private void getMyPdfFile() {
        strMyFile = FirebaseStorage.getInstance();
        refTostrMyFile = strMyFile.getReference("Uploaded Job Pdf/" +nameOfJob +".pdf");
        // handling the i/o file exception
        try {
            //file object to create temp file with the filename
            File locFile = File.createTempFile(nameOfJob,".pdf");
            refTostrMyFile.getFile(locFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(UserSearchAllJob.this, "Downloading.....", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e) {
            Toast.makeText(UserSearchAllJob.this, "Downloading failed.....", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        // work for download the pdf file by url and open it on a pdf reader
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(takeMyUrl12), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // intent for ceate chooser
        Intent myIntent = Intent.createChooser(intent, "Open File");
        try {
            startActivity(myIntent);
        }
        catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(UserSearchAllJob.this, "You need to install a pdf reader", Toast.LENGTH_LONG).show();
        }
    }
}
