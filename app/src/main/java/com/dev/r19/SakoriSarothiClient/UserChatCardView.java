package com.dev.r19.SakoriSarothiClient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserChatCardView extends RecyclerView.ViewHolder {
    public TextView nameOfUser;
    public TextView timeOfChat;
    public TextView textMessage;
    public UserChatCardView(@NonNull View itemView) {
        super(itemView);

        nameOfUser = (TextView) itemView.findViewById(R.id.chat_user);
        timeOfChat = (TextView) itemView.findViewById(R.id.chat_time);
        textMessage = (TextView) itemView.findViewById(R.id.chat_message);
    }
}
