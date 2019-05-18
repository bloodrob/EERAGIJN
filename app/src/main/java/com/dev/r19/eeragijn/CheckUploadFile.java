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

    private Button checkPersonImage, checkPersonSign, checkPersonHslc, checkPersonHS, checkPersonGraduation, checkPersonPostGraduation, checkPersonCast, checkPersonPrc;
    private Button generateId;

    //variable to pass string through intent
    String pI,pS,pHl,pHs,pG,pPg,pC,pPr;
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
        checkPersonHS = (Button)findViewById(R.id.check_person_hs);
        checkPersonGraduation = (Button)findViewById(R.id.check_person_graduation);
        checkPersonPostGraduation = (Button)findViewById(R.id.check_person_postgraduation);
        checkPersonCast = (Button)findViewById(R.id.check_person_cast);
        checkPersonPrc = (Button)findViewById(R.id.check_person_prc);
        generateId = (Button)findViewById(R.id.to_generate_id);

        //assign value to the varible to be passed
        pI = "personImage";
        pS = "personSign";
        pHl = "personHslc";
        pHs = "personHs";
        pG = "personGraduation";
        pPg ="personPostgraduation";
        pC = "personCast";
        pPr = "personPrc";
        // ref to firebase
        ref = FirebaseStorage.getInstance();
        //check person image
        checkPersonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // storageref = ref.getReference().child("SelfImage");
                //load image using glide
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personImage",pI);
                startActivity(intent);

            }
        });
        //check person sign
        checkPersonSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personSign", pS);
                startActivity(intent);
            }
        });
        //check person Hslc
        checkPersonHslc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personHslc",pHl);
                startActivity(intent);
            }
        });
        //check person HS
        checkPersonHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personHs", pHs);
                startActivity(intent);
            }
        });
        //check person graduation
        checkPersonGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personGraduation", pG);
                startActivity(intent);
            }
        });
        //check person postgraduation
        checkPersonPostGraduation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personPostgraduation", pPg);
                startActivity(intent);
            }
        });
        //check person cast
        checkPersonCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personCast", pC);
                startActivity(intent);
            }
        });
        //check person Prc
        checkPersonPrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckUploadFile.this, AdminViewDocument.class);
                intent.putExtra("personPrc", pPr);
                startActivity(intent);
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
