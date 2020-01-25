package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class UserRegistration extends AppCompatActivity {

    private EditText userId, pass, conPass;
    private Button Ureg;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);



        //initialization

        userId = (EditText)findViewById(R.id.IdToAccess);
        pass = (EditText)findViewById(R.id.Upass);
        conPass = (EditText)findViewById(R.id.UconPass);
        Ureg = (Button)findViewById(R.id.EnterReg);

        auth = FirebaseAuth.getInstance();
        // Set up onclick listener for Registration button

        Ureg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  convert and get string and varify the string
                String UserId = userId.getText().toString().trim();
                if (TextUtils.isEmpty(UserId)) {
                    Toast.makeText(UserRegistration.this, "User Id must required", Toast.LENGTH_SHORT).show();
                    return;
                }
                String Password = pass.getText().toString().trim();
                if (TextUtils.isEmpty(Password) && Password.length()<7)  {
                    Toast.makeText(UserRegistration.this, "Password Must required and lenght must be 7", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ConPassword = conPass.getText().toString().trim();
                if (!ConPassword.equals(Password)) {
                    Toast.makeText(UserRegistration.this, "Password must be same", Toast.LENGTH_SHORT).show();
                    return;
                }
                    // crate registration credential for the user
               auth.createUserWithEmailAndPassword(UserId, Password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               // checking for exsiting email and set exception
                               if (! task.isSuccessful()) {
                                   if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(UserRegistration.this, "This e-mail address is already registerd in the system, You need to try with your own original e-mail", Toast.LENGTH_SHORT).show();
                                       return;
                                   }
                               }
                           if (task.isSuccessful()) {
                               Toast.makeText(UserRegistration.this, "Successfully registerd to the system", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(UserRegistration.this, UserLogin.class);
                               startActivity(intent);
                           }
                           }
                       });

            }
        });
    }
}
