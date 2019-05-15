package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
                // uploading the file
                pathToPdf = data.getData();
                getThePdfName.setText(data.getDataString());
                Toast.makeText(AdminUploadJobDetailsPdf.this, "File Selected", Toast.LENGTH_SHORT).show();
            }
            if (data.getData() == null){
                Toast.makeText(AdminUploadJobDetailsPdf.this, "File not Selected", Toast.LENGTH_SHORT).show();
            }
        }
    }
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
}
