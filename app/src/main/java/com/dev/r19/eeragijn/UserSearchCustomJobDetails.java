package com.dev.r19.eeragijn;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import static com.dev.r19.eeragijn.AdminUploadJobDetailsPdf.JobDetails;
import static com.dev.r19.eeragijn.AdminUploadJobDetailsPdf.JobName;
import static com.dev.r19.eeragijn.AdminUploadJobDetailsPdf.JobSubject;

public class UserSearchCustomJobDetails extends AppCompatActivity {

    // button variable declare
    private EditText nameJob, categoryJob, lastDateJob, stipenSalaryJob, experienceJob, detailJob, checkAd;
    //to take the string from other page
    static String selectedNane;
    // progress dialogue
    private ProgressDialog pd1;

    // firebase variable for data
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseStorage firStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_custom_job_details);
        // button init
        nameJob = (EditText)findViewById(R.id.name_job);
        categoryJob = (EditText)findViewById(R.id.category_job);
        lastDateJob = (EditText)findViewById(R.id.last_date_job);
        stipenSalaryJob = (EditText)findViewById(R.id.stipen_salary_job);
        experienceJob = (EditText)findViewById(R.id.experience_job);
        detailJob = (EditText)findViewById(R.id.detail_job);
        checkAd = (EditText)findViewById(R.id.check_ad);

        // init of firebase variable
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("UploadedJobDetails");
        // init of Progress bar
        pd1 = new ProgressDialog(this);
        pd1.setCanceledOnTouchOutside(false);
        pd1 = ProgressDialog.show(this, "Searching...", "Please Wait");

        //method of data retrive
        searchJobDetailData();
        // method for getting the pdf url
        getTheJobPdf();
    }
    // mathod for retrieve data
    private void searchJobDetailData() {
        pd1.show();
        //query to match data
        final Query query1 =ref.orderByChild("JobName").equalTo(selectedNane);
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        UserSeachCustomJobModel res1 = child.getValue(UserSeachCustomJobModel.class);
                    }
                }
                else {

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getTheJobPdf() {

    }
}
