package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserHome extends AppCompatActivity {

    private Button login, reg;

    private FirebaseUser auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        //initialization

        login = (Button)findViewById(R.id.toUserLogin);
        reg = (Button)findViewById(R.id.toUserReg);

        // checking for session status
        auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth != null) {
            Intent intent = new Intent(UserHome.this, NewUserMainActivity.class);
            startActivity(intent);
        }

        // start link page user login
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(UserHome.this, UserLogin.class);
              startActivity(intent);
          }
      });
        ; //end
        //start link user reg page
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(UserHome.this, UserRegistration.class);
               startActivity(intent);
           }
       });


    }
}
