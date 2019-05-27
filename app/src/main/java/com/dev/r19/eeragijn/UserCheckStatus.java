package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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

public class UserCheckStatus extends AppCompatActivity {
    //firebase auth instance and get current firebase user
    private FirebaseAuth auth1;
    FirebaseUser user1;
    // string to get the current user id
    private String getCurActId;
    //Firebase database and database refference
    FirebaseDatabase dataB;
    DatabaseReference dbRef;
    // button
    private TextView showErrorData;
    private ListView showExistUserData;
    // list to add the data
    private List<String> addExistUserData;
    //arraydaptor to get the list item
    private ArrayAdapter<String> getAddExistUserdata;
    // Progress dialod to show the process
    private ProgressDialog pb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check_status);

        //initialization of button
        showErrorData = (TextView)findViewById(R.id.show_error_text);
        showExistUserData = (ListView)findViewById(R.id.show_exist_user_data);
        //initialization of list;
        addExistUserData = new ArrayList<>();
        //Initlization of progressbar
        pb1 = new ProgressDialog(this);
        pb1.setMessage("Searching, Please Wait.....");
        pb1.setCanceledOnTouchOutside(false);
        //firebase work for auth
        auth1 = FirebaseAuth.getInstance();
        user1 = auth1.getCurrentUser();
        if (user1 != null) {
            getCurActId = user1.getUid().toString().trim();
            Toast.makeText(UserCheckStatus.this, " Id is "+getCurActId, Toast.LENGTH_LONG).show();
        }
        // firbase initialization and get refference for database
        dataB = FirebaseDatabase.getInstance();
        dbRef = dataB.getReference("ExistingUserPersionalInfo");
        getTheDataOfUser();

    }
    private void getTheDataOfUser() {
        //showing the progress bar
        pb1.show();
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                // taking database snapshot with respect to model class data
               AdminPushDataModel exData = dataSnapshot.getValue(AdminPushDataModel.class);
                //if data is exist
                if (getCurActId.equals(exData.activeId)) {
                    pb1.dismiss();
                    addExistUserData.add("Your Active Id  : "+exData.activeId +"\n\n Your Name  : "+exData.Ex_name +"\n\n Your Emp exchange id  :"+exData.Ex_EmpId +"\n\n Your DOB  : "+exData.Ex_dob +"\n\n Your Cast  : "+exData.Ex_caste);
                    getAddExistUserdata = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addExistUserData);
                    showExistUserData.setAdapter(getAddExistUserdata);
                }
                //if data does not exist
                if (!addExistUserData.equals(exData.activeId) && addExistUserData.isEmpty()){
                    // dismissing the pb
                   // pb1.setCanceledOnTouchOutside(true);
                    pb1.cancel();
                    if (pb1 == null && pb1.isShowing()) {
                        pb1.dismiss();
                    }
                    showErrorData.setText("You are not yet varified to get an Emp Exchange Id, Your application is under Process, Please wait for few more days." +"\n" +"Thank you...");
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
                Toast.makeText(UserCheckStatus.this, "Dtabase Error, Please contact to the database Administrator", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
