package com.dev.r19.eeragijn;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.Os;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Notification;
import android.app.NotificationManager;

import java.lang.annotation.Target;

public class AdminSendJobNotification extends AppCompatActivity {

    //variable for edittext and button
    private EditText nameOfJob, subjectOfJod, detailsOfJod;
    private Button sendJodNoti;
    // To intensiate object for use in notification class;
    private Notification Notfi;
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
                String JobName = nameOfJob.getText().toString().trim();
                String JobSubject = subjectOfJod.getText().toString().trim();
                String JobDetails = detailsOfJod.getText().toString().trim();
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
                //crating notification through notification class with its attributes;
                Notfi = new Notification.Builder(getApplicationContext()).setContentTitle(JobName).setContentText(JobSubject).setContentTitle(JobDetails).setSmallIcon(R.drawable.notilogoweb).build();
                //creating PendinIntent to grant the right to perform operation that we specified
                PendingIntent penIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
                //call setLatestEvent method of noticication class and pass the pendingIntent along with notification subject and body
              //  NotiMan.setLatestEventInfo(getApplicationContext(), JobSubject, JobDetails, penIntent);
               // NotiMan.set
                //checking the android version compatability here it is for oreo
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotiMan.createNotificationChannel(mChannel);
                }
                Notfi.flags |= Notification.FLAG_AUTO_CANCEL;
                // now push the information by the method notify
                NotiMan.notify(notifyID, Notfi);
                Intent intent = new Intent(AdminSendJobNotification.this, AdminMainActivity.class);
                startActivity(intent);

            }
        });
    }
}
