package com.dev.r19.eeragijn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserEducationQualificationInsert extends AppCompatActivity {
    private EditText percantageHslc, percantageHs, percantageGraduation, percantagePostgraduation;
    private Spinner hsBoard, hslcBoard, graduationBoard, postgraduationBoard;
    private Spinner hsYear, hslcYear, graduationYear, postgraduationYear;
    private Button submitEducationalInfo;

    //arraylist for spinner
    List<String> hsBoardList, hslcBoardList, graduationBoardList, postgraduationBoardList;
    List<String> YearList;
    // arrayadaptor to get the arraylist of Board or college or university
    ArrayAdapter<String> getHsBoardList, getHslcBoardList, getGraduationBoardList, getPostgraduationBoardList;
    // arrayadaptor to get the arraylist of year of passing of board, college, university
    ArrayAdapter<String> getHsYearList, getHslcYearList, getGraduationYearList, getPostGraduationYearList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_education_qualification_insert);
        //initialization of editText
        percantageHslc = (EditText)findViewById(R.id.per_hslc);
        percantageHs = (EditText)findViewById(R.id.percentage_hs);
        percantageGraduation = (EditText)findViewById(R.id.percentage_graduation);
        percantagePostgraduation = (EditText)findViewById(R.id.percentage_postgraduation);
        //Initialization of spinner
        hsBoard = (Spinner)findViewById(R.id.SP_hs_board);
        hslcBoard = (Spinner)findViewById(R.id.SP_hslc_board);
        graduationBoard = (Spinner)findViewById(R.id.SP_graduation_board);
        postgraduationBoard = (Spinner)findViewById(R.id.SP_postgraduation_board);
        hsYear = (Spinner)findViewById(R.id.SP_hs_year);
        hslcYear = (Spinner)findViewById(R.id.SP_hslc_year);
        graduationYear = (Spinner)findViewById(R.id.SP_graduation_year);
        postgraduationYear = (Spinner)findViewById(R.id.SP_postgraduation_year);
        //initialization of Button
        submitEducationalInfo = (Button)findViewById(R.id.submit_educational_info);
        //end of initialization
        //adding item to spinner hsBoardlist
        hsBoardList = new ArrayList<String>();
        hsBoardList.add("SEBA");
        hsBoardList.add("CBSE");
        //adding item to spinner hslcBoardlist
        hslcBoardList = new ArrayList<String>();
        hslcBoardList.add("SEBA");
        hslcBoardList.add("CBSE");
        //adding item to spinner graduation college or university
        graduationBoardList = new ArrayList<String>();
        graduationBoardList.add("Dibrugarh University");
        graduationBoardList.add("Tezpur University");
        graduationBoardList.add("Assam UNiversity");
        graduationBoardList.add("Kaziranga University");
        //adding item to spinner of postgarduation University
        postgraduationBoardList = new ArrayList<String >();
        postgraduationBoardList.add("Dibrugarh University");
        postgraduationBoardList.add("Tezpur University");
        postgraduationBoardList.add("Assam University");
        postgraduationBoardList.add("Guwahati University");
        //getting the years into arraylist
        YearList = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1980; i<=thisYear; i++){
            YearList.add(Integer.toString(i));
        }
        //getting the item by arrayadaptor of hsBoardlsit
        getHsBoardList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hsBoardList);
        getHsBoardList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hsBoard.setAdapter(getHsBoardList);
        // getting the selected value to the string
        hsBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String HS_board = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //end of arrayadaptor for hsBoard
        //getting the item by arraadaptor of hslcBoardlist
        getHslcBoardList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hslcBoardList);
        getHslcBoardList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hslcBoard.setAdapter(getHslcBoardList);
        //getting the value into string
        hslcBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String HSLC_board = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of arrayadaptor for hslcBoard
        // getting the item into arradaptor of graduation Board or college or university
        getGraduationBoardList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, graduationBoardList);
        getGraduationBoardList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        graduationBoard.setAdapter(getGraduationBoardList);
        //getting the selected value to the string
        graduationBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Graduation_board = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of the arrayadaptor for graduation Board
        //getting the item of postgraduation board into arrayadaptor
        getPostgraduationBoardList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, postgraduationBoardList);
        getPostgraduationBoardList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        postgraduationBoard.setAdapter(getPostgraduationBoardList);
        //getting the selected value into string
        postgraduationBoard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String PostGraduation_University = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of arrayadaptor of postgraduation board
        //getting the item by arrayadptor into hslcyear of Yearlist
        getHslcYearList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,YearList);
        getHslcYearList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hslcYear.setAdapter(getHslcYearList);
        //getting the selected item to the string
        hslcYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String HSLC_pass_year = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); // end of hslc
        // start of hs
        getHsYearList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, YearList);
        getHsYearList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        hsYear.setAdapter(getHsYearList);
        // getting the selected value to the string
        hsYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String HS_pass_year = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end hs
        // start of graduation
        getGraduationYearList = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, YearList);
        getGraduationYearList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        graduationYear.setAdapter(getGraduationYearList);
        // selected value to string
        graduationYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Graduation_pass_year = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); // end of grduation
        //start of postGraduation
        getPostGraduationYearList = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, YearList);
        getPostGraduationYearList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        postgraduationYear.setAdapter(getPostGraduationYearList);
        //getting the selected value into the string
        postgraduationYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String PostGraduation_pass_year = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });// end of postgraduation
    }
}
