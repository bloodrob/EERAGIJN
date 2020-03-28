package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserChatting extends AppCompatActivity {

    private EditText typedChat;
    private Button submitChat;

    private FirebaseAuth auth;
    private FirebaseDatabase databs, database1;
    private DatabaseReference refToDatabs;
    private DatabaseReference getRefToDatabs1;
    private DatabaseReference getRefToChatMes;
    private ValueEventListener listener;
    private ValueEventListener chatListener;
    // firebase for store chatting
    private DatabaseReference refToStrChat;
    // variable for get the email of the current user
    private String curUserMail;
    // to store the name of the chat user and the chat text
    private String ChatUserName;
    private String ChatTextMes;
   // private String ChatTimeMes;
    private Map ChatTimeMes;
    //variable for Id generator
    private final String alphaNum = "ABCDEFGHIJKLMONPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    private SecureRandom random = new SecureRandom();
    private final int len = 10;
    private StringBuilder strngbldr;

    private RecyclerView chatRecycler;
    private RecyclerView myCHatRecycler;
    private List<UserChattingModel> chatMesList = new ArrayList<>();
    private List<UserChattingModel> myChatMesList = new ArrayList<>();
    private RecyclerView.Adapter getChatmesList;
    private RecyclerView.Adapter getMyChatMesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chatting);
        // initialisation of the utility var
        initUtilityVar();

        // firebase work for get the current user
        auth = FirebaseAuth.getInstance();
        curUserMail = auth.getCurrentUser().getUid();
        database1 = FirebaseDatabase.getInstance();
        getRefToDatabs1 = database1.getReference("UserChat");
        getRefToChatMes = database1.getReference("UserChat/");
        databs = FirebaseDatabase.getInstance();
        refToDatabs = databs.getReference("UserProfile");

        // for get the name of the user
        getTheCurUserName();
        //geeting the chat data
        getTheChatData();

        // start work of the floating bar
        submitChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatTextMes = typedChat.getText().toString().trim();
                //method declare for random id generator
                genRandomString(len);
                //get current time in string
                getCurrentTimeOfSystem();
                // method for store cur user chat text
                updateCurUserChatText();
            }
        });
    }
    //work on getting the name of the current user
    private void getTheCurUserName() {
        final Query query = refToDatabs.orderByChild("activeId").equalTo(curUserMail);
        listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UserProfileUpdateModel userProfileUpdateModel = snapshot.getValue(UserProfileUpdateModel.class);
                        ChatUserName = userProfileUpdateModel.getName();
                        Toast.makeText(UserChatting.this, "Name is2"+ChatUserName, Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    ChatUserName = "user1";
                    Toast.makeText(UserChatting.this, "Name is1"+ChatUserName, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserChatting.this, "error, Contact developer", Toast.LENGTH_LONG).show();
                return;
            }
        };
        query.addValueEventListener(listener);
    } // end of getCurUserName method
    //start updateCurUserChatText
    private void updateCurUserChatText() {
        UserChattingModel mod1 = new UserChattingModel();
        Toast.makeText(UserChatting.this, "Name is"+ChatUserName, Toast.LENGTH_SHORT).show();
        mod1.setChatUser(ChatUserName);
        mod1.setChatText(ChatTextMes);
        //mod1.setChatTime(ChatTimeMes);
        getRefToDatabs1.push().setValue(mod1);
      //  getRefToDatabs1.child(strngbldr.toString()).updateChildren(ChatTimeMes);
        // get chat data
        chatMesList.clear();
        myChatMesList.clear();
        getTheChatData();
        // clear the editText
        typedChat.setText("");

    }
    // get the chat list
    private void getTheChatData() {
        chatMesList.clear();
        // init the arraylist for the message list to add
        getRefToChatMes.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()){
                  //  for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        UserChattingModel userChatMod1 = dataSnapshot.getValue(UserChattingModel.class);
                        if ("Robin".equals(userChatMod1.getChatUser())){
                            chatMesList.add(userChatMod1);

                            getChatmesList = new UserChatAdaptorView(UserChatting.this, (ArrayList<UserChattingModel>) chatMesList);
                            chatRecycler.setAdapter(getChatmesList);
                        }
                        else {
                            myChatMesList.add(userChatMod1);
                            getMyChatMesList = new UserChatAdaptorView(UserChatting.this, (ArrayList<UserChattingModel>) myChatMesList);
                            myCHatRecycler.setAdapter(getMyChatMesList);
                        }
                 //   }
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
    // method defining for random id generator
    public String genRandomString(int len) {
        strngbldr = new StringBuilder(len);
        for (int i =0; i<len; i++) {
            strngbldr.append(alphaNum.charAt(random.nextInt(alphaNum.length())));
        }
        return strngbldr.toString().trim();
    }
    public void getCurrentTimeOfSystem() {
        /*final long timestamp = new Date().getTime();
        // java util date
        final Calendar calTime = Calendar.getInstance();
        calTime.setTimeInMillis(timestamp);
        // egt time in minutes
       // final int minutes = calTime.get(Calendar.MINUTE);
        ChatTimeMes = new SimpleDateFormat("HH:mm:ss:SS").format(calTime.getTime());
        Toast.makeText(UserChatting.this, "You Time :"+ChatTimeMes, Toast.LENGTH_LONG).show(); */

      //  ChatTimeMes = new HashMap();
        //ChatTimeMes.put("ChatTime", ServerValue.TIMESTAMP);

    }

    private void initUtilityVar(){
        typedChat = (EditText)findViewById(R.id.chat_message);
        submitChat = (Button)findViewById(R.id.below_list);

        chatRecycler = (RecyclerView)findViewById(R.id.chat_recycle);
        chatRecycler.setHasFixedSize(true);
        chatRecycler.setLayoutManager(new LinearLayoutManager(UserChatting.this));

        myCHatRecycler = (RecyclerView)findViewById(R.id.my_chat_recycler);
        myCHatRecycler.setHasFixedSize(true);
        myCHatRecycler.setLayoutManager(new LinearLayoutManager(UserChatting.this));
    }
}

