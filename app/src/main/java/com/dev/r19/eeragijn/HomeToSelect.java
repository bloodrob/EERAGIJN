package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeToSelect extends AppCompatActivity {

    Button admin, emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_to_select);
        admin = (Button)findViewById(R.id.AsAdmin);
        emp = (Button)findViewById(R.id.AsEmp);
        // on click listener for admin
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeToSelect.this, AdminLogin.class);
                startActivity(intent);
            }
        });
        // link to user home
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeToSelect.this, UserHome.class);
                startActivity(intent);
            }
        });
    }
}
