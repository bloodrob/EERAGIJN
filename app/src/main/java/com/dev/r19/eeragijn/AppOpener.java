package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AppOpener extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_opener);

        // ccreating a thread object.
        Thread myThread = new Thread() {
            @Override
            public void run() {
                // exception for thread
                try {
                    sleep(2000);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                Intent myIntent = new Intent(AppOpener.this, MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        };
        myThread.start();
    }
}
