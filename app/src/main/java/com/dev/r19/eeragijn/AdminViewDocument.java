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

        //getting the value from SelectedNewUserEduInfoByDistrict
        getIdForimage = SelectedNewUserByDistrict.takeActiveId.toString().trim();
        idUseForNameRef = getIdForimage+".jpeg";
        Toast.makeText(AdminViewDocument.this, "id is :"+idUseForNameRef, Toast.LENGTH_SHORT).show();
        //firebase work
        str1 = FirebaseStorage.getInstance();
        // for person selfImage
        if (getInentString.equals("personImage")) {
            fStr1 = str1.getReference().child("User Document").child("SelfImage").child(getIdForimage);
            // fStr2 = fStr1.child(getIdForimage);
            // Glide.with(this).using(new )
            //Glide.with(this).load(fStr1).into(adminImageView);
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
        // for personSign image
        if (getInentString.equals("personSign")) {
            fStr1 = str1.getReference().child("User Document").child("SignImage").child(getIdForimage);
            // fStr2 = fStr1.child(getIdForimage);
            // Glide.with(this).using(new )
            //Glide.with(this).load(fStr1).into(adminImageView);
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
}
