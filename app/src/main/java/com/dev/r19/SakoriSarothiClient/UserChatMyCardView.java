package com.dev.r19.SakoriSarothiClient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserChatMyCardView extends RecyclerView.ViewHolder {
    public TextView myChatUser;
    public TextView myChatTime;
    public TextView myChatMessage;
    public UserChatMyCardView(@NonNull View itemView) {
        super(itemView);

        myChatUser = (TextView)itemView.findViewById(R.id.mychat_user);
        myChatTime = (TextView)itemView.findViewById(R.id.mychat_time);
        myChatMessage = (TextView)itemView.findViewById(R.id.mychat_message);
    }
}
