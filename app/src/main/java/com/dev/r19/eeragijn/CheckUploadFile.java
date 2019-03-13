package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CheckUploadFile extends AppCompatActivity {

    private Button checkPersonImage, checkPersonSign, checkPersonHslc;
    private ImageView viewPersonImage, viewPersonSign, viewPersonHslc;
    private Button generateId;

    // firebase storage ref variable
    private FirebaseStorage ref;
    private StorageReference storageref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_upload_file);
        //initialization
        checkPersonImage = (Button)findViewById(R.id.check_person_image);
        checkPersonSign = (Button)findViewById(R.id.check_person_sign);
        checkPersonHslc = (Button)findViewById(R.id.check_person_hslc);
        viewPersonImage = (ImageView)findViewById(R.id.view_person_image);
        viewPersonSign = (ImageView)findViewById(R.id.view_person_sign);
        viewPersonHslc = (ImageView)findViewById(R.id.view_person_hslc);
        generateId = (Button)findViewById(R.id.to_generate_id);

        // ref to firebase
        ref = FirebaseStorage.getInstance();

        checkPersonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageref = ref.getReference().child("SelfImage");
                //load image using glide

            }
        });
        //link to AdminGenerateNewId just for
        generateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminGenerateNewEEId.class);
                startActivity(intent);
            }
        });
    }
}
