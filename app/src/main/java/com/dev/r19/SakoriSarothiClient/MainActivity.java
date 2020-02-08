package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView ivLogo ,ivSubtitle ,ivBtn;
    ImageView ivSplash , ivIcon;
    Animation smalltobig , lefttoright , fleft , fhelper;

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
        ivIcon = (ImageView) findViewById(R.id.ivIcon);

        ivLogo.setTypeface(logoX);
        ivSubtitle.setTypeface(mlight);
        ivBtn.setTypeface(mmedium);


        ivIcon.startAnimation(smalltobig);
        ivSplash.startAnimation(smalltobig);
        ivLogo.setTranslationX(400);
        ivSubtitle.setTranslationX(400);
        ivBtn.setTranslationX(400);

        ivLogo.animate().translationX(0).setDuration(800).setStartDelay(500).start();
        ivSubtitle.animate().translationX(0).setDuration(800).setStartDelay(700).start();
        ivBtn.animate().translationX(0).setDuration(800).setStartDelay(900).start();

        ivBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent ax = new Intent(MainActivity.this , UnSignUserMainActivity.class);
                startActivity(ax);
                overridePendingTransition(R.anim.fleft, R.anim.fhelper);
            }
        });
    }
}
