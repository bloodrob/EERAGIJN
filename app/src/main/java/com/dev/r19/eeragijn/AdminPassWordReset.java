package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminPassWordReset extends AppCompatActivity {

    // button declare
    private EditText uniqueId, userName, curPass, newPass, conNewPass;
    private Button resetSubmit;
    //string to take the user entered data
    private String UserName, UniqueId, PassWord, NewPassWord,ConNewPassWord ;
    // firebase
    private FirebaseDatabase dataB;
    private DatabaseReference refToDatab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pass_word_reset);
        // initialization
        uniqueId = (EditText)findViewById(R.id.unique_id);
        userName = (EditText)findViewById(R.id.UserName);
        curPass = (EditText)findViewById(R.id.current_pass);
        newPass = (EditText)findViewById(R.id.pass_word);
        conNewPass = (EditText)findViewById(R.id.con_pass_word);
        resetSubmit = (Button)findViewById(R.id.admin_reset_pass);

        // connecting and gettings a instacne of the database and set the refference to that instance;
        dataB = FirebaseDatabase.getInstance();
        refToDatab = dataB.getReference("AdminLogin");
        //method define for resetSubmit
        resetSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting the entered data in a string
                //validate username
                UserName = userName.getText().toString().trim();
                // checking if the data is empty
                if (TextUtils.isEmpty(UserName)) {
                    Toast.makeText(AdminPassWordReset.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                // cheking if the entered data matched with database data
                Query forUserNAme = refToDatab.orderByChild("UserName").equalTo(UserName);
                forUserNAme.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                     if (dataSnapshot.hasChildren()) {

                     }
                     else {
                         Toast.makeText(AdminPassWordReset.this, "UserName not match", Toast.LENGTH_SHORT).show();
                         return;
                     }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // error message
                        Toast.makeText(AdminPassWordReset.this, "database error, Contact to the database administrators", Toast.LENGTH_LONG).show();
                        return;
                    }
                });
                //end of validating username
                // validate unique id
                UniqueId = uniqueId.getText().toString().trim();
                if (TextUtils.isEmpty(UniqueId)) {
                    Toast.makeText(AdminPassWordReset.this, "Unique id must be required", Toast.LENGTH_SHORT).show();
                    return;
                }
                //checking the entered unique id is matching with database data
                Query forUniqueId = refToDatab.orderByChild("UniqueId").equalTo(UniqueId);
                forUniqueId.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {

                        }
                        else {
                            Toast.makeText(AdminPassWordReset.this, "Unique Id not match", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // error message
                        Toast.makeText(AdminPassWordReset.this, "database error, Contact to the database administrators", Toast.LENGTH_LONG).show();
                        return;
                    }
                });
                // end of validating unique id
                //Validating passwrd
                PassWord = curPass.getText().toString().trim();
                if (TextUtils.isEmpty(PassWord)) {
                    Toast.makeText(AdminPassWordReset.this,"Current password required", Toast.LENGTH_SHORT).show();
                    return;
                }
                // cheking the entered password with database data
                Query forPassWord = refToDatab.orderByChild("PassWord").equalTo(PassWord);
                forPassWord.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChildren()) {
                        }
                        else {
                            Toast.makeText(AdminPassWordReset.this, "Current Password not Match with existing one", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // error message
                        Toast.makeText(AdminPassWordReset.this, "database error, Contact to the database administrators", Toast.LENGTH_LONG).show();
                        return;
                    }
                }); // end of validating password
                // for the new password
                NewPassWord = newPass.getText().toString().trim();
                ConNewPassWord = conNewPass.getText().toString().trim();
                if (TextUtils.isEmpty(NewPassWord) && TextUtils.isEmpty(ConNewPassWord)) {
                    Toast.makeText(AdminPassWordReset.this, "New Password and Confirm New Password required", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!NewPassWord.equals(ConNewPassWord)) {
                    Toast.makeText(AdminPassWordReset.this, "New and Confirm password not matching", Toast.LENGTH_LONG).show();
                    return;
                }
                // method for data update (password) in database
                UpdateNewPass();
            }
        });
    }
    //defining UpdateNewPass
    private void UpdateNewPass() {
        //String PassWord = NewPassWord.toString().trim();
        refToDatab.child("PassWord").setValue(NewPassWord);
        Toast.makeText(AdminPassWordReset.this, "Successfully update the password", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AdminPassWordReset.this, AdminMainActivity.class);
        startActivity(intent);
    }
}
