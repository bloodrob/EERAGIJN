package com.dev.r19.eeragijn;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.load.model.stream.MediaStoreStreamLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class UserDocumentUpload extends AppCompatActivity {
    private Button toSelfImage, toSignImage, toHslcImage, toHsImage, toGraduationImage, toPostgraduationImage, toPrcImage, toCastImage ,uploadsImage;
    private ImageView viewSelfImage, viewSignImage, viewHslcImage, viewHsImage,viewGraduationImage, viewPostGraduationImage, viewPrcImage, viewCastImage ;
    ProgressDialog pd;
    private Uri selfPathToFile, signPathToFile, hslcPathToFile, hsPathToFile, graduationPathToFile, postgraduationPathToFile, castPathToFile, prcPathToFile; // The path to the uploads document
    private static int PICK_IMAGE_REQUEST = 1, PICK_IMAGE_REQUEST1 = 2, PICK_IMAGE_REQUEST2 = 3, PICK_IMAGE_REQUEST3 = 4, PICK_IMAGE_REQUEST4 = 5, PICK_IMAGE_REQUEST5 = 6, PICK_IMAGE_REQUEST6 = 7, PICK_IMAGE_REQUEST7 = 8;  //Request code act as a instance variable. that should be equal to requested uploaded image code
    private String getCurrentId;
    //firebase
    private FirebaseStorage storage;
    private StorageReference ref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_document_upload);
        //initialization of Button
        toSelfImage = (Button)findViewById(R.id.Browse_self_image);
        toSignImage = (Button)findViewById(R.id.Browse_sign_image);
        toHslcImage = (Button)findViewById(R.id.Browse_hslc_image);
        toHsImage = (Button)findViewById(R.id.Browse_hs_image);
        toGraduationImage = (Button)findViewById(R.id.Browse_graduation_image);
        toPostgraduationImage = (Button)findViewById(R.id.Browse_posgraduation_image);
        toCastImage = (Button)findViewById(R.id.Browse_cast_image);
        toPrcImage = (Button)findViewById(R.id.Browse_prc_image);
        uploadsImage = (Button)findViewById(R.id.Uploads_document_image);
        //initialization of imageView
        viewSelfImage = (ImageView) findViewById(R.id.Self_imageView);
        viewSignImage = (ImageView)findViewById(R.id.sign_ImageView);
        viewHslcImage = (ImageView)findViewById(R.id.hslc_ImageView);
        viewHsImage = (ImageView)findViewById(R.id.hs_ImageView);
        viewGraduationImage = (ImageView)findViewById(R.id.graduation_ImageView);
        viewPostGraduationImage = (ImageView)findViewById(R.id.postgraduation_ImageView);
        viewCastImage = (ImageView)findViewById(R.id.cast_ImageView);
        viewPrcImage = (ImageView)findViewById(R.id.prc_ImageView);
        // set up onclick listener for Browse self image
        toSelfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseSelfImage();
            }
        });
        // set up onclick listener for Browse sign image
        toSignImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseSignImage();
            }
        });
        //set up onclick listener for Browse hslc image
        toHslcImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseHslcImage();
            }
        });
        // set up onclick listener for Browse hs Image
        toHsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseHsImage();
            }
        });
        //set up onclickListener for Browse graduation image
        toGraduationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseGraduationImage();
            }
        });
        //set up onclick listener for Browse PostGraduation image
        toPostgraduationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowsePostGraduationImage();
            }
        });
        //set up onclick listener for PRC image
        toPrcImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowsePrcImage();
            }
        });
        //set up onclick listener fro cast image
        toCastImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseCastImage();
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
        ref = storage.getReference("User Document");
        auth = FirebaseAuth.getInstance();
        getCurrentId = auth.getCurrentUser().getUid().toString().trim();

    }

    //starting the browseImage method
    private void BrowseSelfImage() {
        /*
        Intent intent = new Intent();
        intent.setType("image/*"); //setting the type to image
        intent.setAction(Intent.ACTION_GET_CONTENT); // set up to get the content of the selected type
        //createChooser is used to open an dialog i.e leads to your gallary or the document directory and startActivityFor Result receive the seleced result i.e the image in this case
        startActivityForResult(Intent.createChooser(intent, "Select Your Image"), PICK_IMAGE_REQUEST); */

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    //starting the BrosweSignImage method
    private void BrowseSignImage() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent1,PICK_IMAGE_REQUEST1 );
    }
    //starting the BrowseHslcImage
    private void BrowseHslcImage() {
        Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent2, PICK_IMAGE_REQUEST2);
    }
    //starting BrowseHsImage method
    private void BrowseHsImage(){
        Intent intent3 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent3, PICK_IMAGE_REQUEST3);
    }
    //starting BrowseGraduationImage method
    private void BrowseGraduationImage(){
        Intent intent4 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent4, PICK_IMAGE_REQUEST4);
    }
    //starting BrowsePostGraduationImage method
    private void BrowsePostGraduationImage(){
        Intent intent5 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent5, PICK_IMAGE_REQUEST5);
    }
    //starting BrowsePrcImage method
    private void BrowsePrcImage(){
        Intent intent6 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent6, PICK_IMAGE_REQUEST6);
    }
    //starting Browse CastImage method
    private void BrowseCastImage(){
        Intent intent7 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent7, PICK_IMAGE_REQUEST7);
    }
    // onActivityResult method is to display or view the received image. The method receive a request code, result code and data. So here request code will be equals to PICK_IMAGE_REQUEST and result code will be result ok and the data will be avaiable so that image can be displayed
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //for self image
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selfPathToFile = data.getData();
            Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selfPathToFile);
                viewSelfImage.setImageBitmap(bitmap);
           // } catch (FileNotFoundException e) {
            //    e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // for sign image
        if (requestCode == PICK_IMAGE_REQUEST1 && resultCode == Activity.RESULT_OK) {
            signPathToFile = data.getData();
            Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), signPathToFile);
                viewSignImage.setImageBitmap(bitmap1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // for hslc image
        if (requestCode == PICK_IMAGE_REQUEST2 && resultCode == Activity.RESULT_OK) {
            hslcPathToFile = data.getData();
            Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), hslcPathToFile);
                viewHslcImage.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //for hs image
        if (requestCode == PICK_IMAGE_REQUEST3 && resultCode == Activity.RESULT_OK) {
            hsPathToFile = data.getData();
            Toast.makeText(UserDocumentUpload.this, "UPloading...", Toast.LENGTH_SHORT).show();
            try {
                Bitmap bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), hsPathToFile);
                viewHsImage.setImageBitmap(bitmap3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //for graduation image
       if (requestCode == PICK_IMAGE_REQUEST4 && resultCode == Activity.RESULT_OK) {
           graduationPathToFile = data.getData();
           Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
           try {
               Bitmap bitmap4 = MediaStore.Images.Media.getBitmap(getContentResolver(), graduationPathToFile);
               viewGraduationImage.setImageBitmap(bitmap4);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       //for PostGradutaion image
       if (requestCode == PICK_IMAGE_REQUEST5 && resultCode == Activity.RESULT_OK){
           postgraduationPathToFile = data.getData();
           Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
           try {
               Bitmap bitmap5 = MediaStore.Images.Media.getBitmap(getContentResolver(), postgraduationPathToFile);
               viewPostGraduationImage.setImageBitmap(bitmap5);
           }catch (IOException e) {
               e.printStackTrace();
           }
       }
       //for PRC image
       if (requestCode == PICK_IMAGE_REQUEST6 && resultCode == Activity.RESULT_OK) {
           prcPathToFile = data.getData();
           Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
           try {
               Bitmap bitmap6 = MediaStore.Images.Media.getBitmap(getContentResolver(), prcPathToFile);
               viewPrcImage.setImageBitmap(bitmap6);
           } catch(IOException e) {
               e.printStackTrace();
           }
       }
       //for cast image
       if (requestCode == PICK_IMAGE_REQUEST7 && resultCode == Activity.RESULT_OK) {
           castPathToFile = data.getData();
           Toast.makeText(UserDocumentUpload.this, "Uploading...", Toast.LENGTH_SHORT).show();
           try {
               Bitmap  bitmap7 = MediaStore.Images.Media.getBitmap(getContentResolver(), castPathToFile);
               viewCastImage.setImageBitmap(bitmap7);
           }catch (IOException e) {
               e.printStackTrace();
           }
       }
        else {
            Toast.makeText(UserDocumentUpload.this, "Unable to choose the image", Toast.LENGTH_SHORT).show();
        }
    }
    // performing the uplaode operation
    private void UploadImage() {
        //pathTofile is not null
        if (selfPathToFile != null) {
            // set up a progessbar to check the progress of uploading image
            pd = new ProgressDialog(this);
            pd.setTitle("Uplaoding your document... please don't press back");
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            // upload ref for self image
            StorageReference ref1 = ref.child("SelfImage" + getCurrentId.toString());
            //on succes of document upload
            ref1.putFile(selfPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //    pd.dismiss();
               //     Toast.makeText(UserDocumentUpload.this, "Document Succesfully uploaded", Toast.LENGTH_SHORT).show();
                }
            });
            // on failure of document upload
            ref1.putFile(selfPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            });// end of upload self image
            // upload ref for sign image
            StorageReference ref2 = ref.child("SignImage" + getCurrentId.toString());
            ref2.putFile(signPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                 //   pd.dismiss();
                }
            });
            ref2.putFile(signPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of upload sign image
            // upload ref for hslc image
            StorageReference ref3 = ref.child("HslcImage" + getCurrentId.toString());
            ref3.putFile(hslcPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                }
            });
            ref3.putFile(hslcPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of upload hslc image
            // upload ref for hs
            StorageReference ref4 = ref.child("HsImage"+getCurrentId.toString());
            ref4.putFile(hsPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
            ref4.putFile(hsPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of hs
            // upload ref for graduation
            StorageReference ref5 = ref.child("Graduation"+getCurrentId.toString());
            ref5.putFile(graduationPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
            ref5.putFile(graduationPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); //end of graduation
            //upload ref for Postgraduation
            StorageReference ref6 = ref.child("PostGraduationImage"+getCurrentId.toString());
            ref6.putFile(postgraduationPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
            ref6.putFile(postgraduationPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of postgraduation
            // upload ref for PRC
            StorageReference ref7 = ref.child("PRC"+getCurrentId.toString());
            ref7.putFile(prcPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
            ref7.putFile(prcPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of PRC
            //upload ref for CAST
            StorageReference ref8 = ref.child("Cast"+getCurrentId.toString());
            ref8.putFile(castPathToFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Document Succesfully uploaded", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserDocumentUpload.this, SuccessDocumentUpload.class);
                    startActivity(intent);
                }
            });
            ref8.putFile(castPathToFile).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                    Toast.makeText(UserDocumentUpload.this, "Failed to upload document" +e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }); // end of cast
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
