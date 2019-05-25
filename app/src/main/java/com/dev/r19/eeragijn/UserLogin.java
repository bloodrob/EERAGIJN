package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserLogin extends AppCompatActivity {

    private EditText userId, Pass;
    private Button login, resetPassword, toUserReg;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        // initialization
        toUserReg = (Button)findViewById(R.id.to_uReg);
        userId = (EditText)findViewById(R.id.empUserId);
        Pass = (EditText)findViewById(R.id.empUserPass);
        login = (Button)findViewById(R.id.empLogIn);
        resetPassword = (Button)findViewById(R.id.UlogIn);

        auth = FirebaseAuth.getInstance();
        //log in listener
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String UserId = userId.getText().toString().trim();
                if (TextUtils.isEmpty(UserId)) {
                    Toast.makeText(UserLogin.this, "User Id must Required", Toast.LENGTH_SHORT).show();
                    return;
                }
                String password = Pass.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(UserLogin.this, "Password must required", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(UserId, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if (task.isSuccessful()) {
                       Toast.makeText(UserLogin.this, "Successfully Log In", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(UserLogin.this, NewUserMainActivity.class);
                       startActivity(intent);
                   }
                    }
                });
            }
        });
        // to reset password
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, ResetPassword.class);
                startActivity(intent);
            }
        });
        // to user registration
        toUserReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLogin.this, UserRegistration.class);
                startActivity(intent);
            }
        });
    }
}
