package com.dev.r19.eeragijn;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Notification;
import android.app.NotificationManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminSendJobNotification extends AppCompatActivity {

    //variable for edittext and button
    private EditText nameOfJob, subjectOfJod, detailsOfJod;
    private Button sendJodNoti;
    //static string for input value
    static String JobName, JobSubject, JobDetails;
    // To intensiate object for use in notification class;
    private Notification Notfi;
    //notification builder for format the notification
    Notification.Builder notiBuild;
    //to intensiate object in perpose to push notification
    private NotificationManager NotiMan;
    //set a id for the channle;
    String Channel_id = "my_channel_01";
    //parameter of notification channel to set up priority
    int important;
    // variable for notification channel object intensiate
    NotificationChannel mChannel;
    //Character sequence for the channel name to use in notification channel
    CharSequence nameOfChannel;
    // set up a notification id so it can be updated
    int notifyID = 1;
    //use to get system times
    int requesttime;
    // firebase to store job details
    FirebaseDatabase database;
    DatabaseReference ref;
    //set up a annotaion for comtable with adnroid version. here it is for Oreo. Api level 26
    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_send_job_notification);
        //initiazing edittext and button
        nameOfJob = (EditText)findViewById(R.id.name_of_job);
        subjectOfJod = (EditText)findViewById(R.id.subject_of_job);
        detailsOfJod = (EditText)findViewById(R.id.details_of_job);
        sendJodNoti = (Button)findViewById(R.id.send_job_noti);
        //sendJobNoti method start
        sendJodNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 JobName = nameOfJob.getText().toString().trim();
                 JobSubject = subjectOfJod.getText().toString().trim();
                 JobDetails = detailsOfJod.getText().toString().trim();
                //Context for use in setLatestEventInfo
                Context context = getApplicationContext();
                //set up notification priority
                important = NotificationManager.IMPORTANCE_HIGH;
                //set up channel name
                nameOfChannel = "my_channel";
                //create a notification and set the notification channel
                mChannel = new NotificationChannel(Channel_id, nameOfChannel, important);

                //Notification compact bulider to support android version 8 or higher than 8
                //NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(AdminSendJobNotification.this).setSmallIcon(R.drawable.notilogoweb).setContentTitle(JobName).setContentText(JobSubject).setContentTitle(JobDetails);
                //object intentiate of notificationManager class by requasting the android system through getSystemService Method
                NotiMan = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                //implicit intent
                Intent notIntent = new Intent(AdminSendJobNotification.this, ViewAdminUploadedJobDetails.class);
                notIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                requesttime = (int) System.currentTimeMillis();
                //creating PendinIntent to grant the right to perform operation that we specified i.e give permision to open the app through notification
                PendingIntent penIntent = PendingIntent.getActivity(AdminSendJobNotification.this, requesttime, notIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                //crating notification through notification class with its attributes;
                notiBuild = new Notification.Builder(getApplicationContext()).setContentTitle(JobName).setContentText(JobSubject).setContentTitle(JobDetails).setSmallIcon(R.drawable.notilogoweb).setChannelId(Channel_id).setContentIntent(penIntent);
                Notfi = notiBuild.build();
                //call setLatestEvent method of noticication class and pass the pendingIntent along with notification subject and body
               // Notfi.setLatestEventInfo(context, JobSubject, JobDetails, penIntent);
               // NotiMan.set
                //checking the android version compatability here it is for oreo
              /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotiMan.createNotificationChannel(mChannel);
                }*/
              //  Notfi.flags |= Notification.FLAG_AUTO_CANCEL;
                // now push the information by the method notify
                NotiMan.notify(notifyID, Notfi);

                // firebase work to save the data
                database = FirebaseDatabase.getInstance();
                ref = database.getReference("UploadedJobDetails");
                // mthod to assign key value
                SaveJobDetails(JobName, JobSubject, JobDetails);
                Intent intent = new Intent(AdminSendJobNotification.this, AdminUploadJobDetailsPdf.class);
                startActivity(intent);
            }
        });
    }
    // method for save data
    private void SaveJobDetails(String JobName, String JobSubject, String JObDetails) {
        JobUploadDetailsModel jobUp = new JobUploadDetailsModel(JobName, JobSubject, JobDetails);
        JobName = jobUp.Jobname;
        ref.child(JobName).setValue(jobUp);
        // method to save data
        CreateToSaveData();
    }
    private void CreateToSaveData(){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                JobUploadDetailsModel JobUp = dataSnapshot.getValue(JobUploadDetailsModel.class);
                if (JobUp == null) {
                    Toast.makeText(AdminSendJobNotification.this, "No Dta Entered", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(AdminSendJobNotification.this, "Data is suceessfully save to the database", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminSendJobNotification.this, "Something Wrong, Contact To Database Administrator", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}
