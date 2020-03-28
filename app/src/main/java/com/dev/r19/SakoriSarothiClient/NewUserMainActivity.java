package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class NewUserMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView setMarqueeContent;
    private TextView userText_1;
    private TextView text;
    private TextView myName;
    private ImageView image;
    private Animation blink;
    private Animation smalltobig;
    // for navigation header view
    private View navHeaderView;
    //for rotate image
    private ImageView rotateImage12;
    private int[] imageArray12;
    private int curIndex12;
    private int startIndex12;
    private int endIndex12;
    //firebase
    private FirebaseUser user;
    //variable to store the username
    private String myUserName;
    private String myUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_main);
        //init activity var
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //just delete it
        FirebaseMessaging.getInstance().subscribeToTopic("Oil");


        userText_1 = (TextView) findViewById(R.id.userText_1);
        // for the navigation header view
        navHeaderView = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);
        myName = navHeaderView.findViewById(R.id.cur_user_email);
        myName.setText("robin");
        //getting the name
        getMyUserName();

        userText_1.setTranslationX(800);

        userText_1.animate().translationX(0).setDuration(1500).setStartDelay(900).start();

        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");//font style

        userText_1.setTypeface(mmedium);

        smalltobig = AnimationUtils.loadAnimation(this , R.anim.smalltobig);
        image = (ImageView) findViewById(R.id.image);
        image.startAnimation(smalltobig);

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
        // as you specify a parent activity in AndroidManifest.networkxml.
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
            Intent intent = new Intent(NewUserMainActivity.this, SubscribeToAChannel.class);
            startActivity(intent);

        } else if (id == R.id.nav_updateProfile) {
            Intent intent = new Intent(NewUserMainActivity.this, PopUpTermsAndCondition.class);
            startActivity(intent);


        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(NewUserMainActivity.this, UserFeedback.class);
            startActivity(intent);

        } else if (id == R.id.nav_searchJob) {
            Intent intent = new Intent(NewUserMainActivity.this, UserSearchAllJob.class);
            startActivity(intent);
        } else if (id == R.id.nav_jobYouPrefer) {
            Intent intent = new Intent(NewUserMainActivity.this, UserSearchCustomJob.class);
            startActivity(intent);
        }else if (id == R.id.nav_chat) {
          //  Toast.makeText(NewUserMainActivity.this, "This Feature Will be Comming Soon", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(NewUserMainActivity.this, MyChat.class);
            startActivity(intent);
        }else if (id == R.id.nav_ourInfo) {
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
    private void getMyUserName() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            myUserEmail = user.getEmail();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("UserProfile");
        final Query query = ref.orderByChild("Email").equalTo(myUserEmail);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()) {
                    UserGetNameModel nameMod = dataSnapshot.getValue(UserGetNameModel.class);
                    myUserName = nameMod.getName();
                    myName.setText( myUserName);
                }
                else
                {
                    myName.setText(myUserEmail);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(NewUserMainActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                return;
            }
        });

    }
}
