package com.dev.r19.SakoriSarothiClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyChat extends AppCompatActivity {

    private EditText chatText;
    private ImageButton chatSendButton;
    private ListView myChatListView;
    private View viewChat;

    private ArrayList<UserChattingModel> myChatArrayList = new ArrayList<>();
    private ChatAdapter getMyChatArrayList;

    private FirebaseDatabase chatDatabase;
    private DatabaseReference refChatDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        initUtilityVar();


        chatDatabase = FirebaseDatabase.getInstance();
        refChatDatabase = chatDatabase.getReference("UserChat");

        searchchatMesList();

        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitChatData();
            }
        });
    }

    private void submitChatData(){

    }

    private void searchchatMesList(){
        refChatDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.hasChildren()){

                  //  for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UserChattingModel myChattingModel = dataSnapshot.getValue(UserChattingModel.class);
                        myChatArrayList.add(myChattingModel);
                        getMyChatArrayList = new ChatAdapter(MyChat.this, myChatArrayList);
                        myChatListView.setAdapter(getMyChatArrayList);
                    Toast.makeText(MyChat.this, "Success"+myChattingModel.getChatText(), Toast.LENGTH_LONG).show();
                   // }
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

    private void initUtilityVar(){
        chatText = (EditText)findViewById(R.id.chat_text);
        chatSendButton = (ImageButton)findViewById(R.id.chat_send_button);
        myChatListView = (ListView)findViewById(R.id.my_chat_listview);
        myChatListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        myChatListView.setStackFromBottom(true);
    }
}
