package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserMainActivity extends AppCompatActivity {

    private Button UsignOut, toNewUser, toRegisterUser;
    private FirebaseAuth auth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        toNewUser = (Button)findViewById(R.id.to_new_user);
        toRegisterUser = (Button)findViewById(R.id.to_register_user);
        UsignOut = (Button)findViewById(R.id.SignOUt);

        //link to new user activity page
        toNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainActivity.this, NewUserMainActivity.class);
                startActivity(intent);
            }
        });
        // link to Existing user activity page
        toRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMainActivity.this, AuthExistingUserMainActivity.class);
                startActivity(intent);
            }
        });
        //to sign out
        UsignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth = FirebaseAuth.getInstance();
                auth.signOut();
                user = auth.getCurrentUser();
                if (user == null) {
                    Intent intent = new Intent(UserMainActivity.this, UserHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
