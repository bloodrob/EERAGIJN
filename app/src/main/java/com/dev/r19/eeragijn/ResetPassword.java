package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private EditText regEmail;
    private Button submitEmail;
    // firebase work
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        // initializing the button
        regEmail = (EditText)findViewById(R.id.reg_mail);
        submitEmail = (Button)findViewById(R.id.submit_email);
        // initialization auth
        auth = FirebaseAuth.getInstance();
        //on click listener
        submitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // method to reset the pass
                resPassWord();
            }
        });
    }
    private void resPassWord() {
        String OfMyEmail = regEmail.getText().toString().trim();
        // firebase work on success database
        auth.sendPasswordResetEmail(OfMyEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ResetPassword.this, "Please check your email for password recovery", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ResetPassword.this, UserLogin.class);
                    startActivity(intent);
                }
            }
        });
        // firebase work for database failure
        auth.sendPasswordResetEmail(OfMyEmail).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ResetPassword.this, "Database Failure, Please Contact to the Database Administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }
}
