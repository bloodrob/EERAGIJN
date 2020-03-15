package com.dev.r19.SakoriSarothiClient;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private ListView listOfChatMessage;
    private TextView myChatText, myChatUser, myChatTime;
    // firebase list adaptor to populate the list item
    //private FirebaseListAdaptor<UserChattingModel> chattedList;
    //firebase for create instance and get reference
    private FirebaseAuth auth;
    private FirebaseDatabase databs, database1;
    private DatabaseReference refToDatabs;
    private DatabaseReference getRefToDatabs1;
    private DatabaseReference getRefToChatMes;
    // firebase for store chatting
    private DatabaseReference refToStrChat;
    //arraylist for list of message
    private List<String> chatMesList;
    // arrayAdaptor for list of message
    private ArrayAdapter<String> getChatmesList;
    // variable for get the email of the current user
    private String curUserMail;
    // to store the name of the chat user and the chat text
    private String ChatUserName;
    private String ChatTextMes;
   // private String ChatTimeMes;
    private Map ChatTimeMes;
    //variable for Id generator
    static final String alphaNum = "ABCDEFGHIJKLMONPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
    static SecureRandom random = new SecureRandom();
    static final int len = 10;
    static StringBuilder strngbldr;
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
        // firebase work for entry and retrieve data
        database1 = FirebaseDatabase.getInstance();
        getRefToDatabs1 = database1.getReference("UserChat");
        getRefToChatMes = database1.getReference("UserChat/");
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
    public void getTheCurUserName() {
        final Query query = refToDatabs.orderByChild("Email").equalTo(curUserMail);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.hasChildren()) {
                    UserGetNameModel myMod1 = dataSnapshot.getValue(UserGetNameModel.class);
                    ChatUserName = myMod1.getName().toString().trim();
                    Toast.makeText(UserChatting.this, "name is :"+ChatUserName, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(UserChatting.this, "Data not found", Toast.LENGTH_LONG).show();
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
    private void updateCurUserChatText() {
        UserChattingModel mod1 = new UserChattingModel();
        mod1.setChatUser(ChatUserName);
        mod1.setChatText(ChatTextMes);
        //mod1.setChatTime(ChatTimeMes);
        getRefToDatabs1.push().child(strngbldr.toString()).setValue(mod1);
      //  getRefToDatabs1.child(strngbldr.toString()).updateChildren(ChatTimeMes);
        // get chat data
        getTheChatData();
        // clear the editText
        typedChat.setText("");

    }
    // get the chat list
    private void getTheChatData() {
        // init the arraylist for the message list to add
        chatMesList = new ArrayList<String>();
        getRefToChatMes.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserChattingModel useMod = dataSnapshot.getValue(UserChattingModel.class);
                if (dataSnapshot.hasChildren()) {
                    chatMesList.add(useMod.getChatUser() +"\n"+"Mes :"+useMod.getChatText()+"\n " +"\n\n");
                    getChatmesList = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,chatMesList);
                    listOfChatMessage.setAdapter(getChatmesList);
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

            }
        }) ;

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
}

