package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserProfileUpdate extends AppCompatActivity {

    // for avitivity view variable
    private EditText userName;
    private EditText eMail;
    private Spinner myLatsQuali;
    private EditText myMobile;
    private Button submitInfo;
    private String LatestQualification;
    private String Email;
    private String Mobile;
    private String Name;
    //to store list of district
    List<String> preferList, latestQualiList;
    //array adaptor for list
    ArrayAdapter<String> getPreferList1, getLatestQualiList;
    //firebase variable
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseUser user;
    //id to set the current userId to the firebase database
    private String getMyId;
    public static final String TAG = UserProfileUpdate.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_update);
        // Initialization acvitivity var
        initActivityVar();
        setAndgetLatestQualiList();
        //end

        //Firebase Connection
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UserProfile");
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            getMyId = user.getUid();
        }
        submitMyInfo();
        // Start method for submit information
    }
    private void submitMyInfo(){
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = userName.getText().toString().trim();
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(UserProfileUpdate.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Email = eMail.getText().toString().trim();
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(UserProfileUpdate.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Mobile = myMobile.getText().toString().trim();
                if (TextUtils.isEmpty(Mobile)) {
                    Toast.makeText(UserProfileUpdate.this, "Mobile no need to be add", Toast.LENGTH_SHORT).show();
                    return;
                }


                // method declare for submit info
                UserPerInfoCreate();
            }
        });
    }
        // Crete and ref id set to the database
    private void UserPerInfoCreate() {
                UserProfileUpdateModel upInfo = new UserProfileUpdateModel();
                upInfo.setActiveId(getMyId);
                upInfo.setName(Name);
                upInfo.setEmail(Email);
                upInfo.setLatestQualification(LatestQualification);
                upInfo.setMobile(Mobile);
                ref.child(getMyId).setValue(upInfo);
                UserPerInfoAdd();
    }
    //add data to the database
    private void UserPerInfoAdd() {
                ref.child(getMyId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserProfileUpdateModel upInfo = dataSnapshot.getValue(UserProfileUpdateModel.class);
                        if (upInfo == null) {
                            Log.e(TAG, "Data is null");
                            return;
                        }
                        Log.e(TAG, "Data is Successfully inserted to the database");
                        Toast.makeText(UserProfileUpdate.this, "Data is Successfully Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserProfileUpdate.this, SubscribeToAChannel.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(UserProfileUpdate.this, "Somethimg Error in your database", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
    private void initActivityVar() {
        userName = (EditText) findViewById(R.id.user_name);
        eMail = (EditText)findViewById(R.id.email);
        myLatsQuali = (Spinner)findViewById(R.id.latsQuali);
        myMobile = (EditText)findViewById(R.id.mobile);
        submitInfo = (Button) findViewById(R.id.submit_persional_info);
    }
    private void setAndgetLatestQualiList() {
        //adding data ino cast list
        latestQualiList = new ArrayList<String>();
        latestQualiList.add("Under Matric");
        latestQualiList.add("HSLC");
        latestQualiList.add("HS");
        latestQualiList.add("Graduation");
        latestQualiList.add("PostGraduation");
        latestQualiList.add("Engineering");
        latestQualiList.add("Civil Enginnering");
        latestQualiList.add("Phd");
        // use of arrayadaptor of cats list
        getLatestQualiList = new ArrayAdapter<String>(UserProfileUpdate.this, android.R.layout.simple_spinner_item, latestQualiList);
        getLatestQualiList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        myLatsQuali.setAdapter(getLatestQualiList);
        // get the selecteed value
        myLatsQuali.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              LatestQualification   = parent.getItemAtPosition(position).toString().trim();
              Toast.makeText(UserProfileUpdate.this, "you select :"+LatestQualification, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserProfileUpdate.this, "Please Select a Cast", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }
}
