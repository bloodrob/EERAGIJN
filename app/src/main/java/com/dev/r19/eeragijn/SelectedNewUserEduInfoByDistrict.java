package com.dev.r19.eeragijn;

import android.content.Intent;
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

public class SelectedNewUserEduInfoByDistrict extends AppCompatActivity {
    private TextView viewEduInfo;
    private TextView viewHslcBoard, viewHslcPassYear, viewHslcPercantage, viewHsBoard, viewHsPassYear, viewHsPercantage, viewGraduate, viewGraduatePassYear, viewGraduatePercantage, viewPostGraduate, viewPostGraduatePassYear, viewPostGraduatePercantage;
    private Button viewPerInfo, viewUploadDoc;
    // static string for intent string value from selectednewuserbydistrict
    static String getActiveId;
    //static string to get the email and useit in another java class
    static String useEmailIdForAll;
    //Firebase variable
    private FirebaseDatabase database;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_new_user_edu_info_by_district);
        // initialization
        viewEduInfo = (TextView)findViewById(R.id.view_edu_info);
        viewPerInfo = (Button)findViewById(R.id.view_per_info);
        viewUploadDoc = (Button)findViewById(R.id.view_upload_doc);
        viewHslcBoard = (TextView)findViewById(R.id.view_hslc_board);
        viewHslcPassYear = (TextView)findViewById(R.id.view_hslc_pass_year);
        viewHslcPercantage = (TextView)findViewById(R.id.view_hslc_percantage);
        viewHsBoard = (TextView)findViewById(R.id.view_hs_board);
        viewHsPassYear = (TextView)findViewById(R.id.view_hs_pass_year);
        viewHsPercantage = (TextView)findViewById(R.id.view_hs_percantage);
        viewGraduate = (TextView)findViewById(R.id.view_graduate);
        viewGraduatePassYear = (TextView)findViewById(R.id.view_graduate_pass_year);
        viewGraduatePercantage = (TextView)findViewById(R.id.view_graduate_percantage);
        viewPostGraduate = (TextView)findViewById(R.id.view_postgraduate);
        viewPostGraduatePassYear = (TextView)findViewById(R.id.view_postgraduate_pass_year);
        viewPostGraduatePercantage = (TextView)findViewById(R.id.view_postgraduate_percantage);

        // Creating instance and getting ref to the instance of firebase database
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("NewUserEducationQualification");
        // search of data
                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    //On database search success
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        UserEducationQualificationInfoInsertModel edumod = dataSnapshot.getValue(UserEducationQualificationInfoInsertModel.class);
                        if (edumod.activeId.equals(getActiveId)) {
                            viewHslcBoard.setText(edumod.HSLC_board);
                            viewHslcPassYear.setText(edumod.HSLC_pass_year);
                            viewHslcPercantage.setText(edumod.HSLC_percantage +"%");
                            viewHsBoard.setText(edumod.HS_board);
                            viewHsPassYear.setText(edumod.HS_pass_year);
                            viewHsPercantage.setText(edumod.HS_percantage +"%");
                            viewGraduate.setText(edumod.Graduation_college);
                            viewGraduatePassYear.setText(edumod.Graduation_pass_year);
                            viewGraduatePercantage.setText(edumod.Graduation_percantage +"%");
                            viewPostGraduate.setText(edumod.Postgraduation_university_name);
                            viewPostGraduatePassYear.setText(edumod.Postgraduation_pass_year);
                            viewPostGraduatePercantage.setText(edumod.Postgradution_percantage +"%");
                        }
                        if (! edumod.activeId.equals(getActiveId)) {
                            Toast.makeText(SelectedNewUserEduInfoByDistrict.this, "No data is Found", Toast.LENGTH_SHORT).show();
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
                        // On database search Failure
                        Toast.makeText(SelectedNewUserEduInfoByDistrict.this, "Database Error Occured, Please Place a query", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
        //link to selectednewuserbydistrict class
        viewPerInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedNewUserEduInfoByDistrict.this, SelectedNewUserByDistrict.class);
                startActivity(intent);
            }
        });
        //link to selectednewusereduinfobydistrict class
        viewUploadDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedNewUserEduInfoByDistrict.this, CheckUploadFile.class);
                startActivity(intent);
            }
        });
        // getting the email from selectedNewUserByDistrict class
        useEmailIdForAll = SelectedNewUserByDistrict.takeEmailId.toString().trim();
    }
}
