package com.dev.r19.eeragijn;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.LiveFolders.INTENT;
import static com.dev.r19.eeragijn.R.id.email;
import static com.dev.r19.eeragijn.R.id.start;

public class SendGeneratedIdToUser extends AppCompatActivity {
    private Button backToHome, backToResend, tapToPush;
    private TextView viewSuccessMsg, viewFailureMsg;
    // string to get the value from another class
    static String getTheId;
    // get the E-mail from another class
    static String getTheEmail;
    // subject of the email
    static final String subject = "You have successfully varified to Generate The User employee Id";
    // message of the email
    static final String message = "This is a System Generated mail form the app, No need to reply this message. Your Employee id is : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_generated_id_to_user);
        // initialization
        backToHome = (Button)findViewById(R.id.back_to_home);
        backToResend = (Button)findViewById(R.id.back_to_resend);
        tapToPush = (Button)findViewById(R.id.push_user_data);
        viewSuccessMsg = (TextView)findViewById(R.id.view_success_msg);
        viewFailureMsg = (TextView)findViewById(R.id.view_failure_msg);
        //ToHome
        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendGeneratedIdToUser.this, AdminMainActivity.class);
                startActivity(intent);
            }
        });
        //ToBack
        backToResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendGeneratedIdToUser.this, AdminGenerateNewEEId.class);
                startActivity(intent);
            }
        });
        //getting the id from AdminGeneratedNewEEid
        getTheId = AdminGenerateNewEEId.getIdAccessToAll.toString().trim();
        // getting the email of the derired user from SelectedNewUserByDistrict Class
        getTheEmail = SelectedNewUserByDistrict.takeEmailId.toString().trim();
        Toast.makeText(SendGeneratedIdToUser.this, "Your Email is :" +getTheEmail, Toast.LENGTH_SHORT).show();
        //method declare for checking the internet Connection
        // isOnline();
        //send id to the user through e-mail using intent
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("text/plain");
       // intent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{getTheEmail} );
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message +getTheId);
        // sure to prompt e-mail client only
      //  intent.setType("message/rcf822");
        startActivity(intent);
       // startActivity(Intent.createChooser(intent, "for thr e-mail client : "+getTheEmail));
        //push data to the user
        tapToPush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendGeneratedIdToUser.this, AdminPushUserData.class);
                startActivity(intent);
            }
        });
    }
   /* public Boolean isOnline() {
        // creating instance for get device connection service
        ConnectivityManager conmg = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // ref the instance to check network information of device
        NetworkInfo netInfo = conmg.getActiveNetworkInfo();
        if (netInfo !=null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        Toast.makeText(SendGeneratedIdToUser.this, "Your Device Network Is not Working, Please check the intenet Connectionnad Try Again", Toast.LENGTH_SHORT).show();
        return false;
    } */
}
