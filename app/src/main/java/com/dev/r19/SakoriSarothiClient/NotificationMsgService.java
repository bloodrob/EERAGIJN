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
    public static final String NOTIFICATION_CHANNEL_ID = "my_channel_id2";
    public static final String TAG = "FireBase_Message";
    private Intent intent;
    private String myJobName;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, remoteMessage.toString());

        //check if message contaon data payload
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message Data PlayLoad" + remoteMessage.getData());
            Map<String, String> payLoad = remoteMessage.getData();
            showNotificatrion(payLoad.get("title"), payLoad.get("jobDate"));
            myJobName = payLoad.get("title").toString().trim();
        }

        // check is message conatain notification payload
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body" +remoteMessage.getNotification().getBody());
        }
    }
    public void showNotificatrion(String title, String jobDate) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show();
            intent = new Intent(this, NewUserMainActivity.class);
            intent.putExtra("title", myJobName);
        }
        int notificationId = (int)System.currentTimeMillis();

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent penIntent = PendingIntent.getActivity(this, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Notification.Builder notiBulid = new Notification.Builder(this.getApplicationContext());
        NotificationCompat.Builder notiBulid = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notiBulid.setAutoCancel(true).
                setDefaults(Notification.DEFAULT_ALL).
                setWhen(System.currentTimeMillis()).
                setSmallIcon(R.drawable.homelogo).
                setContentTitle("Job Name:" +title).
                setContentText(jobDate);

        //  myNotification = notiBulid.build();
        NotificationManager notiMan = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
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
            notiMan.notify(notificationId, notiBulid.build());
        }
        Log.d("channel Id :", " " + NOTIFICATION_CHANNEL_ID);
        // myNotification = notiBulid.build();
        notiMan.notify(notificationId, notiBulid.build());

    }
}
