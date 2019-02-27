package com.dev.r19.eeragijn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import static com.dev.r19.eeragijn.R.id.add;

public class DocumentUpload extends AppCompatActivity {
    Button toSelfImage, uploadsImage;
    ImageView viewSelfImage;
    ProgressDialog pd;
    private Uri pathToFile; // The path to the uploads document
    private static int PICK_IMAGE_REQUEST = 1;  //Request code act as a instance variable. that should be equal to requested uploaded image code
    //firebase
    FirebaseStorage storage;
    StorageReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);
        //initialization

        toSelfImage = (Button)findViewById(R.id.Browse_self_image);
        viewSelfImage = (ImageView) findViewById(R.id.Self_imageView);
        uploadsImage = (Button)findViewById(R.id.Uploads_self_image);
        // set up onclick listener for Browse image
        toSelfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
        // set up onclick listener for uploads image
        uploadsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImage();
            }
        });
        //get instance and reference to the node
        storage = FirebaseStorage.getInstance();
        ref = storage.getReference();
    }

    //starting the browseImage method
    private void BrowseImage() {
        /*
        Intent intent = new Intent();
        intent.setType("image/*"); //setting the type to image
        intent.setAction(Intent.ACTION_GET_CONTENT); // set up to get the content of the selected type
        //createChooser is used to open an dialog i.e leads to your gallary or the document directory and startActivityFor Result receive the seleced result i.e the image in this case
        startActivityForResult(Intent.createChooser(intent, "Select Your Image"), PICK_IMAGE_REQUEST); */

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    // onActivityResult method is to display or view the received image. The method receive a request code, result code and data. So here request code will be equals to PICK_IMAGE_REQUEST and result code will be result ok and the data will be avaiable so that image can be displayed
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            pathToFile = data.getData();
            Toast.makeText(DocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), pathToFile);
                viewSelfImage.setImageBitmap(bitmap);
           // } catch (FileNotFoundException e) {
            //    e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(DocumentUpload.this, "Unable to choose the image", Toast.LENGTH_SHORT).show();
        }
    }
    // performing the uplaode operation
    private void UploadImage() {
        //pathTofile is not null
        if (pathToFile != null) {
            // set up a progessbar to check the progress of uploading image
            pd = new ProgressDialog(this);
            pd.setTitle("Uplaoding your document......");
            pd.show();

            StorageReference ref1 = ref.child("image/" + UUID.randomUUID().toString());
            //on succes of document upload
            ref1.putFile(pathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(DocumentUpload.this, "Document Succesfully uploaded", Toast.LENGTH_SHORT).show();
                }
            });
            // on failure of document upload
            ref1.putFile(pathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                    Toast.makeText(DocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            });
            // set Up the dialog view box for the uploaded progress
         /*   ref1.putFile(pathToFile).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressbar.setMessage("uploaded" +(int)progress+"%");
                }
            }); */
        }
    }
}
