package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class AdminGenerateNewEEId extends AppCompatActivity {
    private Button genNewId;
    private TextView viewNewGenId;
    private Button sendIdToUser;
    static String getGenId;
    //variable for Id generator
    static final String alphaNum = "ABCDEFGHIJKLMONPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    static SecureRandom random = new SecureRandom();
    static final int len = 10;
    static StringBuilder strngbldr;
    //STAtic string for id to use in all java file
    static String getIdAccessToAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_generate_new_eeid);
        //Initialization
        genNewId = (Button) findViewById(R.id.gen_new_id);
        viewNewGenId = (TextView) findViewById(R.id.view_new_gen_id);
        sendIdToUser = (Button)findViewById(R.id.send_id_to_user);
        //on click for link to SendGeneratedIdToUser class
        sendIdToUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminGenerateNewEEId.this, SendGeneratedIdToUser.class);
                startActivity(intent);
            }
        });
        // set On click listener
        genNewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method declare for random id generator
                    genRandomString(len);
                    viewNewGenId.setText(strngbldr);
                getIdAccessToAll = strngbldr.toString().trim();
            }
        });
    }
    // method defining for random id generator
    public String genRandomString(int len) {
        strngbldr = new StringBuilder(len);
        for (int i =0; i<len; i++) {
            strngbldr.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return strngbldr.toString().trim();
    }
}
