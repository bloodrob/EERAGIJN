package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.Callback;

public class MobileSIgnIn extends AppCompatActivity {

    private Intent intent;
    private String myMobile;
    private TextView varifyView;
    private EditText mobileOtp;
    private Button submitOtp;
    private Button submitMobileReg;
    private Callback mCallback;
    private PhoneAuthProvider phnAuthProvider;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks;
    private PhoneAuthCredential credentials;
    private String mVerificationId;
    private String getMyOtp;
    private String rcvCode;
    private FirebaseAuth auth;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_sign_in);
        initUtilityVar();
        utilitySetDefault();
        initPdBar();
        pd.show();
        intent = getIntent();
        if (intent != null) {
            myMobile = intent.getStringExtra("mobile").trim();
        }
        sendOptToUser(myMobile);
        auth = FirebaseAuth.getInstance();
        submitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterButtonClickFn();
            }
        });
    }

    private void sendOptToUser(String MyMobile){
        phnAuthProvider = PhoneAuthProvider.getInstance();
        phnAuthProvider.verifyPhoneNumber(
                myMobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        rcvCode = phoneAuthCredential.getSmsCode();
                        mobileOtp.setText(rcvCode);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken){
                        pd.dismiss();
                        super.onCodeSent(s, forceResendingToken);
                        mVerificationId = s;
                    }
                }
        );
    }

    public void afterButtonClickFn(){
        getMyOtp = mobileOtp.getText().toString().trim();
        if (!rcvCode.equals(getMyOtp)){
            varifyView.setText("OTP not matched. Please enter correctly");
        }
        if (rcvCode.equals(getMyOtp)){
            varifyView.setText("verified");
            verifyThecode();
        }
       /* mCallBacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                rcvCode = phoneAuthCredential.getSmsCode();
                if (rcvCode.equals(getMyOtp)) {
                    varifyView.setText("verified");
                    verifyThecode();
                    //  submitMobileReg.setEnabled(true);
                }
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken){
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
            }

        }; */
    }

    private void  verifyThecode(){
        credentials = PhoneAuthProvider.getCredential(mVerificationId, rcvCode);
        signInWithFirebaseUsingMobile(credentials);
    }

    private void signInWithFirebaseUsingMobile(PhoneAuthCredential credentialsMob){
        auth.signInWithCredential(credentialsMob)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                          //  submitMobileReg.setEnabled(true);
                            //Toast.makeText(MobileSIgnIn.this, "Success", Toast.LENGTH_LONG).show();
                        }else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    public void initPdBar(){
        pd=new ProgressDialog(this, R.style.CustomDialog);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setTitle("sending OTP...Please wait..");
        pd.setCanceledOnTouchOutside(false);
    }
    public void utilitySetDefault(){
        //submitMobileReg.setEnabled(false);
    }

    public void initUtilityVar(){
        varifyView = (TextView)findViewById(R.id.varify_view);
        mobileOtp = (EditText)findViewById(R.id.mobile_otp);
        submitOtp = (Button)findViewById(R.id.submit_otp);
        submitMobileReg = (Button)findViewById(R.id.submit_req_mobile);
    }
}
