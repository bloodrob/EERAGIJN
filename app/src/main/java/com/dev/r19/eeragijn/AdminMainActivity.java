package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {

    private Button toUploadFile, toCheckNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //initialization
        toUploadFile = (Button)findViewById(R.id.to_upload_file);
        toCheckNewUser = (Button)findViewById(R.id.to_check_new_user);

        //link to the page
        toUploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, CheckUploadFile.class);
                startActivity(intent);
            }
        });
        //link to search new user page
        toCheckNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, SearchNewUserByDistrict.class);
                startActivity(intent);
            }
        });
    }
}
