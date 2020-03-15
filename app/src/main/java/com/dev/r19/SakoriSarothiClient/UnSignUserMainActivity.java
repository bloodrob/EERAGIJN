package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class UnSignUserMainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    private RecyclerView recyclerView1;
    private List<UnSignUserMainActivityModel> myJobList = new ArrayList<>();
    private RecyclerView.Adapter myJobListAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_sign_user_main);
        // init activity var
        initActivityVar();
        // firebase
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("UploadedJobDetails");
        //search job
        searchJobList();
    }
    private void searchJobList() {
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
              //  for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
                    UnSignUserMainActivityModel unSignMod = dataSnapshot.getValue(UnSignUserMainActivityModel.class);
                    myJobList.add(unSignMod);
             //   }

                myJobListAdaptor = new UnsignUserActivityAdapterView(UnSignUserMainActivity.this, (ArrayList<UnSignUserMainActivityModel>) myJobList);
                recyclerView1.setAdapter(myJobListAdaptor);
               /* listOfJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //check current user status
                        checkCurrentUserStatus();
                    }
                });*/
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UnSignUserMainActivity.this, "Database Error, Please re-Install the app", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void checkCurrentUserStatus() {
        //check current user status
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(UnSignUserMainActivity.this, UserHome.class);
            startActivity(intent);
        }
    }

    private void initActivityVar() {
        ;recyclerView1 = (RecyclerView)findViewById(R.id.unsign_user_recycler);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(UnSignUserMainActivity.this));
    }

    public void onBackPressed(){
        Intent intent = new Intent(UnSignUserMainActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
