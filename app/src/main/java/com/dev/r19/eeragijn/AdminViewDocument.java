package com.dev.r19.eeragijn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class AdminViewDocument extends AppCompatActivity {

    private ImageView adminImageView;
    //get the Id
    static String getIdForimage;
    //firebase variable
    private StorageReference fStr1, fStr2;
    private FirebaseStorage str1;
    private String idUseForNameRef;
    //string to get the value of intent
    String getInentString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_document);
        adminImageView = (ImageView)findViewById(R.id.admin_view_image);

        // getting Intent value
        Intent intent = getIntent();
        if (intent.hasExtra("personImage")) {
            getInentString = intent.getStringExtra("personImage");
        }
        if (intent.hasExtra("personSign")) {
            getInentString = intent.getStringExtra("personSign");
        }
        if (intent.hasExtra("personHslc")) {
            getInentString = intent.getStringExtra("personHslc");
        }
        if (intent.hasExtra("personHs")) {
            getInentString = intent.getStringExtra("personHs");
        }
        if (intent.hasExtra("personGraduation")) {
            getInentString = intent.getStringExtra("personGraduation");
        }
        if (intent.hasExtra("personPostgraduation")) {
            getInentString = intent.getStringExtra("personPostgraduation");
        }
        if (intent.hasExtra("personCast")) {
            getInentString = intent.getStringExtra("personCast");
        }
        if (intent.hasExtra("personPrc")) {
            getInentString = intent.getStringExtra("personPrc");
        }

        //getting the value from SelectedNewUserEduInfoByDistrict
        getIdForimage = SelectedNewUserByDistrict.takeActiveId.toString().trim();
        idUseForNameRef = getIdForimage+".jpeg";
        Toast.makeText(AdminViewDocument.this, "id is :"+idUseForNameRef, Toast.LENGTH_SHORT).show();
        //firebase work
        str1 = FirebaseStorage.getInstance();
        // for person selfImage
        if (getInentString.equals("personImage")) {
            fStr1 = str1.getReference().child("User Document").child("SelfImage").child(getIdForimage);
        }
        // for personSign image
        if (getInentString.equals("personSign")) {
            fStr1 = str1.getReference().child("User Document").child("SignImage").child(getIdForimage);
        }
        // for personHslc image
        if (getInentString.equals("personHslc")) {
            fStr1 = str1.getReference().child("User Document").child("HslcImage").child(getIdForimage);
        }
        // for personHs image
        if (getInentString.equals("personHs")) {
            fStr1 = str1.getReference().child("User Document").child("HsImage").child(getIdForimage);
        }
        // for personGraduation
        if (getInentString.equals("personGraduation")) {
            fStr1 = str1.getReference().child("User Document").child("GraduationImage").child(getIdForimage);
        }
        // for personPostgraduation
        if (getInentString.equals("personPostgraduation")) {
            fStr1 = str1.getReference().child("User Document").child("PostGraduationImage").child(getIdForimage);
        }
        // for person Cast Image
        if (getInentString.equals("personCast")) {
            fStr1 = str1.getReference().child("User Document").child("CastImage").child(getIdForimage);
        }
        //for person Prc Image
        if (getInentString.equals("personPrc")) {
            fStr1 = str1.getReference().child("User Document").child("PRCImage").child(getIdForimage);
        }
        // actual firebase work for image retrieve. as the process search image asynchronously so we use try and catch exception handling method
            try {
                final File locFile = File.createTempFile("images", "jpeg");
                fStr1.getFile(locFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Bitmap bitmap = BitmapFactory.decodeFile(locFile.getAbsolutePath());
                        adminImageView.setImageBitmap(bitmap);
                    }
                });
                fStr1.getFile(locFile).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdminViewDocument.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                        return;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
