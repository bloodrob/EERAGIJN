package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SearchAllExistingUserByDistrict extends AppCompatActivity {

        // buttun variable
        private Button submitSearch;
        private Spinner spinnerDistrict;
        private ListView existUserSearchedList;
        // arrayList for add the data into array
        List<String> districtList;
        // arrayAdaptor to get the list of data;
        ArrayAdapter<String> getTheListOfDistrict;
        //arraylist for add the data of searc value
        List<String> takeDistrictList;
        // arrayAdaptor for getting the list of data i.e searched
        ArrayAdapter<String> takeTheListOfSearchedDistrict;
        //static string to get the selected string of district
        static String District;
        // Firebase work to search data
        FirebaseDatabase database;
        DatabaseReference ref;
        // to use in network connectivity checking
        private ConnectivityManager conMan1;
        private NetworkInfo netInfo1;
        // progress dialog to check the progress
        ProgressDialog ProDai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_existing_user_by_district);
        //Initialization
        submitSearch = (Button)findViewById(R.id.submit_district);
        spinnerDistrict = (Spinner)findViewById(R.id.spinner_district_list);
        existUserSearchedList = (ListView)findViewById(R.id.exist_user_searched_list);
        // initialization of progress dialog
        ProDai = new ProgressDialog(this);
        ProDai.setMessage("Please Wait, Searching for data");
        ProDai.setCanceledOnTouchOutside(false);

        // Initialization and assigning data to arraylist
        districtList = new ArrayList<String>();
        districtList.add("Tinsukia");
        districtList.add("Dibrugarh");
        districtList.add("Choraideo");
        districtList.add("Sibsagar");
        districtList.add("Jorhat");
        districtList.add("Golaghat");
        districtList.add("Dhemaji");
        districtList.add("LakhimPur");
        districtList.add("Nogaon");
        districtList.add("Sonitpur");
        districtList.add("Udalguri");
        districtList.add("Kamrup");
        districtList.add("Baksha");
        districtList.add("Barpeta");
        districtList.add("Mongoldoi");
        districtList.add("Dhubri");
        districtList.add("Darang");
        districtList.add("Karbi-Anglong");
        districtList.add("Kokrajhar");
        districtList.add("Hailakandi");
        districtList.add("Goalpara");
        districtList.add("Morigaon");
        districtList.add("Karimganj");
        districtList.add("Cachar");
        districtList.add("BongaiGaon");
        districtList.add("Chirang");
        districtList.add("Majuli");
        districtList.add("Nalbari");
        districtList.add("Dima-Hasao");
        //Initiatization and getting the listed data item to adaptor;
        getTheListOfDistrict = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtList);
        getTheListOfDistrict.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(getTheListOfDistrict);
        // getting the select string
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                District = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        submitSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method to search the data
                searchExistUser();
                // checking the network connectivity
                isYouOnline();
            }
        });
    }
    private void searchExistUser() {
        // firebase creating instance and set a ref to instance
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("ExistingUserPersionalInfo");
        // show the progress
        ProDai.show();
        //Initialization of arrayList
        takeDistrictList = new ArrayList<>();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdminPushDataModel adMod1 = dataSnapshot.getValue(AdminPushDataModel.class);
                // condition if equals
                if (District.equals(adMod1.Ex_district)){
                    ProDai.dismiss();
                    // adding searched data to arraylist
                    takeDistrictList.add("Employee Id : " +adMod1.Ex_EmpId +"\n" +"Name  :  "+adMod1.Ex_name +"\n"+"\n"+"\n");
                    //initializating and getting the list of item
                    takeTheListOfSearchedDistrict = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, takeDistrictList);
                    existUserSearchedList.setAdapter(takeTheListOfSearchedDistrict);
                }
                //condition if not equals
                else if (!equals(adMod1.Ex_district)) {
                    //dissmissing the pd
                    ProDai.setCanceledOnTouchOutside(true);
                    ProDai.cancel();
                    if (ProDai == null && ProDai.isShowing()) {
                        ProDai.dismiss();
                    }
                    Toast.makeText(SearchAllExistingUserByDistrict.this, "No Existing User In This District", Toast.LENGTH_SHORT).show();
                    // empty ing the list view
                   // ArrayAdapter adoptor = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1);
                   // existUserSearchedList.setEmptyView(findViewById(R.id.exist_user_searched_list));
                   // existUserSearchedList.setAdapter(adoptor);
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
                Toast.makeText(SearchAllExistingUserByDistrict.this, "Database Error, Please Contact To DataBase Administrators", Toast.LENGTH_SHORT).show();
                return;

            }
        });
    }
    // function for checking the network connectivity
    private void isYouOnline() {
        // checking network connection of the device
        conMan1 = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo1 = conMan1.getActiveNetworkInfo();
        if (netInfo1 != null & netInfo1.isConnected()) {
            Toast.makeText(SearchAllExistingUserByDistrict.this, "You are Online", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(SearchAllExistingUserByDistrict.this, "No Internet Connection, Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
            // intent to Device Network setting
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //intent.sett
            // intent.setClassName("com.android.phone","com.android.phone.NetworkSetting");
            startActivity(intent);
            return;
        }
    }
}
