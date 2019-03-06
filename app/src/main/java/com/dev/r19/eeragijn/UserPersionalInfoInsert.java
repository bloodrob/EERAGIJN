package com.dev.r19.eeragijn;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.telecom.DisconnectCause.LOCAL;

public class UserPersionalInfoInsert extends AppCompatActivity {

    private EditText userName, eMAIL, fatherName, dOB, aDDRESS, cITY, sTATE, dISTRICT, mOBILE;
    private RadioButton radioMale, radioFemale;
    private Button submitInfo;
    static String Gender;
    static Calendar myCalendar;
    static String DOB;
    //firebase variable
    private FirebaseDatabase database;
    private DatabaseReference ref;
    //id to set the current userId to the firebase database
    private String getId;
    public static final String TAG = UserPersionalInfoInsert.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_persional_info_insert);
        // Initialization edit text
        userName = (EditText) findViewById(R.id.user_name);
        eMAIL = (EditText) findViewById(R.id.email);
        fatherName = (EditText)findViewById(R.id.father);
        dOB = (EditText) findViewById(R.id.dob);
        // for calnedar
        dOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UserPersionalInfoInsert.this, Bdate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        aDDRESS = (EditText) findViewById(R.id.address);
        cITY = (EditText) findViewById(R.id.city);
        sTATE = (EditText) findViewById(R.id.state);
        dISTRICT = (EditText) findViewById(R.id.district);
        mOBILE = (EditText) findViewById(R.id.mobile);
        // initialization button
        submitInfo = (Button) findViewById(R.id.submit_persional_info);

        //Firebase Connection
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("NewUserPersionalInfo");
        // Start method for submit information
        submitInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = userName.getText().toString().trim();
                if (TextUtils.isEmpty(Name)) {
                    Toast.makeText(UserPersionalInfoInsert.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String Email = eMAIL.getText().toString().trim();
                String Father_name = fatherName.getText().toString().trim();
                String Address = aDDRESS.getText().toString().trim();
                String City = cITY.getText().toString().trim();
                String District = dISTRICT.getText().toString().trim();
                String State = sTATE.getText().toString().trim();
                String Mobile = mOBILE.getText().toString().trim();
                // method declare for submit info
                UserPerInfoCreate(Name, Email, Father_name, Gender, DOB, Address, City, District, State, Mobile);
            }
        });
    }
        // Crete and ref id set to the database
    private void UserPerInfoCreate(String Name, String Email, String Father_name, String Gender, String DOB, String Address, String City, String District, String State, String Mobile) {
                UserPersionalInfoInsertModel upInfo = new UserPersionalInfoInsertModel(Name,Email,Father_name, Gender, DOB, Address, City, District, State, Mobile);
                upInfo.activeId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                getId = upInfo.activeId;
                ref.child(getId).setValue(upInfo);
                UserPerInfoAdd();
    }
    //add data to the database
    private void UserPerInfoAdd() {
                ref.child(getId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserPersionalInfoInsertModel upInfo = dataSnapshot.getValue(UserPersionalInfoInsertModel.class);
                        if (upInfo == null) {
                            Log.e(TAG, "Data is null");
                            return;
                        }
                        Log.e(TAG, "Data is Successfully inserted to the database");
                        Toast.makeText(UserPersionalInfoInsert.this, "Data is Successfully Inserted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserPersionalInfoInsert.this, UserEducationQualificationInsert.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(UserPersionalInfoInsert.this, "Somethimg Error in your database", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
    }
    // method for rdioButton
    public void onRadioButtonClicked(View view) {
        //is radio button checked
        Boolean Checked = ((RadioButton) view).isChecked();
        // check whic button is clicked
        switch (view.getId()) {
            case (R.id.maleRadio) :
                if (Checked)
                    Gender = "Male";
                Toast.makeText(UserPersionalInfoInsert.this, "You Have SElected Male", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.femaleRadio) :
                if (Checked)
                    Gender = "Female";
                Toast.makeText(UserPersionalInfoInsert.this, "You Have Selected Female", Toast.LENGTH_SHORT).show();
                break;
        }
    } // end of radioButton
    // Method for datePickerDialog
    DatePickerDialog.OnDateSetListener Bdate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(myCalendar.YEAR, year);
            myCalendar.set(myCalendar.MONTH, month);
            myCalendar.set(myCalendar.DAY_OF_MONTH, dayOfMonth);
            UpdateLabel();
        }
    };
    private void UpdateLabel() {
        String myDateFormat = "MM/DD/YYYY";
        SimpleDateFormat sdf = new SimpleDateFormat(myDateFormat, Locale.ENGLISH);
        dOB.setText(sdf.format(myCalendar.getTime()));
        DOB = dOB.getText().toString().trim();
        Toast.makeText(UserPersionalInfoInsert.this, "Your selected date is :" +DOB, Toast.LENGTH_SHORT).show();
    }
}
