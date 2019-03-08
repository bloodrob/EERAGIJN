package com.dev.r19.eeragijn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SelectedNewUserByDistrict extends AppCompatActivity {
    private Button searchEduQuali, searchUploadedDocument;
    private TextView viewName, viewEmail, viewGender, viewAddress, viewCity, viewDistrict, viewMobile;
    //Firebase varibale for create instance and get ref
    FirebaseDatabase database;
    DatabaseReference ref;
    //static variable to get a value from another java class
    static String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_new_user_by_district);
        //button initialize
        searchEduQuali = (Button)findViewById(R.id.search_edu_quali);
        searchUploadedDocument = (Button)findViewById(R.id.search_uploaded_doc);
        //textView initialization
        viewName = (TextView)findViewById(R.id.view_name);
        viewEmail = (TextView)findViewById(R.id.view_email);
        viewGender = (TextView)findViewById(R.id.view_gender);
        viewAddress = (TextView)findViewById(R.id.view_address);
        viewCity = (TextView)findViewById(R.id.view_city);
        viewDistrict = (TextView)findViewById(R.id.view_district);
        viewMobile = (TextView)findViewById(R.id.view_mobile);
        // creating instance of firebase and getting a ref
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("NewUserPersionalInfo");
        //search data of selected persion
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SelectedNewUserByDistrictModel selmod = dataSnapshot.getValue(SelectedNewUserByDistrictModel.class);
                if (selmod != null) {
                    if (selmod.Name.equals(selectedName)) {
                        viewName.setText(selmod.Name);
                        viewEmail.setText(selmod.Email);
                        viewGender.setText(selmod.Gender);
                        viewAddress.setText(selmod.Address);
                        viewCity.setText(selmod.City);
                        viewDistrict.setText(selmod.District);
                        viewMobile.setText(selmod.Mobile);
                    }
                }
                if (selmod == null) {
                    Toast.makeText(SelectedNewUserByDistrict.this, "Somthing happen, error occured. please re-search again", Toast.LENGTH_SHORT).show();
                    return;
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
                Toast.makeText(SelectedNewUserByDistrict.this, "Database error", Toast.LENGTH_SHORT).show();
                return;
            }
        });
        //link to education qalification search
        searchEduQuali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //link to UploadDocument search
        searchUploadedDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
