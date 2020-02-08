package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class UserHome extends AppCompatActivity {

    private Button login, reg;
    private TextView userHome;
    private Animation rotate;
    private FirebaseUser auth;
    private TextView userHomeText;
    private Animation blink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        //initialization

        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");//font style

        userHome = (TextView) findViewById(R.id.userHome);
        login = (Button)findViewById(R.id.toUserLogin);
        reg = (Button)findViewById(R.id.toUserReg);

        blink = AnimationUtils.loadAnimation(this , R.anim.blink_anim);
        userHomeText = (TextView)findViewById(R.id.user_home_text);
        userHomeText.startAnimation(blink);
        userHomeText.setSelected(true);

        userHome.setTypeface(mmedium);//text style

        // checking for session status
        auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth != null) {
            Intent intent = new Intent(UserHome.this, NewUserMainActivity.class);
            startActivity(intent);
        }

        //text animation
        rotate = AnimationUtils.loadAnimation(this , R.anim.rotate_1);
        userHome = (TextView) findViewById(R.id.userHome);
        userHome.startAnimation(rotate);

        // start link page user login
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Animation animation = AnimationUtils.loadAnimation(UserHome.this , R.anim.bounce);
              login.startAnimation(animation);
              Intent intent = new Intent(UserHome.this, UserLogin.class);
              startActivity(intent);
          }
      });
        ; //end
        //start link user reg page
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Animation animation = AnimationUtils.loadAnimation(UserHome.this , R.anim.bounce);
               reg.startAnimation(animation);
               Intent intent = new Intent(UserHome.this, UserRegistration.class);
               startActivity(intent);
           }
       });


    }
}
