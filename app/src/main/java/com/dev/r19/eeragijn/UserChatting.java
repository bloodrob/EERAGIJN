package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class UserChatting extends AppCompatActivity {

    private EditText typedChat;
    private ListView listOfChatMessage;
    private TextView myChatText, myChatUser, myChatTime;
    // firebase list adaptor to populate the list item
    //private FirebaseListAdaptor<UserChattingModel> chattedList;
    //firebase for create instance and get reference
    private FirebaseAuth auth;
    private FirebaseDatabase databs, database1;
    private DatabaseReference refToDatabs, getRefToDatabs1;
    // firebase for store chatting
    DatabaseReference refToStrChat;
    // variable for get the email of the current user
    private String curUserMail;
    // to store the name of the chat user and the chat text
    private String ChatUser;
    private String ChatText;
    private Long ChatTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chatting);
        // initialisation of the button
        typedChat = (EditText)findViewById(R.id.chat_message);
        listOfChatMessage = (ListView)findViewById(R.id.list_of_chat_message);
        myChatText = (TextView)findViewById(R.id.chat_text);
        myChatUser = (TextView)findViewById(R.id.chat_user);
        myChatTime = (TextView)findViewById(R.id.chat_time);
        //end of initialization  of button

        // firebase work for get the current user
        auth = FirebaseAuth.getInstance();
        curUserMail = auth.getCurrentUser().getEmail().toString().trim();
        //Toast.makeText(UserChatting.this, "email is :"+curUserMail, Toast.LENGTH_LONG).show();
        //firebase init
        databs = FirebaseDatabase.getInstance();
        refToDatabs = databs.getReference("NewUserPersionalInfo");
        // for get the name of the user
        getTheCurUserName();
        //geeting the chat data
        getTheChatData();

        //end
        // floatingActionButton initiaization
        FloatingActionButton flobar = (FloatingActionButton)findViewById(R.id.below_list);
        // start work of the floating bar
        flobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatText = typedChat.getText().toString().trim();
                ChatTime = 7000000000000L;
                // method for store cur user chat text
                updateCurUserChatText(ChatText, ChatUser, ChatTime);
            }
        });
    }
    //work on getting the name of the current user
    public void getTheCurUserName() {
        final Query query = refToDatabs.orderByChild("Email").equalTo(curUserMail);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.hasChildren()) {
                    UserChatGetNameModel myMod1 = dataSnapshot.getValue(UserChatGetNameModel.class);
                    ChatUser = myMod1.Name.toString().trim();
                    Toast.makeText(UserChatting.this, "name is :"+ChatUser, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UserChatting.this, "You need to be a varified user of employee exchangement system", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserChatting.this, NewUserMainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserChatting.this, "Database Error, Contact to the administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    } // end of getCurUserName method
    //start updateCurUserChatText
    private void updateCurUserChatText(String ChatText, String ChatUser, long ChatTime) {
        // firebase work
        database1 = FirebaseDatabase.getInstance();
        getRefToDatabs1 = database1.getReference("UserChat");
        UserChattingModel mod1 = new UserChattingModel(ChatText, ChatUser, ChatTime);
        getRefToDatabs1.child(ChatUser).setValue(mod1);
        updateChat();
        // clear the editText
        typedChat.setText("");
    }
    //upodate chat
    private void updateChat() {
        getRefToDatabs1.child(ChatUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserChattingModel mod2 = dataSnapshot.getValue(UserChattingModel.class);
                if (mod2 == null) {
                    Toast.makeText(UserChatting.this, "Error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // get the chat list
    private void getTheChatData() {

    }
}

