package com.dev.r19.eeragijn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UserSearchCustomJob extends AppCompatActivity {

    private Spinner searchByCategory;
    private Button submitCusSearch;
    //arraylist for category
    List<String> cusSerachCategoryList;
    //arrayadaptor of the list
    ArrayAdapter<String> getCusSearchCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job);

        // init of button
        searchByCategory = (Spinner)findViewById(R.id.category_search);
        submitCusSearch = (Button)findViewById(R.id.custom_search);

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

    }
}
