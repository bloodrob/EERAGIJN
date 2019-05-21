package com.dev.r19.eeragijn;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URISyntaxException;

public class AdminUploadJobDetailsPdf extends AppCompatActivity {

    // string to get the value from other class
    String getTheName;
    //button and imageview
    private Button choosePdf, uploadPdf;
    private TextView showSuccessPdfUpload, getThePdfName;
    // uri to set a path to store the file
    private Uri pathToPdf;
    // use in the file chooser as a passed code
    final static int PICK_PDF_CODE = 2342;
    //Firebase variable
    FirebaseStorage database;
    StorageReference stref;
    // for set up progressbar
    ProgressDialog pdD;
    // static string
    static String getTheFileName;
    // Context and Uri to use in get the file path from uri
    Context context1;
    Uri uri1;
    //static string to passed the data from afunction
    static String myResult;
    //set up a annotaion for comtable with adnroid version. here it is for Oreo. Api level 26
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_upload_job_details_pdf);
        //Initialization
        choosePdf = (Button)findViewById(R.id.choose_pdf);
        uploadPdf = (Button)findViewById(R.id.upload_pdf);
        showSuccessPdfUpload = (TextView)findViewById(R.id.show_success_upload_pdf);
        getThePdfName = (TextView)findViewById(R.id.get_the_pdf_name);
        // pogress dialog Initialization
        pdD = new ProgressDialog(this);
        pdD.setTitle("Uploading the file, Please don't press back.");
        pdD.setCanceledOnTouchOutside(false);
        // getting the string from AdminSendJobNotification class
        getTheName = AdminSendJobNotification.JobName.toString().trim();
        //listener for choose the image
        choosePdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method to choose image
                PdfChooser();
            }
        });
        // listener for uploadthe pdf
        uploadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PdfUploader();
            }
        });
    }
    // get the pdf from the storage
    private void PdfChooser(){
        // creating a intent for file Chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select Pdf"), PICK_PDF_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // check if the chooser choose file or not
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData()!=null) {
            //if file is selected
            if (data.getData()!= null) {
                // method define to get the name
                try {
                    getTheFileName(context1, uri1);
                    File fileName = new File(myResult);
                    getTheFileName = fileName.getName();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                // uploading the file
                pathToPdf = data.getData();
                getThePdfName.setText("Here your file to be uploaded, if it is correct then you can upload it" + getTheFileName);
                Toast.makeText(AdminUploadJobDetailsPdf.this, "File Selected", Toast.LENGTH_SHORT).show();
            }
            if (data.getData() == null){
                Toast.makeText(AdminUploadJobDetailsPdf.this, "File not Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // method to upload the pdf file to firebase
    private void PdfUploader() {
        pdD.show();
        database = FirebaseStorage.getInstance();
        stref = database.getReference("Uploaded Job Pdf/" +getTheName + System.currentTimeMillis() + ".pdf");
        stref.putFile(pathToPdf).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                pdD.dismiss();
                Toast.makeText(AdminUploadJobDetailsPdf.this, "Uploaded SuccessFully", Toast.LENGTH_SHORT).show();
                showSuccessPdfUpload.setText("Successfuly uploaded, You can Choose other file to upload or go back to previous page.");
            }
        });
        stref.putFile(pathToPdf).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminUploadJobDetailsPdf.this, "Uploaded Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // method declare for getting the file name
    private String getTheFileName(Context context1, Uri uri1) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri1.getScheme())) {
            String[] proj = {MediaStore.Files.FileColumns.DATA};
            Cursor cursor;
            try {
                cursor = context1.getContentResolver().query(uri1, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);
                if (cursor.moveToFirst()) {
                     myResult = cursor.getString(column_index);
                    return myResult;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("file".equalsIgnoreCase(uri1.getScheme())) {
            myResult =  uri1.getPath();
            return myResult;
        }
        return null;
    }
}
