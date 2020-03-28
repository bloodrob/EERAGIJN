package com.dev.r19.SakoriSarothiClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends BaseAdapter {

    private static LayoutInflater myInflater = null;
    private ArrayList<UserChattingModel> chatMessageList;
    private Context context;
    private LinearLayout textLinearLayout;
    private LinearLayout mainLinearLayout;
    private TextView chatTextView;
    private View myView;

    public ChatAdapter(Context context, ArrayList<UserChattingModel> chatMessageList){
        this.context = context;
        this.chatMessageList = chatMessageList;
    }
    @Override
    public int getCount() {
        return chatMessageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserChattingModel chatMod = (UserChattingModel) chatMessageList.get(position);
        myView = convertView;
        if (convertView == null) {
            myInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            myView = myInflater.inflate(R.layout.mychat_text_layout,null);
        }
        chatTextView = (TextView)myView.findViewById(R.id.chat_textView);
        chatTextView.setText(chatMod.getChatUser() +"\n"+ chatMod.getChatText());
        textLinearLayout = (LinearLayout)myView.findViewById(R.id.text_linear_layout);
        mainLinearLayout = (LinearLayout)myView.findViewById(R.id.main_linear_layout);

        //checking the message side
        if ("Robin".equals(chatMod.getChatUser())) {
            textLinearLayout.setBackgroundResource(R.drawable.own_chat_text);
            mainLinearLayout.setGravity(Gravity.RIGHT);
           // chatTextView.setBackgroundResource(R.drawable.own_chat_text);
        }
        else {
            textLinearLayout.setBackgroundResource(R.drawable.other_chat_text);
            mainLinearLayout.setGravity(Gravity.LEFT);
        }
        chatTextView.setTextColor(Color.BLACK);
        return myView;
    }
    public void add(UserChattingModel myChatView){
        chatMessageList.add(myChatView);
    }
}
