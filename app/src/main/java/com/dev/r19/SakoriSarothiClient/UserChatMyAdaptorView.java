package com.dev.r19.SakoriSarothiClient;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserChatMyAdaptorView extends RecyclerView.Adapter<UserChatMyCardView> {
    private Context context;
    private ArrayList<UserChattingModel> myChatList;

    public UserChatMyAdaptorView(Context context, ArrayList<UserChattingModel> myChatList){
        this.context = context;
        this.myChatList = myChatList;
    }
    @NonNull
    @Override
    public UserChatMyCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myChatView = LayoutInflater.from(context).inflate(R.layout.layout_for_my_userchat, parent, false);
        myChatView.setBackgroundColor(Color.LTGRAY);
        UserChatMyCardView userChatMyCardView = new UserChatMyCardView(myChatView);
        return userChatMyCardView;
    }

    @Override
    public void onBindViewHolder(@NonNull UserChatMyCardView holder, int position) {
        UserChattingModel myChatListModel = myChatList.get(position);
        holder.myChatUser.setText(myChatListModel.getChatUser());
        holder.myChatMessage.setText(myChatListModel.getChatText());

    }

    @Override
    public int getItemCount() {
        return myChatList.size();
    }
}
