package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSignOut extends AppCompatActivity {

    //for firebase auth instance and firebase current user
    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_out);
        // initialization and checking the current user;
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        user = auth.getCurrentUser();
        // checking if still user session is on
        if (user == null) {
            Toast.makeText(UserSignOut.this, "Successfully Sign Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserSignOut.this, UserHome.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
