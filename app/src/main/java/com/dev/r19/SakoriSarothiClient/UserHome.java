package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import android.graphics.Typeface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.w3c.dom.Text;

public class UserHome extends AppCompatActivity {

    private Button login, reg;
    private TextView userHome;
    private Animation rotate;
    private FirebaseUser auth;
    private TextView userHomeText;
    private Animation blink;
    private LoginButton facebookImage;
    private SignInButton googleImage;
    // for facebook
    private CallbackManager rCallbackManager;
    private AuthCredential myFbCredential;
    private FirebaseAuth myFbAuth;
    private AccessToken myAccessToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        //initialization of utils var
        initUtilityvar();

        Typeface mmedium = Typeface.createFromAsset(getAssets(), "fonts/MontserratMedium.ttf");//font style

        blink = AnimationUtils.loadAnimation(this , R.anim.blink_anim);
        userHomeText = (TextView)findViewById(R.id.user_home_text);
        userHomeText.startAnimation(blink);
        userHomeText.setSelected(true);
        userHome.setTypeface(mmedium);//text style
        //text animation
        rotate = AnimationUtils.loadAnimation(this , R.anim.rotate_1);
        userHome = (TextView) findViewById(R.id.userHome);
        userHome.startAnimation(rotate);

        // for facebook login
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        // start link page user login
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Animation animation = AnimationUtils.loadAnimation(UserHome.this , R.anim.bounce);
              login.startAnimation(animation);
              Intent intent = new Intent(UserHome.this, UserLogin.class);
              startActivity(intent);
          }
      });
        ; //end
        //start link user reg page
       reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Animation animation = AnimationUtils.loadAnimation(UserHome.this , R.anim.bounce);
               reg.startAnimation(animation);
               Intent intent = new Intent(UserHome.this, UserRegistration.class);
               startActivity(intent);
           }
       });

       facebookImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              // Toast.makeText(UserHome.this, "this feature will come soon", Toast.LENGTH_LONG).show();
               facebookLoginWork();
           }
       });

       googleImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(UserHome.this, GoogleSignInWithFirebase.class);
               startActivity(intent);
           }
       });
    }

    private void facebookLoginWork(){
        rCallbackManager = CallbackManager.Factory.create();
        facebookImage.setReadPermissions("email", "public_profile");
        LoginManager.getInstance().registerCallback(rCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(UserHome.this, "Success", Toast.LENGTH_LONG).show();
              //  Log.d("success", "facebook:onSuccess:" + loginResult);
              //  myAccessToken = loginResult.getAccessToken();
                firebaseAuthWithFacebookData(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("cancel", "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("no", "facebook:onError", error);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        rCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //using facebook
    private void firebaseAuthWithFacebookData(AccessToken myToken) {
        Log.d("success", "firebaseAuthWithFacebookData" +myToken);
        myFbCredential = FacebookAuthProvider.getCredential(myToken.getToken());
        myFbAuth = FirebaseAuth.getInstance();
        myFbAuth.signInWithCredential(myFbCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserHome.this, "Sign in success", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(UserHome.this, "Sign in failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void initUtilityvar(){
        userHome = (TextView) findViewById(R.id.userHome);
        login = (Button)findViewById(R.id.toUserLogin);
        reg = (Button)findViewById(R.id.toUserReg);
        googleImage = (SignInButton) findViewById(R.id.google_image);
        googleImage.setSize(SignInButton.SIZE_STANDARD);
        facebookImage= findViewById(R.id.facebook_image);

    }
}
