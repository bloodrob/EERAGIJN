package com.dev.r19.eeragijn;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NewUserMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView setMarqueeContent;
    private TextView userText_1;
    private  TextView text;
    private ImageView adminLogo;
    private Animation blink;
    //for rotate image
    private ImageView rotateImage12;
    private int[] imageArray12;
    private int curIndex12, startIndex12, endIndex12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userText_1 = (TextView) findViewById(R.id.userText_1);



        userText_1.setTranslationX(800);

        userText_1.animate().translationX(0).setDuration(1500).setStartDelay(900).start();

        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");//font style

        userText_1.setTypeface(mmedium);



        blink = AnimationUtils.loadAnimation(this , R.anim.blink_anim);
        text = (TextView) findViewById (R.id.Text_1);
        text.startAnimation(blink);
        //for rotate image
        rotateImage12 = (ImageView)findViewById(R.id.rotate_img12);
        imageArray12 = new int[4];
        imageArray12[0] = R.drawable.img_1;
        imageArray12[1] = R.drawable.img_2;
        imageArray12[2] = R.drawable.img_3;
        imageArray12[3] = R.drawable.img_4;
        startIndex12 = 0;
        endIndex12 = 3;
        //end
        //method of swapping image within time interval
        sweepNextImage12();

        text.setTypeface(mmedium);//text style

        setMarqueeContent = (TextView)findViewById(R.id.set_moving_content_textview);
        setMarqueeContent.setSelected(true);
        //  setMarqueeContent.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // end of BUtton



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_user_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_updateProfile) {
            Intent intent = new Intent(NewUserMainActivity.this, PopUpTermsAndCondition.class);
            startActivity(intent);


        } else if (id == R.id.nav_checkStatus) {
            Intent intent = new Intent(NewUserMainActivity.this, UserCheckStatus.class);
            startActivity(intent);

        } else if (id == R.id.nav_searchJob) {
            Intent intent = new Intent(NewUserMainActivity.this, UserSearchAllJob.class);
            startActivity(intent);
        }else if (id == R.id.nav_chat) {
            Toast.makeText(NewUserMainActivity.this, "This Feature Will be Comming Soon", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.nav_aboutUs) {
            Intent intent = new Intent(NewUserMainActivity.this, AboutUs.class);
            startActivity(intent);
        }else if (id == R.id.nav_contactUs) {
            Intent intent = new Intent(NewUserMainActivity.this, ContactUs.class);
            startActivity(intent);
        }else if (id == R.id.nav_signOut) {
            Intent intent = new Intent(NewUserMainActivity.this, UserSignOut.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // fnction for the method sweepNextImage
    public void sweepNextImage12() {
        rotateImage12.setImageResource(imageArray12[curIndex12]);
        Animation animRote = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        rotateImage12.startAnimation(animRote);
        curIndex12 ++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // checking condition for if the current index is greater than end Index
                if (curIndex12>endIndex12) {
                    curIndex12 --;
                    // method for sweep prvious image
                    sweepPreviousImage12();
                }
                else {
                    sweepNextImage12(); // here we give 4 second interval to previous from current
                }
            }
        }, 4000);
    }
    public void sweepPreviousImage12() {
        rotateImage12.setImageResource(imageArray12[curIndex12]);
        Animation animRote = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        rotateImage12.startAnimation(animRote);
        curIndex12 --;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // checking condition for if the start index is greater than current Index
                if (curIndex12<startIndex12) {
                    curIndex12 ++;
                    // method for sweep next image
                    sweepNextImage12();
                }
                else {
                    sweepPreviousImage12();// here we give 4 second interval to current from previous
                }
            }
        }, 4000);
    }
}
