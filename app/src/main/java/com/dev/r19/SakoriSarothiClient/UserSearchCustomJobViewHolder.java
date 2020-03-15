package com.dev.r19.SakoriSarothiClient;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserSearchCustomJobViewHolder extends RecyclerView.ViewHolder {
    public TextView customJobDateTextview;
    public TextView customJobNameTextview;
    public UserSearchCustomJobViewHolder(@NonNull View itemView) {
        super(itemView);

        customJobDateTextview = (TextView)itemView.findViewById(R.id.custom_jobdate_textview);
        customJobNameTextview = (TextView)itemView.findViewById(R.id.custom_jobname_textview);
    }
}
