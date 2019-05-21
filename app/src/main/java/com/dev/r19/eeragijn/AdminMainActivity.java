package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    private Button toCheckNewUser, toSendNotification, toExistingUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        //initialization
        toCheckNewUser = (Button)findViewById(R.id.to_check_new_user);
        toSendNotification = (Button)findViewById(R.id.to_send_notification);
        toExistingUser = (Button)findViewById(R.id.to_check_existing_user);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //link to search new user page
        toCheckNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, SearchNewUserByDistrict.class);
                startActivity(intent);
            }
        });
        //link to send notification
        toSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, AdminSendJobNotification.class);
                startActivity(intent);
            }
        });
        //link to check Existing user
        toExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainActivity.this, SearchAllExistingUserByDistrict.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }
    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home_drawer, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home )
        {
            Toast.makeText( this , "This is home ",Toast.LENGTH_SHORT ).show();
        }
        if(id == R.id.nav_check_new_appli)
        {
            Intent intent = new Intent(AdminMainActivity.this, SearchNewUserByDistrict.class);
            startActivity(intent);
        }
        if (id == R.id.nav_send_job_noti){
            Intent intent = new Intent(AdminMainActivity.this, AdminSendJobNotification.class);
            startActivity(intent);
        }
        if (id == R.id.nav_check_exist_appli) {
            Intent intent = new Intent(AdminMainActivity.this, AdminSearchExistingUser.class);
            startActivity(intent);
        }
        DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
