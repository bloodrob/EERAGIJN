package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewUserMainActivity extends AppCompatActivity {

    private Button docUpload, persionalInfoInsert, educationalInfoInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_main);
        //initialization
        docUpload = (Button)findViewById(R.id.doc_upload);
        persionalInfoInsert = (Button)findViewById(R.id.persional_info_insert);
        educationalInfoInsert = (Button)findViewById(R.id.educational_info_insert);

        //link to document upload page
        docUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserMainActivity.this, UserDocumentUpload.class);
                startActivity(intent);
            }
        });

        //link to persional information insert page
        persionalInfoInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserMainActivity.this, UserPersionalInfoInsert.class);
                startActivity(intent);
            }
        });
        //link to education information insert page
        educationalInfoInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewUserMainActivity.this, UserEducationQualificationInsert.class);
                startActivity(intent);
            }
        });
    }
}
