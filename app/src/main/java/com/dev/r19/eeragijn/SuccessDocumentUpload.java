package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessDocumentUpload extends AppCompatActivity {

    private Button toMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_document_upload);

        toMain = (Button)findViewById(R.id.toUserMian);

        toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessDocumentUpload.this, NewUserMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
