package com.dev.r19.SakoriSarothiClient;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.w3c.dom.Text;

public class UserRegistration extends AppCompatActivity {

    private EditText userId;
    private EditText pass;
    private EditText conPass;
    private EditText reqMobile;
    private Button Ureg;
    private Button submitReqMobile;
    private FirebaseAuth auth;
    private String UserId;
    private String Password;
    private String ConPassword;
    private String Mobile;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        //initialization
        initUtilityVar();
        auth = FirebaseAuth.getInstance();
        // for pd bar
        progessDialogWork();
        // Set up onclick listener for Registration button
        Ureg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserId = userId.getText().toString().trim();
                if (TextUtils.isEmpty(UserId)) {
                    Toast.makeText(UserRegistration.this, "User Id must required", Toast.LENGTH_SHORT).show();
                    return;
                }
                Password = pass.getText().toString().trim();
                if (TextUtils.isEmpty(Password) && Password.length()<7)  {
                    Toast.makeText(UserRegistration.this, "Password Must required and length must be 7", Toast.LENGTH_SHORT).show();
                    return;
                }
                ConPassword = conPass.getText().toString().trim();
                if (ConPassword.length()<7) {
                    Toast.makeText(UserRegistration.this, "Password length must be 7", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!ConPassword.equals(Password)) {
                    Toast.makeText(UserRegistration.this, "Password must be same", Toast.LENGTH_SHORT).show();
                    return;
                }
                regWithEmailPassword();
            }
        });

        submitReqMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mobile = reqMobile.getText().toString().trim();
                if (TextUtils.isEmpty(Mobile) && Mobile.length()>13 || Mobile.length()<10){
                    Toast.makeText(UserRegistration.this, "Must not empty and should be a valid number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Intent intent = new Intent(UserRegistration.this, MobileSIgnIn.class);
                    intent.putExtra("mobile", Mobile);
                    startActivity(intent);
                }
            }
        });
    }

    private void regWithEmailPassword(){
        pd.show();
        // crate registration credential for the user
        auth.createUserWithEmailAndPassword(UserId, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // checking for exsiting email and set exception
                        if (! task.isSuccessful()) {
                            pd.dismiss();
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(UserRegistration.this, "This e-mail address is already registerd in the system, You need to try with your own original e-mail", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            Toast.makeText(UserRegistration.this, "Successfully registerd to the system", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UserRegistration.this, UserLogin.class);
                            startActivity(intent);
                        }
                    }
                });
    }

    public void progessDialogWork(){
        pd=new ProgressDialog(this, R.style.CustomDialog);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("Registering...Please wait..");
        pd.setCanceledOnTouchOutside(false);
    }

    public void initUtilityVar(){
        userId = (EditText)findViewById(R.id.IdToAccess);
        pass = (EditText)findViewById(R.id.Upass);
        conPass = (EditText)findViewById(R.id.UconPass);
        reqMobile = (EditText)findViewById(R.id.req_mobile);
        Ureg = (Button)findViewById(R.id.EnterReg);
        submitReqMobile = (Button)findViewById(R.id.submit_req_mobile);
    }
}
