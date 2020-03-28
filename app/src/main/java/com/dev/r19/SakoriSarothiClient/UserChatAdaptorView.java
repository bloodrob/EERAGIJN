package com.dev.r19.SakoriSarothiClient;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserChatAdaptorView extends RecyclerView.Adapter<UserChatCardView> {

    private ArrayList<UserChattingModel> chatList;
    private Context context;

    public UserChatAdaptorView(Context context, ArrayList<UserChattingModel> chatList){
        this.context = context;
        this.chatList = chatList;
    }
    @NonNull
    @Override
    public UserChatCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(context).inflate(R.layout.layout_for_other_userchat, parent, false);
        //chatView.setBackgroundResource(R.drawable.card_view_corner);
        UserChatCardView userChatCardView = new UserChatCardView(chatView);
        return userChatCardView;
    }

    @Override
    public void onBindViewHolder(@NonNull UserChatCardView holder, int position) {
       final UserChattingModel userChatMod = chatList.get(position);
       holder.nameOfUser.setText(userChatMod.getChatUser());
      // holder.timeOfChat.setText((CharSequence) userChatMod.getChatTime());
       holder.textMessage.setText(userChatMod.getChatText());

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
