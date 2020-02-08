package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.messaging.FirebaseMessaging;

public class SubscribeToAChannel extends AppCompatActivity {

    private Button topicOnesub;
    private Button topicOneUnsub;
    private Button topicTwoSub;
    private Button topicTwoUnsub;
    private Button topicThreeSub;
    private Button topicThreeUnsub;
    private Button topicFourSub;
    private Button topicFourUnsub;
    private Button topicFiveSub;
    private Button topicFiveUnsub;
    private Button topicSixSub;
    private Button topicSixUnsub;
    private Button topicSevenSub;
    private Button topicSevenUnsub;
    private Button topicEightSub;
    private Button topicEightUnsub;
    private Button topicNineSub;
    private Button topicNineUnsub;
    private Button topicTenSub;
    private Button topicTenUnsub;
    private Button topicElevenSub;
    private Button topicElevenUnsub;
    private Button topicTwelveSub;
    private Button topicTwelveUnsub;

    private String topOne;
    private String topTwo;
    private String topThree;
    private String topFour;
    private String topFive;
    private String topSix;
    private String topSeven;
    private String topEight;
    private String topNine;
    private String topTen;
    private String topEleven;
    private String topTwelve;

    private String myId;

    public static final String TAG = "Sorry";

    private FirebaseDatabase subDatabase;
    private FirebaseDatabase getSubDatabase;
    private DatabaseReference refSubDatabase;
    private DatabaseReference refGetSubDatabase;
   // private Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_to_achannel);
        // init utility var
        initUtilityVar();
        //fireabse work
        subDatabase = FirebaseDatabase.getInstance();
        refSubDatabase = subDatabase.getReference("SubscriptionTopic");
        getSubDatabase = FirebaseDatabase.getInstance();
        refGetSubDatabase = getSubDatabase.getReference("SubscriptionTopic");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            myId = user.getUid();
        }

        //check subscription of topic
        searchSubscriptionToTopic();
        //button work
        topicOnesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Bank");
                topOne = "Bank";
                saveSubscriptionToATopic();
            }
        });

        topicTwoSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("State_Govt");
                topTwo = "State_Govt";
                saveSubscriptionToATopic();
            }
        });

        topicThreeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Central_Govt");
                topThree = "Central_Govt";
                saveSubscriptionToATopic();
            }
        });

        topicFourSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Railway");
                topFour = "Railway";
                saveSubscriptionToATopic();
            }
        });

        topicFiveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Oil");
                topFive = "Oil";
                saveSubscriptionToATopic();
            }
        });

        topicSixSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Ongc");
                topSix = "Ongc";
                saveSubscriptionToATopic();
            }
        });

        topicSevenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Educational_Institute");
                topSeven = "Educational_Institute";
                saveSubscriptionToATopic();
            }
        });

        topicEightSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Research");
                topEight = "Research";
                saveSubscriptionToATopic();
            }
        });

        topicNineSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("It_Company");
                topNine = "It_company";
                saveSubscriptionToATopic();
            }
        });

        topicTenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Private_Company");
                topTen = "Private_Company";
                saveSubscriptionToATopic();
            }
        });

        topicElevenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("General_Recruitment");
                topEleven = "General_Recruitment";
                saveSubscriptionToATopic();
            }
        });

        topicTwelveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Insurance");
                topTwelve = "Insurance";
                saveSubscriptionToATopic();
            }
        });

        //store subscription topic in firebase
        //saveSubscriptionToATopic();
    }

    public void searchSubscriptionToTopic() {
        final Query query = refGetSubDatabase.orderByChild("userId").equalTo(myId);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){
                SubscribeToaChannelModel serSubMod = dataSnapshot.getValue(SubscribeToaChannelModel.class);

                    Toast.makeText(SubscribeToAChannel.this, "You Are", Toast.LENGTH_LONG).show();
                    if ("Bank".equals(serSubMod.getTopicOne())) {
                        topicOnesub.setEnabled(false);
                        topicOnesub.setText("Subscribed");
                    }
                    if ("State_Govt".equals(serSubMod.getTopicTwo())){
                        topicTwoSub.setEnabled(false);
                        topicTwoSub.setText("Subscribed");
                    }
                    if ("Central_Govt".equals(serSubMod.getTopicThree())) {
                        topicThreeSub.setEnabled(false);
                        topicThreeSub.setText("Subscribed");
                    }
                    if ("Railway".equals(serSubMod.getTopicFour())) {
                        topicFourSub.setEnabled(false);
                        topicFourSub.setText("Subscribed");
                    }
                    if ("Oil".equals(serSubMod.getTopicFive())) {
                        topicFiveSub.setEnabled(false);
                        topicFiveSub.setText("Subscribed");
                    }
                    if ("Ongc".equals(serSubMod.getTopicSix())) {
                        topicSixSub.setEnabled(false);
                        topicSixSub.setText("Subscribed");
                    }
                    if ("Educational_Institute".equals(serSubMod.getTopicSeven())) {
                        topicSevenSub.setEnabled(false);
                        topicSevenSub.setText("Subscribed");
                    }
                    if ("Research".equals(serSubMod.getTopicEight())) {
                        topicEightSub.setEnabled(false);
                        topicEightSub.setText("Subscribed");
                    }
                    if ("Tt_Company".equals(serSubMod.getTopicNine())) {
                        topicNineSub.setEnabled(false);
                        topicNineSub.setText("Subscribed");
                    }
                    if ("Private_Company".equals(serSubMod.getTopicTen())) {
                        topicTenSub.setEnabled(false);
                        topicTenSub.setText("Subscribed");
                    }
                    if ("General_Recruitment".equals(serSubMod.getTopicEleven())) {
                        topicElevenSub.setEnabled(false);
                        topicElevenSub.setText("Subscribed");
                    }
                    if ("Insurance".equals(serSubMod.getTopicTwelve())) {
                        topicTwelveSub.setEnabled(false);
                        topicTwelveSub.setText("Subscribed");
                    }
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

    public void saveSubscriptionToATopic() {
        SubscribeToaChannelModel subMod = new SubscribeToaChannelModel();
        subMod.setUserId(myId);
        subMod.setTopicOne(topOne);
        subMod.setTopicTwo(topTwo);
        subMod.setTopicThree(topThree);
        subMod.setTopicFour(topFour);
        subMod.setTopicFive(topFive);
        subMod.setTopicSix(topSix);
        subMod.setTopicSeven(topSeven);
        subMod.setTopicEight(topEight);
        subMod.setTopicNine(topNine);
        subMod.setTopicTen(topTen);
        subMod.setTopicEleven(topEleven);
        subMod.setTopicTwelve(topTwelve);

        refSubDatabase.child(myId).setValue(subMod);
        updateSaveSubscriptionToATopic();
        searchSubscriptionToTopic();
    }

    public void updateSaveSubscriptionToATopic() {

        refSubDatabase.child(myId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SubscribeToaChannelModel subMod1 = dataSnapshot.getValue(SubscribeToaChannelModel.class);
                    if (subMod1 == null) {
                        Log.e(TAG, "Data is null");
                        return;
                    }
                Toast.makeText(SubscribeToAChannel.this, "Subscribed", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initUtilityVar() {
        topicOnesub = (Button)findViewById(R.id.topic_one_sub);
        topicOneUnsub = (Button)findViewById(R.id.topic_one_unSub);
        topicTwoSub = (Button)findViewById(R.id.topic_two_sub);
        topicTwoUnsub = (Button)findViewById(R.id.topic_two_unSub);
        topicThreeSub = (Button)findViewById(R.id.topic_three_sub);
        topicThreeUnsub = (Button)findViewById(R.id.topic_three_unSub);
        topicFourSub = (Button)findViewById(R.id.topic_four_sub);
        topicFourUnsub = (Button)findViewById(R.id.topic_four_unSub);
        topicFiveSub = (Button)findViewById(R.id.topic_five_sub);
        topicFiveUnsub = (Button)findViewById(R.id.topic_five_unSub);
        topicSixSub = (Button)findViewById(R.id.topic_six_sub);
        topicSixUnsub = (Button)findViewById(R.id.topic_six_unSub);
        topicSevenSub = (Button)findViewById(R.id.topic_seven_sub);
        topicSevenUnsub = (Button)findViewById(R.id.topic_seven_unSub);
        topicEightSub = (Button)findViewById(R.id.topic_eight_sub);
        topicEightUnsub = (Button)findViewById(R.id.topic_eight_unSub);
        topicNineSub = (Button)findViewById(R.id.topic_nine_sub);
        topicNineUnsub = (Button)findViewById(R.id.topic_nine_unSub);
        topicTenSub = (Button)findViewById(R.id.topic_ten_sub);
        topicTenUnsub = (Button)findViewById(R.id.topic_ten_unSub);
        topicElevenSub = (Button)findViewById(R.id.topic_eleven_sub);
        topicElevenUnsub = (Button)findViewById(R.id.topic_eleven_unSub);
        topicTwelveSub = (Button)findViewById(R.id.topic_twelve_sub);
        topicTwelveUnsub = (Button)findViewById(R.id.topic_two_unSub);

    }
}
