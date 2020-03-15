package com.dev.r19.SakoriSarothiClient;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private TextView ivLogo;
    private TextView ivSubtitle;
    private TextView ivBtn;
    private ImageView ivSplash;
    private Animation smalltobig;
    public Animation lefttoright;
    public Animation fleft;
    public Animation fhelper;
    private NetworkInfo nInfo;
    private ConnectivityManager cman;
    private Intent notiIntent;

    private GoogleSignInAccount mySignInAccount;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smalltobig = AnimationUtils.loadAnimation(this , R.anim.smalltobig);
        lefttoright = AnimationUtils.loadAnimation(this , R.anim.lefttoright);
        fleft = AnimationUtils.loadAnimation(this , R.anim.fleft);
        fhelper = AnimationUtils.loadAnimation(this , R.anim.fhelper);

        Typeface logoX = Typeface.createFromAsset(getAssets(), "fonts/Fredoka.ttf");
        Typeface mlight = Typeface.createFromAsset(getAssets(), "fonts/MontserratLight.ttf");
        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");
        Typeface mregular = Typeface.createFromAsset(getAssets(), "fonts/MontserratRegular.ttf");


        ivLogo = (TextView) findViewById(R.id.ivLogo);
        ivSubtitle = (TextView) findViewById(R.id.ivSubtitle);
        ivBtn = (TextView) findViewById(R.id.ivBtn);
        ivSplash = (ImageView) findViewById(R.id.ivSplash);

        ivLogo.setTypeface(logoX);
        ivSubtitle.setTypeface(mlight);
        ivBtn.setTypeface(mmedium);


        ivSplash.startAnimation(smalltobig);
        ivLogo.setTranslationX(400);
        ivSubtitle.setTranslationX(400);
        ivBtn.setTranslationX(400);

        ivLogo.animate().translationX(0).setDuration(800).setStartDelay(500).start();
        ivSubtitle.animate().translationX(0).setDuration(800).setStartDelay(700).start();
        ivBtn.animate().translationX(0).setDuration(800).setStartDelay(900).start();

        mySignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        //check the notification intent value
        notiIntent = getIntent();
        if (notiIntent.hasExtra("id")) {
            // get and put intent to the other page
            getAndTransferNotification();
        }

        // check network connectivity
        isMOnline();

        ivBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                isMOnline();
                if (mySignInAccount != null){
                    Intent gIntent = new Intent(MainActivity.this, NewUserMainActivity.class);
                    startActivity(gIntent);
                }

                if (user != null) {
                    Intent ax = new Intent(MainActivity.this , NewUserMainActivity.class);
                    startActivity(ax);
                }
                else {
                    Intent intent = new Intent(MainActivity.this, UnSignUserMainActivity.class);
                    startActivity(intent);
                }
                overridePendingTransition(R.anim.fleft, R.anim.fhelper);
            }
        });
    }
    private void isMOnline(){
        cman = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        nInfo = cman.getActiveNetworkInfo();
        if (nInfo != null && nInfo.isConnected()) {
            Toast.makeText(this, "You Online", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No Internet Connection, Please Check Your Network Connection", Toast.LENGTH_SHORT).show();
            // intent to Device Network setting
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            //intent.sett
            // intent.setClassName("com.android.phone","com.android.phone.NetworkSetting");
            startActivity(intent);
            return;
        }
    }
    private void getAndTransferNotification() {
        Intent sendIntent = new Intent(MainActivity.this, UserSearchCustomJobDetails.class);
        sendIntent.putExtra("id", notiIntent.getStringExtra("id"));
        sendIntent.putExtra("title", notiIntent.getStringExtra("title"));
        startActivity(sendIntent);
    }

    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
