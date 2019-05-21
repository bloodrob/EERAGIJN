package com.dev.r19.eeragijn;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView setMarqueeContent;

    // varible for custom function of image changing
    private ImageView rotateImg;
    private int[] imageArray;
    private int curImgIndex;
    private int startImgIndex;
    private int endImgIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Button Initilization

        setMarqueeContent = (TextView)findViewById(R.id.set_moving_content_textview);
        setMarqueeContent.setSelected(true);
        //  setMarqueeContent.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        // end of BUtton
        rotateImg = (ImageView) findViewById(R.id.rotate_img);
        //end
        //initization array and assign value
        imageArray = new int[2];
        imageArray[0] = R.drawable.loginlogo;
        imageArray[1] = R.drawable.logo1;
        startImgIndex = 0;
        endImgIndex = 1;
        //end
        //method of swapping image within time interval
        sweepNextImage();


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
        getMenuInflater().inflate(R.menu.admin_main, menu);
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
            // Handle camera action
        } else if (id == R.id.nav_sendJobNotification) {
            Intent intent = new Intent(AdminMainActivity.this, AdminSendJobNotification.class);
            startActivity(intent);

        } else if (id == R.id.nav_searchExistingUser) {
            Intent intent = new Intent(AdminMainActivity.this, AdminSearchExistingUser.class);
            startActivity(intent);

        } else if (id == R.id.nav_searchNewUser) {
            Intent intent = new Intent(AdminMainActivity.this, SearchNewUserByDistrict.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // fnction for the method sweepNextImage
    public void sweepNextImage() {
        rotateImg.setImageResource(imageArray[curImgIndex]);
        Animation animRote = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        rotateImg.startAnimation(animRote);
        curImgIndex ++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // checking condition for if the current index is greater than end Index
                if (curImgIndex>endImgIndex) {
                    curImgIndex --;
                    // method for sweep prvious image
                    sweepPreviousImage();
                }
                else {
                    sweepNextImage();
                }
            }
        }, 3000);
    }
    public void sweepPreviousImage() {
        rotateImg.setImageResource(imageArray[curImgIndex]);
        Animation animRote = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        rotateImg.startAnimation(animRote);
        curImgIndex --;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // checking condition for if the start index is greater than current Index
                if (curImgIndex<startImgIndex) {
                    curImgIndex ++;
                    // method for sweep next image
                    sweepNextImage();
                }
                else {
                    sweepPreviousImage();// here we give 3 second interval to current from previous
                }
            }
        }, 3000);
    }
}
