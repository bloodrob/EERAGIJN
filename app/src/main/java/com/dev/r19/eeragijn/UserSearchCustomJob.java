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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserSearchCustomJob extends AppCompatActivity {

    private Spinner searchByCategory;
    private Button submitCusSearch;
    private ListView listOfItem;
    // static variable for Listitem
    static String categoryItem;
    //arraylist for category
    List<String> cusSerachCategoryList;
    //arrayadaptor of the list
    ArrayAdapter<String> getCusSearchCategoryList;
    //array for search datalist
    static List<String> catSearchList;
    //arrayadaptor for search list
    ArrayAdapter<String> getCatSearchList;
    // for mobile network
    private ConnectivityManager cman;
    private NetworkInfo nInfo;
    //progress
    private ProgressDialog pd1;
    //Firebase variable
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job);

        // init of button
        searchByCategory = (Spinner)findViewById(R.id.category_search);
        submitCusSearch = (Button)findViewById(R.id.custom_search);
        listOfItem = (ListView)findViewById(R.id.list_of_item);

        // init and assign of arraylist
        cusSerachCategoryList = new ArrayList<String>();
        cusSerachCategoryList.add("Bank");
        cusSerachCategoryList.add("State Govt.");
        cusSerachCategoryList.add("Central Govt.");
        cusSerachCategoryList.add("Railway");
        cusSerachCategoryList.add("Oil");
        cusSerachCategoryList.add("Ongc");
        cusSerachCategoryList.add("School/College/University");
        cusSerachCategoryList.add("Research");
        cusSerachCategoryList.add("It company");
        cusSerachCategoryList.add("Private Company");
        cusSerachCategoryList.add("General Recruitment");
        cusSerachCategoryList.add("Insurance");
        //get the item into arrayadaptor
        getCusSearchCategoryList = new ArrayAdapter<String>(UserSearchCustomJob.this, android.R.layout.simple_spinner_item,cusSerachCategoryList);
        getCusSearchCategoryList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        searchByCategory.setAdapter(getCusSearchCategoryList);
        //get the selected item
        searchByCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryItem = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserSearchCustomJob.this, "Select a category", Toast.LENGTH_LONG).show();
            }
        });
        //firebase init
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");

        //init of arraylist for the searched item
        catSearchList = new ArrayList<String>();

        //butto submit method
        submitCusSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //submit search method declare
                submitSearch();
                //checking network connectivity
                isOnline();
            }
        });
    }
    //submit method define
    private void submitSearch() {
        pd1 = new ProgressDialog(this);
        pd1.setCanceledOnTouchOutside(false);
        pd1 = ProgressDialog.show(this, "searching.." ,"please wait");
        pd1.show();
        //query to search data
        final Query query = ref.orderByChild("JobSubject").equalTo(categoryItem);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        UserSeachCustomJobModel res = child.getValue(UserSeachCustomJobModel.class);
                        // dismissing the pd
                        pd1.dismiss();
                        //into the list
                        catSearchList.add("job name :" + res.JobName +"\n" + "Last Date :" +res.LastDate +"\n\n\n");
                        getCatSearchList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, catSearchList);
                        listOfItem.setAdapter(getCatSearchList);

                        //select item to details view
                       listOfItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                           @Override
                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                               Intent intent = new Intent(UserSearchCustomJob.this, UserSearchCustomJobDetails.class);
                               UserSearchCustomJobDetails.selectedNane = parent.getItemAtPosition(position).toString().trim();
                               startActivity(intent);

                           }
                       });
                    }
                }
                else {
                    // for cancel pd
                    pd1.cancel();
                    if (pd1 == null && pd1.isShowing()) {
                        pd1.dismiss();
                    }
                    //for empty view and place a message
                    List<String> myMes = new ArrayList<String>();
                    myMes.add("Sorry ... No job advertisement availble for the category");
                    listOfItem.setEmptyView(findViewById(R.id.list_of_item));
                    ArrayAdapter<String> getMyMes = new ArrayAdapter<String>(UserSearchCustomJob.this, android.R.layout.simple_list_item_1, myMes);
                    listOfItem.setAdapter(getMyMes);
                    Toast.makeText(UserSearchCustomJob.this, "Sorry..No job Ad found", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //isOnline method declare
    private void isOnline() {
        cman = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        nInfo = cman.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Toast.makeText(this, "You Online", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No Internet Connection, Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
            // intent to Device Network setting
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //intent.sett
            // intent.setClassName("com.android.phone","com.android.phone.NetworkSetting");
            startActivity(intent);
            return;
        }

    }
}
