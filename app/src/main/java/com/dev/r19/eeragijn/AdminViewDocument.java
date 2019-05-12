package com.dev.r19.eeragijn;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AdminViewDocument extends AppCompatActivity {

    private ImageView adminImageView;
    //get the Id
    static String getIdForimage;
    //firebase variable
    private StorageReference fStr1, fStr2;
    private FirebaseStorage str1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_document);
        adminImageView = (ImageView)findViewById(R.id.admin_view_image);

        //getting the value from SelectedNewUserEduInfoByDistrict
        getIdForimage = SelectedNewUserByDistrict.takeActiveId.toString().trim();

        //firebase work
        str1 = FirebaseStorage.getInstance();
        fStr1 = str1.getReference("User Document/Self Image/"+getIdForimage+".jpg");
       // fStr2 = fStr1.child(getIdForimage);
       // Glide.with(this).using(new )
        Glide.with(this).load(fStr1).into(adminImageView);
    }
}
