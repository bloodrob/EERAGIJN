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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private HashMap<String, Object> subTopicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe_to_achannel);
        // init utility var
        initUtilityVar();
        unSubsButDefault();
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

        //map datapost
        subTopicList = new HashMap<String, Object>();

        //sub work start
        topicOnesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Bank");
                topOne = "Bank";
                topicOneUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicOne", topOne);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicTwoSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("State_Govt");
                topTwo = "State_Govt";
                topicTwoUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicTwo", topTwo);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicThreeSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Central_Govt");
                topThree = "Central_Govt";
                topicThreeUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicThree", topThree);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicFourSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Railway");
                topFour = "Railway";
                topicFourUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicFour", topFour);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicFiveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Oil");
                topFive = "Oil";
                topicFiveUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicFive", topFive);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicSixSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Ongc");
                topSix = "Ongc";
                topicSixUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicSix", topSix);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicSevenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Educational_Institute");
                topSeven = "Educational_Institute";
                topicSevenUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicSeven", topSeven);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicEightSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Research");
                topEight = "Research";
                topicEightUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicEight", topEight);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicNineSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("It_Company");
                topNine = "It_Company";
                topicNineUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicNine", topNine);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicTenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Private_Company");
                topTen = "Private_Company";
                topicTenUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicTen", topTen);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicElevenSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("General_Recruitment");
                topEleven = "General_Recruitment";
                topicElevenUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicEleven", topEleven);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });

        topicTwelveSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().subscribeToTopic("Insurance");
                topTwelve = "Insurance";
                topicTwelveUnsub.setEnabled(true);
                subTopicList.put("userId", myId);
                subTopicList.put("topicTwelve", topTwelve);
                refSubDatabase.child(myId).updateChildren(subTopicList);
                searchSubscriptionToTopic();
            }
        });// end sub work

        // unsub work start
        topicOneUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Bank");
                refSubDatabase.child(myId).child("topicOne").removeValue();
                topicOneUnsub.setEnabled(false);
                topicOnesub.setEnabled(true);
                topicOnesub.setText("Subscribe");
            }
        });
        topicTwoUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("State_Govt");
                refSubDatabase.child(myId).child("topicTwo").removeValue();
                topicTwoUnsub.setEnabled(false);
                topicTwoSub.setEnabled(true);
                topicTwoSub.setText("Subscribe");
            }
        });
        topicThreeUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Central_Govt");
                refSubDatabase.child(myId).child("topicThree").removeValue();
                topicThreeUnsub.setEnabled(false);
                topicThreeSub.setEnabled(true);
                topicThreeSub.setText("subscribe");
            }
        });
        topicFourUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Railway");
                refSubDatabase.child(myId).child("topicFour").removeValue();
                topicFourUnsub.setEnabled(false);
                topicFourSub.setEnabled(true);
                topicFourSub.setText("Subscribe");
            }
        });
        topicFiveUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Oil");
                refSubDatabase.child(myId).child("topicFive").removeValue();
                topicFiveUnsub.setEnabled(false);
                topicFiveSub.setEnabled(true);
                topicFiveSub.setText("Subscribe");
            }
        });
        topicSixUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Ongc");
                refSubDatabase.child(myId).child("topicSix").removeValue();
                topicSixUnsub.setEnabled(false);
                topicSixSub.setEnabled(true);
                topicSixSub.setText("Subscribe");
            }
        });
        topicSevenUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Educational_Institute");
                refSubDatabase.child(myId).child("topicSeven").removeValue();
                topicSevenUnsub.setEnabled(false);
                topicSevenSub.setEnabled(true);
                topicSevenSub.setText("Subscribe");
            }
        });
        topicEightUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Research");
                refSubDatabase.child(myId).child("topicEight").removeValue();
                topicEightUnsub.setEnabled(false);
                topicEightSub.setEnabled(true);
                topicEightSub.setText("Subscribe");
            }
        });
        topicNineUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("It_Company");
                refSubDatabase.child(myId).child("topicNine").removeValue();
                topicNineUnsub.setEnabled(false);
                topicNineSub.setEnabled(true);
                topicNineSub.setText("Subscribe");
            }
        });
        topicTenUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Private_Company");
                refSubDatabase.child(myId).child("topicTen").removeValue();
                topicTenUnsub.setEnabled(false);
                topicTenSub.setEnabled(true);
                topicTenSub.setText("Subscribe");
            }
        });
        topicElevenUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("General_Recruitment");
                refSubDatabase.child(myId).child("topicEleven").removeValue();
                topicElevenUnsub.setEnabled(false);
                topicElevenSub.setEnabled(true);
                topicElevenSub.setText("Subscribe");
            }
        });
        topicTwelveUnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging.getInstance().unsubscribeFromTopic("Insurance");
                refSubDatabase.child(myId).child("topicTwelve").removeValue();
                topicTwelveUnsub.setEnabled(false);
                topicTwelveSub.setEnabled(true);
                topicTwelveSub.setText("Subscribe");
            }
        });

    }

    public void searchSubscriptionToTopic() {
        //final Query query = refGetSubDatabase.orderByChild("userId").equalTo(myId);
        refGetSubDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()) {
                    SubscribeToaChannelModel serSubMod = dataSnapshot.getValue(SubscribeToaChannelModel.class);
                    if (myId.equals(serSubMod.getUserId())) {
                        List<SubscribeToaChannelModel> myListSub = new ArrayList<>();
                        myListSub.add(serSubMod);
                        Toast.makeText(SubscribeToAChannel.this, "gscgjsg", Toast.LENGTH_SHORT).show();
                        for (SubscribeToaChannelModel temp : myListSub) {
                            if ("Bank".equals(temp.getTopicOne())) {
                                topicOnesub.setEnabled(false);
                                topicOnesub.setText("Subscribed");
                                topicOneUnsub.setEnabled(true);
                            }
                            if ("State_Govt".equals(temp.getTopicTwo())) {
                                topicTwoSub.setEnabled(false);
                                topicTwoSub.setText("Subscribed");
                                topicTwoUnsub.setEnabled(true);
                            }
                            if ("Central_Govt".equals(temp.getTopicThree())) {
                                topicThreeSub.setEnabled(false);
                                topicThreeSub.setText("Subscribed");
                                topicThreeUnsub.setEnabled(true);
                            }
                            if ("Railway".equals(temp.getTopicFour())) {
                                topicFourSub.setEnabled(false);
                                topicFourSub.setText("Subscribed");
                                topicFourUnsub.setEnabled(true);
                            }
                            if ("Oil".equals(temp.getTopicFive())) {
                                topicFiveSub.setEnabled(false);
                                topicFiveSub.setText("Subscribed");
                                topicFiveUnsub.setEnabled(true);
                            }
                            if ("Ongc".equals(temp.getTopicSix())) {
                                topicSixSub.setEnabled(false);
                                topicSixSub.setText("Subscribed");
                                topicSixUnsub.setEnabled(true);
                            }
                            if ("Educational_Institute".equals(temp.getTopicSeven())) {
                                topicSevenSub.setEnabled(false);
                                topicSevenSub.setText("Subscribed");
                                topicSevenUnsub.setEnabled(true);
                            }
                            if ("Research".equals(temp.getTopicEight())) {
                                topicEightSub.setEnabled(false);
                                topicEightSub.setText("Subscribed");
                                topicEightUnsub.setEnabled(true);
                            }
                            if ("It_Company".equals(temp.getTopicNine())) {
                                topicNineSub.setEnabled(false);
                                topicNineSub.setText("Subscribed");
                                topicNineUnsub.setEnabled(true);
                            }
                            if ("Private_Company".equals(temp.getTopicTen())) {
                                topicTenSub.setEnabled(false);
                                topicTenSub.setText("Subscribed");
                                topicTenUnsub.setEnabled(true);
                            }
                            if ("General_Recruitment".equals(temp.getTopicEleven())) {
                                topicElevenSub.setEnabled(false);
                                topicElevenSub.setText("Subscribed");
                                topicElevenUnsub.setEnabled(true);
                            }
                            if ("Insurance".equals(temp.getTopicTwelve())) {
                                topicTwelveSub.setEnabled(false);
                                topicTwelveSub.setText("Subscribed");
                                topicTwelveUnsub.setEnabled(true);
                            }
                        }
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
    private void unSubsButDefault() {
        topicOneUnsub.setEnabled(false);
        topicTwoUnsub.setEnabled(false);
        topicThreeUnsub.setEnabled(false);
        topicFourUnsub.setEnabled(false);
        topicFiveUnsub.setEnabled(false);
        topicSixUnsub.setEnabled(false);
        topicSevenUnsub.setEnabled(false);
        topicEightUnsub.setEnabled(false);
        topicNineUnsub.setEnabled(false);
        topicTenUnsub.setEnabled(false);
        topicElevenUnsub.setEnabled(false);;
        topicTwelveUnsub.setEnabled(false);
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
        topicTwelveUnsub = (Button)findViewById(R.id.topic_twelve_unSub);
    }
}
