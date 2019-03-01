package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewUserMainActivity extends AppCompatActivity {

    private Button docUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_main);
        //initialization
        docUpload = (Button)findViewById(R.id.doc_upload);

        //link to document upload page
        docUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserMainActivity.this, DocumentUpload.class);
                startActivity(intent);
            }
        });
    }
}
