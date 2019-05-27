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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchNewUserByDistrict extends AppCompatActivity {
    private Spinner selectDistrict;
    private Button submitSearchInfo;
    private ListView viewSearchItem;
    private ProgressDialog pd1;

    //list to get the data
    List<String> districtList;
    //list to get the search data
    List<String> searchDataList;
    //array adaptor to get the list item
    ArrayAdapter<String> getDistrictList;
    //ArrayAdaptor for search resultList
    ArrayAdapter<String> getSearchDataList;
    //static variable
    static String District;
    static String sendName;
    // Firebase database instance and reference variable
    private FirebaseDatabase database;
    private DatabaseReference ref;
    // for mobile network
    private ConnectivityManager cman;
    private NetworkInfo nInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_new_user_by_district);
        //initialization
        selectDistrict = (Spinner)findViewById(R.id.select_district);
        submitSearchInfo = (Button)findViewById(R.id.submit_search_info);
        viewSearchItem = (ListView)findViewById(R.id.view_search_item);
        //Firebase work start
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("NewUserPersionalInfo");
        //end
        // list initialization and adding value
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
      // geeting the item in the arrayadaptor
        getDistrictList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, districtList);
        getDistrictList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        selectDistrict.setAdapter(getDistrictList);
        // getting the selected item into the string
        selectDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                District = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of arrayAdaptor
        //initializing the searchlist
        searchDataList = new ArrayList<String>();
        submitSearchInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method declare for serach submit
                submitSearch();
                //checking the network connection
                IsOnline();
            }
        });
    }
    // defining the method
    private void submitSearch(){
        //initialization of progress dialog
        pd1 = new ProgressDialog(this);
        //  pd1.setMessage("searching....  Please wait");
        pd1.setCanceledOnTouchOutside(false);
        pd1 = ProgressDialog.show(this, "Searching....", "Please wait.");
        pd1.show();
        // querying the data to match condition
        final Query query1 = ref.orderByChild("District").equalTo(District);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        SearchNewUserByDistrictModel res = child.getValue(SearchNewUserByDistrictModel.class);
                        // dismissing pd
                        pd1.dismiss();
                        searchDataList.add(res.Name);
                        getSearchDataList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,searchDataList);
                        viewSearchItem.setAdapter(getSearchDataList);
                        // selected item to the string
                        viewSearchItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //send the selected item to the correspondence page
                                Intent intent = new Intent(SearchNewUserByDistrict.this, SelectedNewUserByDistrict.class);
                                SelectedNewUserByDistrict.selectedName = parent.getItemAtPosition(position).toString().trim();
                                //  intent.putExtra("sendName", sendName);
                                startActivity(intent);
                            }
                        });
                    }
                }
                else
                {
                    //dissmissing the pd
                    // pd1.setCanceledOnTouchOutside(true);
                    pd1.cancel();
                    if (pd1 == null && pd1.isShowing()) {
                        pd1.dismiss();
                    }
                    List<String> mylist = new ArrayList<String>();
                    mylist.add("No new applicant avaiable right now in this location, Kindly try after the day");
                    viewSearchItem.setEmptyView(findViewById(R.id.view_search_item));
                    ArrayAdapter<String> adpt = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, mylist);
                    viewSearchItem.setAdapter(adpt);
                    Toast.makeText(SearchNewUserByDistrict.this, "No New Apllicant Available Right Now", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SearchNewUserByDistrict.this, "Something wrong, please contact to the database administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
    private void IsOnline() {
        cman = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        nInfo = cman.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Toast.makeText(SearchNewUserByDistrict.this, "You Online", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(SearchNewUserByDistrict.this, "No Internet Connection, Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
            // intent to Device Network setting
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //intent.sett
           // intent.setClassName("com.android.phone","com.android.phone.NetworkSetting");
            startActivity(intent);
            return;
        }
    }
}
