package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {

    private Button toCheckNewUser, toSendNotification, toExistingUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //initialization
        toCheckNewUser = (Button)findViewById(R.id.to_check_new_user);
        toSendNotification = (Button)findViewById(R.id.to_send_notification);
        toExistingUser = (Button)findViewById(R.id.to_check_existing_user);
        //link to search new user page
        toCheckNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, SearchNewUserByDistrict.class);
                startActivity(intent);
            }
        });
        //link to send notification
        toSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminSendJobNotification.class);
                startActivity(intent);
            }
        });
        //link to check Existing user
        toExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminSearchExistingUser.class);
                startActivity(intent);
            }
        });
    }
}
