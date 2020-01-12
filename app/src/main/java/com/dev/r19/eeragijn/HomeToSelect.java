package com.dev.r19.eeragijn;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class HomeToSelect extends AppCompatActivity {

    private TextView home;
    private Animation blink;
    private Button emp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_to_select);
        emp = (Button)findViewById(R.id.AsEmp);

        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");//font style

        blink = AnimationUtils.loadAnimation(this , R.anim.blink_anim);
        home = (TextView) findViewById (R.id.home);
        home.startAnimation(blink);

        home.setTypeface(mmedium);//text style
        // link to user home
        emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeToSelect.this, UserHome.class);
                startActivity(intent);
            }
        });
    }
}
