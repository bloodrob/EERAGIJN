package com.dev.r19.SakoriSarothiClient;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        // query for search the data with required condition
       final Query query123 = dbRef.orderByChild("activeId").equalTo(getCurActId);
        // searching for data
        query123.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child123 : dataSnapshot.getChildren()) {
                        // taking database snapshot with respect to model class data
                     //   AdminPushDataModel exData = child123.getValue(AdminPushDataModel.class);
                        pb1.dismiss();
                     //   addExistUserData.add("Your Active Id  : "+exData.activeId +"\n\n Your Name  : "+exData.Ex_name +"\n\n Your Emp exchange id  :"+exData.Ex_EmpId +"\n\n Your DOB  : "+exData.Ex_dob +"\n\n Your Cast  : "+exData.Ex_caste);
                     //   getAddExistUserdata = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addExistUserData);
                     //   showExistUserData.setAdapter(getAddExistUserdata);
                    }
                }
                else {
                    pb1.cancel();
                    if (pb1 == null && pb1.isShowing()) {
                        pb1.dismiss();
                    }
                    showErrorData.setText("You have not update your profile, or You are not yet varified for the employee exchange number. If you are not yet update your profile then please update it or please wait for few more days.");
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserCheckStatus.this, "Something wrong, please contact to the database administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
