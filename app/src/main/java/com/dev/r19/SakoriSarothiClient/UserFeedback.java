package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFeedback extends AppCompatActivity {

    private Spinner feedbackCategory;
    private EditText feedbackMessage;
    private Button submitFeedback;
    // arraylist and array adaptor for feedback category
    private List<String> feedbackCategoryList;
    private ArrayAdapter<String> getFeedbackCategoryList;
    // get the selected feedback category
    private String feedType;
    private String feedMes;
    //firebase
    private FirebaseDatabase database123;
    private FirebaseDatabase databaseName;
    private DatabaseReference dbRef123;
    private DatabaseReference refToDatabaseName;
    private FirebaseUser userfd;
    // feedback root child and Data;
    private String FeedbackUserName;
    private String FeedbackUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        // init activity var
        initActivityVar();
        // init , add and get the feedback categorylist
        getCategoryFeedback();
        // current user name
        takeTheCurUserName();
        // on submit
        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTheInputString();
                submitInsertedFeedback();
            }
        });
    }
    private void getCategoryFeedback() {
        feedbackCategoryList = new ArrayList<String>();
        feedbackCategoryList.add("Comment");
        feedbackCategoryList.add("Query");
        feedbackCategoryList.add("Suggetion");

        getFeedbackCategoryList = new ArrayAdapter<String>(UserFeedback.this, android.R.layout.simple_spinner_item, feedbackCategoryList);
        getFeedbackCategoryList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        feedbackCategory.setAdapter(getFeedbackCategoryList);

        feedbackCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                feedType = parent.getItemAtPosition(position).toString().trim();
                Toast.makeText(UserFeedback.this, "You Select :"+feedType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(UserFeedback.this, "You Select Nothing", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
    private void getTheInputString() {
        feedMes = feedbackMessage.getText().toString().trim();
    }
    private void takeTheCurUserName() {
        userfd = FirebaseAuth.getInstance().getCurrentUser();
        if (userfd != null) {
            FeedbackUserEmail = userfd.getEmail();
        }
        // for data retrive
        userNameForFeedback();
    }
    private void userNameForFeedback() {
        databaseName = FirebaseDatabase.getInstance();
        refToDatabaseName = databaseName.getReference("UserProfile");
        final Query query = refToDatabaseName.orderByChild("Email").equalTo(FeedbackUserEmail);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()) {
                    UserGetNameModel nameMod = dataSnapshot.getValue(UserGetNameModel.class);
                    FeedbackUserName = nameMod.getName().trim();
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

            }
        });
    }
    private void submitInsertedFeedback() {
        UserFeedbackModel feedMod = new UserFeedbackModel();
        // for data store
        database123 = FirebaseDatabase.getInstance();
        dbRef123 = database123.getReference("UserFeedback");
        feedMod.setFeedbackCategory(feedType);
        feedMod.setFeedbackPerson(FeedbackUserName);
        feedMod.setMessageFeedback(feedMes);
        dbRef123.push().setValue(feedMod);
        Toast.makeText(UserFeedback.this, "Thanks, For Your Valuable feedback", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserFeedback.this, NewUserMainActivity.class);
        startActivity(intent);

    }
    private void initActivityVar() {
        feedbackCategory = (Spinner)findViewById(R.id.feedback_category);
        feedbackMessage = (EditText)findViewById(R.id.feedback_message);
        submitFeedback = (Button)findViewById(R.id.submit_feedback);
    }
}
