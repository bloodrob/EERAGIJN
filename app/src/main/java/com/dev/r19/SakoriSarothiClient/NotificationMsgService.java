package com.dev.r19.SakoriSarothiClient;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class NotificationMsgService extends FirebaseMessagingService {
    private FirebaseUser user;
    // private Notification myNotification;
    private static final String NOTIFICATION_CHANNEL_ID = "my_channel_id3";
    private static final String TAG = "Notification_Message";
    private Intent intent;
    private String myJobName;
    private String myJobDate;

    private NotificationManager notiMan;
    private Context mContext;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       // Log.d(TAG, remoteMessage.toString());

        Log.d(TAG, "From" +remoteMessage.getFrom());

        //check if message contaon data payload
       if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Data PlayLoad" + remoteMessage.getData());
            Map<String, String> payLoad = remoteMessage.getData();
            myJobName = remoteMessage.getData().get("title");
            myJobDate = remoteMessage.getData().get("body");
            showNotification(myJobName, myJobDate);

        }

        // check is message conatain notification payload
       /* if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notification Body" +remoteMessage.getNotification().getBody());
        } */

        //showNotification(myJobName, myJobDate);
        super.onMessageReceived(remoteMessage);
    }
    public void showNotification(String myJobName, String myJobDate) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
            intent = new Intent(mContext.getApplicationContext(), UserSearchCustomJobDetails.class);
            intent.putExtra("id", "notiSerJob");
            intent.putExtra("title", myJobName);
        }
        else {
            intent = new Intent(this, UserLogin.class);
        }
        int notificationId = (int)System.currentTimeMillis();
        int reqstTime = (int)System.currentTimeMillis() * 2;

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent penIntent = PendingIntent.getActivity(mContext, reqstTime, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //NotificationCompat.Builder notiBulid = new NotificationCompat().Builder(this.getApplicationContext());
        NotificationCompat.Builder notiBulid = new NotificationCompat.Builder(mContext.getApplicationContext(), "notify_1993");
        notiBulid.setAutoCancel(true).
                setDefaults(Notification.DEFAULT_ALL).
                setWhen(System.currentTimeMillis()).
                setSmallIcon(R.drawable.userhomelogo).
                setContentTitle("Job Name:" +myJobName).
                setContentText(myJobDate);
        notiBulid.setContentIntent(penIntent);
        //  myNotification = notiBulid.build();
        notiMan = (NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE);
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notiBulid.setColor(Color.parseColor("#1d099e"));
        } */

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notiChan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "SAKORI_SAROTHI_CHANNEL", importance);
            notiChan.enableLights(true);
            notiChan.setLightColor(Color.GREEN);
            notiChan.enableVibration(true);
            notiChan.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notiBulid.setChannelId(NOTIFICATION_CHANNEL_ID).
                    setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE);
            notiMan.createNotificationChannel(notiChan);
            //notiMan.notify(notificationId, notiBulid.build());
        }
        Log.d("channel Id :", " " + NOTIFICATION_CHANNEL_ID);
        // myNotification = notiBulid.build();
        notiMan.notify(notificationId, notiBulid.build());

    }
}
