package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AdminPushUserData extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref, ref1;
    static String getActiveIdToImportUserData;
    String Ex_EmpId, Ex_name, Ex_email, Ex_fatherName, Ex_cast, Ex_dob, Ex_gender, Ex_address, Ex_district, Ex_city, Ex_state, Ex_mobile;
    //To use ref activeId of the node
    String getId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_push_user_data);
        //firebase work
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("NewUserPersionalInfo");
        ref1 = database.getReference("ExistingUserPersionalInfo"); //end


        //getting the activeId from another class
        getActiveIdToImportUserData = SelectedNewUserByDistrict.takeActiveId.toString().trim();
        //firebase work to access database
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               SearchNewUserByDistrictModel selModNewUSer = dataSnapshot.getValue(SearchNewUserByDistrictModel.class);
                if (getActiveIdToImportUserData.equals(selModNewUSer.activeId)) {
                    Toast.makeText(AdminPushUserData.this, "The name is " +selModNewUSer.Name, Toast.LENGTH_LONG).show();
                    Ex_name = selModNewUSer.Name;
                    Ex_email = selModNewUSer.Email;
                    Ex_fatherName = selModNewUSer.Father_name;
                    Ex_cast = selModNewUSer.Caste;
                    Ex_dob = selModNewUSer.DOB;
                    Ex_gender = selModNewUSer.Gender;
                    Ex_address = selModNewUSer.Address;
                    Ex_district = selModNewUSer.District;
                    Ex_city = selModNewUSer.City;
                    Ex_state = selModNewUSer.State;
                    Ex_mobile = selModNewUSer.Mobile;
                    Ex_EmpId = SendGeneratedIdToUser.getTheId.toString().trim();

                    //method declare to add data
                    AddDataToExisting(Ex_name, Ex_email, Ex_fatherName, Ex_cast, Ex_dob, Ex_gender, Ex_address, Ex_district, Ex_city, Ex_state, Ex_mobile, Ex_EmpId);
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
    //firebase work to submit data from new to exist node
    // add ref activeId to the coressponding node
    private void AddDataToExisting(String Ex_name, String Ex_email, String Ex_fatherName, String Ex_caste, String Ex_gender, String Ex_dob, String Ex_address, String Ex_district, String Ex_city, String Ex_state, String Ex_mobile, String ex_mobile) {
        AdminPushDataModel adMod = new AdminPushDataModel(Ex_name,Ex_EmpId, Ex_email, Ex_fatherName, Ex_caste, Ex_gender, Ex_dob, Ex_address, Ex_district, Ex_city, Ex_state, Ex_mobile);
        adMod.activeId = getActiveIdToImportUserData.toString().trim();
        getId = adMod.activeId;
        ref1.child(getId).setValue(adMod);
        AddActualDataToExisting();
    }// end of ref
   //start of adding data to the cor node
    private void AddActualDataToExisting() {
        ref1.child(getId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                AdminPushDataModel adMod = dataSnapshot.getValue(AdminPushDataModel.class);
                if (adMod == null ) {
                    Toast.makeText(AdminPushUserData.this, "Data Is Null, Please Resubmit Again", Toast.LENGTH_LONG).show();
                    return;
                }
                //deleting the data from the database if succussfully submitted
                //query for geting the required child node
                Query delQuery = ref.orderByChild("activeId").equalTo(getActiveIdToImportUserData);
                delQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot delSnapshot: dataSnapshot.getChildren()) {
                            delSnapshot.getRef().removeValue();
                            //Toasting the result
                            Toast.makeText(AdminPushUserData.this, "Successfully assigned the Emp Id to The user with data", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(AdminPushUserData.this, SearchNewUserByDistrict.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminPushUserData.this, "Database Failure, Contact the administrtion...", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
