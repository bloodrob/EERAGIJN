package com.dev.r19.eeragijn;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserChatting extends AppCompatActivity {

    private EditText typedChat;
    private ListView listOfChatMessage;
    private TextView myChatText, myChatUser, myChatTime;
    // firebase list adaptor to populate the list item
    //private FirebaseListAdaptor<UserChattingModel> chattedList;
    //firebase for create instance and get reference
    private FirebaseAuth auth;
    private FirebaseDatabase databs;
    private DatabaseReference refToDatabs;
    // firebase for store chatting
    DatabaseReference refToStrChat;
    // variable for get the email of the current user
    private String curUserMail;
    // to store the name of the chat user and the chat text
    private String ChatUser;
    private String ChatText;
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
        // for get the name of the user
        getTheCurUserName();
        //end
        // floatingActionButton initiaization
        FloatingActionButton flobar = (FloatingActionButton)findViewById(R.id.below_list);
        // start work of the floating bar
        flobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatText = typedChat.getText().toString().trim();
                // method for store cur user chat text
                updateCurUserChatText();
            }
        });
    }
    //work on getting the name of the current user
    private void getTheCurUserName() {
        databs = FirebaseDatabase.getInstance();
        refToDatabs = databs.getReference("ExistingUserPersionalInfo");
        Query getTheName = refToDatabs.orderByChild("Ex_email").equalTo(curUserMail);
        getTheName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    for (DataSnapshot myData : dataSnapshot.getChildren()) {
                        AdminPushDataModel myMod = myData.getValue(AdminPushDataModel.class);
                        ChatUser = myMod.Ex_name.toString().trim();
                    }
                }
                else {
                    Toast.makeText(UserChatting.this, "You need to be a varified user of employee exchangement system", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserChatting.this, NewUserMainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserChatting.this, "Database Error, Contact to the administrators", Toast.LENGTH_LONG).show();
                return;
            }
        });
    } // end of getCurUserName method
    //start updateCurUserChatText
    private void updateCurUserChatText() {
        // firebase work
        refToStrChat = FirebaseDatabase.getInstance().getReference("UserChat");
        refToStrChat.push().setValue(new UserChattingModel(ChatUser, ChatText));
        Toast.makeText(UserChatting.this, "Stored", Toast.LENGTH_LONG).show();
        // clear the editText
        typedChat.setText("");
    }
}

