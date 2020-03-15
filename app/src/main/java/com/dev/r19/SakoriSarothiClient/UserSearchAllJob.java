package com.dev.r19.SakoriSarothiClient;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

    private RecyclerView recyclerView2;
    private List<UserSearchAllJobModel> addListOfAllJob = new ArrayList<>();
    private RecyclerView.Adapter getAddListOfAllJob;
    // Progress Dialog to show the progress
    private ProgressDialog allJobPd;
    private String selectedNam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_all_job);
        //button initialization
        initUtilityVar();
        //firebase database get instance and ref to the instance
     //   FirebaseApp.initializeApp(this);
       // myApp = FirebaseApp.getInstance("[DEFAULT]");
        dataB = FirebaseDatabase.getInstance();
        refDataB = dataB.getReference("UploadedJobDetails");
        //initialize and set up progressdialog
        initPdbar();

        searchAllJob();
    }
    private void searchAllJob() {
        //start progress dialog
        allJobPd.show();
        refDataB.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.hasChildren()) {
                    UserSearchAllJobModel JobMod = dataSnapshot.getValue(UserSearchAllJobModel.class);
                    // dissmissing the progress dialog
                    allJobPd.dismiss();
                    addListOfAllJob.add(JobMod);

                    getAddListOfAllJob = new UserSearchAllJobAdapterView(UserSearchAllJob.this, (ArrayList<UserSearchAllJobModel>) addListOfAllJob);
                    recyclerView2.setAdapter(getAddListOfAllJob);
                }
                if (!dataSnapshot.hasChildren()){
                    allJobPd.dismiss();
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
                allJobPd.dismiss();
                Toast.makeText(UserSearchAllJob.this, "Database Error, Please contact to the database administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    private void initPdbar(){
        allJobPd =new ProgressDialog(this, R.style.CustomDialog);
        allJobPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        allJobPd.setTitle("searching...Please wait..");
        allJobPd.setCanceledOnTouchOutside(false);
    }

    private void initUtilityVar(){
        recyclerView2 = (RecyclerView)findViewById(R.id.all_job_recycler);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(UserSearchAllJob.this));
    }
}
