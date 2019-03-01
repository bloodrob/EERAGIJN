package com.dev.r19.eeragijn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AuthExistingUserMainActivity extends AppCompatActivity {

    private EditText regEeId, regDateOfBirth, regPassword;
    private Button submitData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_existing_user_main);
        // initalization
        regEeId = (EditText) findViewById(R.id.reg_ee_id);
        regDateOfBirth = (EditText)findViewById(R.id.reg_date_of_birth);
        regPassword = (EditText)findViewById(R.id.reg_password);
        submitData = (Button)findViewById(R.id.submit_data);

        // submit function start
        submitData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
