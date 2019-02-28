package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        Button admin, emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin = (Button)findViewById(R.id.AsAdmin);
        emp = (Button)findViewById(R.id.AsEmp);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AdminHome.class);
                startActivity(intent);
            }
        });
        // link to user home
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserHome.class);
                startActivity(intent);
            }
        });

    }
}
