package com.dev.r19.SakoriSarothiClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class UserSearchCustomJob extends AppCompatActivity {

    private Spinner searchByCategory;
    private Button submitCusSearch;
    private TextView customSearchText;
    private ListView listOfItem;
    // static variable for Listitem
    private String categoryItem;

    private List<String> cusSerachCategoryList;
    private ArrayAdapter<String> getCusSearchCategoryList;
    
    private RecyclerView cusRecyclerView;
    private List<UserSearchCustomJobModel> catSearchList = new ArrayList<>();
    private RecyclerView.Adapter getCatSearchList;
    // for mobile network
    private ConnectivityManager cman;
    private NetworkInfo nInfo;
    //progress
    private ProgressDialog cusJobPd;
    //Firebase variable
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private ValueEventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job);

        // init activityvar
        initActivityVar();
        initArrayAndAdapter();
        initPdBar();
        // init and assign of arraylist
        
        //firebase init
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");

        //butto submit method
        submitCusSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking network connectivity
                isOnline();
                //submit search method declare
                submitSearch();

            }
        });
    }
    //submit method define
    private void submitSearch() {
        cusJobPd.show();
        catSearchList.clear();
        //query to search data
        final Query query = ref.orderByChild("JobSubject").equalTo(categoryItem);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    cusJobPd.dismiss();
                    for (DataSnapshot dSnapshot : dataSnapshot.getChildren()) {
                        UserSearchCustomJobModel resCusJob = dSnapshot.getValue(UserSearchCustomJobModel.class);
                        customSearchText.setText("Here you go with you the job you prefer. Click the job for further information.");
                        catSearchList.add(resCusJob);

                        getCatSearchList = new UserSearchCustomJobAdapterView(UserSearchCustomJob.this, (ArrayList<UserSearchCustomJobModel>) catSearchList);
                        cusRecyclerView.setAdapter(getCatSearchList);
                    }
                }
                else {
                    cusJobPd.dismiss();
                    catSearchList.clear();
                    getCatSearchList = new UserSearchCustomJobAdapterView(UserSearchCustomJob.this, (ArrayList<UserSearchCustomJobModel>) catSearchList);
                    cusRecyclerView.setAdapter(getCatSearchList);
                    customSearchText.setText("No Job Available.........");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addValueEventListener(listener);
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
    
    private void initArrayAndAdapter(){
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
                Toast.makeText(UserSearchCustomJob.this, "Selected item is :"+categoryItem, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserSearchCustomJob.this, "Select a category", Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private void initPdBar(){
        cusJobPd =new ProgressDialog(this, R.style.CustomDialog);
        cusJobPd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        cusJobPd.setTitle("searching...Please wait..");
        cusJobPd.setCanceledOnTouchOutside(false);
    }

    private void initActivityVar() {
        searchByCategory = (Spinner)findViewById(R.id.category_search);
        submitCusSearch = (Button)findViewById(R.id.custom_search);
        customSearchText = (TextView)findViewById(R.id.custom_search_text);

        cusRecyclerView = (RecyclerView)findViewById(R.id.custom_JOb_recycler);
        cusRecyclerView.setHasFixedSize(true);
        cusRecyclerView.setLayoutManager(new LinearLayoutManager(UserSearchCustomJob.this));
    }
}
