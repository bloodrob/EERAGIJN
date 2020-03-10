package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleSignInWithFirebase extends AppCompatActivity {

    //google client
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount mSignInAccount;
    private GoogleSignInAccount mGetSignIndata;
    private int RC_SIGN_IN ;
    private AuthCredential myGoogleCredential;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in_with_firebase);
        //firebaseAuth
        myAuth = FirebaseAuth.getInstance();

        // Check for existing Google Sign In account,
        mSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (mSignInAccount != null){
            Intent intent = new Intent(GoogleSignInWithFirebase.this, NewUserMainActivity.class);
            startActivity(intent);
        }
        //configure google sign in option
        GoogleSignInOptions googleSgnOpt = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSgnOpt);
        googleLoginWork();
    }

    private void googleLoginWork(){
        RC_SIGN_IN = 2;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> resTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // sign in success. auth with firebase
                mGetSignIndata = resTask.getResult(ApiException.class);
                firebaseAuthWithGoogleData(mGetSignIndata);
            }catch (ApiException e) {
                Log.w("Sorry", "Google sign in failed", e);
            }
        }
    }

    // using google
    private void firebaseAuthWithGoogleData(GoogleSignInAccount acct) {
        Log.d("Success", "firebaseAuthWithGoogle:" + acct.getId());
        myGoogleCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        myAuth.signInWithCredential(myGoogleCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           Intent intent = new Intent(GoogleSignInWithFirebase.this, NewUserMainActivity.class);
                           startActivity(intent);
                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(GoogleSignInWithFirebase.this, "Sign in failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
